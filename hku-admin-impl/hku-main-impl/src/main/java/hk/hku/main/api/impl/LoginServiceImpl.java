package hk.hku.main.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import hk.hku.common.core.api.BaseApiService;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.common.core.utils.IpUtil;
import hk.hku.common.core.utils.MD5Utils;
import hk.hku.common.core.utils.RedisUtil;
import hk.hku.common.core.utils.TokenUtils;
import hk.hku.main.api.LoginService;
import hk.hku.main.api.dto.req.LoginUserReqDto;
import hk.hku.main.api.impl.entity.SysUser;
import hk.hku.main.api.impl.entity.SysUserLoginLog;
import hk.hku.main.api.impl.manage.LoginLogManage;
import hk.hku.main.api.impl.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author kyleli
 * @ClassName LoginServiceImpl
 */

@RestController
@Slf4j
public class LoginServiceImpl extends BaseApiService implements LoginService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginLogManage loginLogManage;

    @Override
    public BaseResponse<JSONObject> login(LoginUserReqDto loginUserReqDto) {
        //validate the parameter if it is empty
        String phoneNumber = loginUserReqDto.getPhoneNumber();
        if(StringUtils.isEmpty(phoneNumber)){
            return setResultError("phone number cannot be empty");
        }
        String password = loginUserReqDto.getPassword();
        if(StringUtils.isEmpty(password)){
            return setResultError("password cannot be empty");
        }
        //based on the phone number, find the user info from the database
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("phone_number", phoneNumber);
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        if(sysUser==null){
            return setResultError("phone number not registered");
        }
        //compare the password+salt
        String pwdMd5Inp = MD5Utils.md5(password + sysUser.getSalt());
        if(!pwdMd5Inp.equals(sysUser.getPassword())){
            return setResultError("password incorrect");
        }
        //generate the token
        String token = TokenUtils.getToken();
        RedisUtil.setString(token, sysUser.getId().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUserLoginLog sysUserLoginLog = new SysUserLoginLog(sysUser.getId(), IpUtil.getIp(request), new Date(), token, loginUserReqDto.getChannel(), loginUserReqDto.getEquipment());
        loginLogManage.asyncLoginLog(sysUserLoginLog);
        return setResultSuccessData(jsonObject);
    }
}
