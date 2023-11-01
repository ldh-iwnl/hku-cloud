package hk.hku.main.api.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import hk.hku.common.core.api.BaseApiService;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.common.core.utils.MeiteBeanUtils;
import hk.hku.common.core.utils.RedisUtil;
import hk.hku.main.api.UserInfoService;
import hk.hku.main.api.dto.res.UserResDto;
import hk.hku.main.api.impl.entity.SysUser;
import hk.hku.main.api.impl.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kyleli
 * @ClassName UserInfoServiceImpl
 */
@RestController
public class UserInfoServiceImpl extends BaseApiService implements UserInfoService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public BaseResponse<UserResDto> getUserInfoByToken(String token) {
        if(StringUtils.isEmpty(token)){
            return setResultError("Token cannot be empty");
        }
        String redisUserId = RedisUtil.getString(token);
        if(StringUtils.isEmpty(redisUserId)){
            return setResultError("Token invalid");
        }
        Integer userId = Integer.parseInt(redisUserId);
        SysUser sysUser = sysUserMapper.selectById(userId);
        if(sysUser==null){
            return setResultError("User not found");
        }
        UserResDto userResDto = MeiteBeanUtils.doToDto(sysUser, UserResDto.class);
        return setResultSuccessData(userResDto);
    }
}
