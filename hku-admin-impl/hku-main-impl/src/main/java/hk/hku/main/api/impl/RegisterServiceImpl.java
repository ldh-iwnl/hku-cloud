package hk.hku.main.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import hk.hku.common.core.api.BaseApiService;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.common.core.utils.MD5Utils;
import hk.hku.common.core.utils.SaltUtils;
import hk.hku.main.api.RegisterService;
import hk.hku.main.api.impl.entity.SysUser;
import hk.hku.main.api.impl.entity.SysUserId;
import hk.hku.main.api.impl.mapper.SysUserIdMapper;
import hk.hku.main.api.impl.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kyleli
 * @ClassName RegisterServiceImpl
 */

@RestController
public class RegisterServiceImpl extends BaseApiService implements RegisterService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserIdMapper sysUserIdMapper;

    @Override
    public BaseResponse<String> register(String phoneNumber, String password) {
        // validate the parameter if it is empty
        if(StringUtils.isEmpty(phoneNumber)){
            return setResultError("phone number cannot be empty");
        }
        if(StringUtils.isEmpty(password)){
            return setResultError("password cannot be empty");
        }
       //check if the phone number is already registered
        QueryWrapper<SysUser> objectQueryWrapper = new QueryWrapper<SysUser>();
        SysUser usr1 = sysUserMapper.selectOne(objectQueryWrapper.eq("phone_number", phoneNumber));
        if(usr1!=null){
            return setResultError("phone number already registered");
        }
        String salt = SaltUtils.getSalt();
        String md5Password = MD5Utils.md5(password+salt);
        SysUser sysUser = new SysUser(phoneNumber,md5Password,salt);
        //generate global user id
        SysUserId sysUserId = new SysUserId();
        int insertResult = sysUserIdMapper.getUserId(sysUserId);
        if(insertResult<=0){
            return setResultError("failed to generate global user id");
        }
        sysUser.setId(sysUserId.getUserId());
        int insert = sysUserMapper.insert(sysUser);
        return setResultDb(insert);
    }
}
