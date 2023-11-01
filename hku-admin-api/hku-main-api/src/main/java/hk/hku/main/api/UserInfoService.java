package hk.hku.main.api;

import hk.hku.common.core.api.BaseResponse;
import hk.hku.main.api.dto.res.UserResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kyleli
 * @ClassName UserInfoService
 */
@Api(tags = "用户基本信息接口")

public interface UserInfoService {
    @GetMapping("/getUserInfoByToken")
    @ApiOperation(value = "查询用户信息", notes = "根据token查询用户信息")
    @ApiImplicitParam(paramType = "query", name = "token", value = "用户token", required = true)
    BaseResponse<UserResDto> getUserInfoByToken(String token);

}
