package hk.hku.main.api;

import hk.hku.common.core.api.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kyleli
 * @ClassName RegisterService
 */

@Api(tags = "Register Interface")

public interface RegisterService {


    @PostMapping("register")
    @ApiOperation(value = "注册接口", notes = "根据手机号码、密码 实现注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phoneNumber", value = "手机号码", required = true),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true)
    })
    BaseResponse<String> register(String phoneNumber, String password);
}
