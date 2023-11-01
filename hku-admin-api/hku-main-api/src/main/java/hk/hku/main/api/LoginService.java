package hk.hku.main.api;

import com.alibaba.fastjson.JSONObject;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.main.api.dto.req.LoginUserReqDto;
import io.swagger.annotations.*;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author kyleli
 * @ClassName LoginService
 */
@Api(tags = "login interface")
public interface LoginService {

    @PostMapping("/login")
    @ApiOperation(value = "login interface", notes = "login interface")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 500, message = "登录失败")})
    BaseResponse<JSONObject> login(@RequestBody LoginUserReqDto loginUserReqDto);

}
