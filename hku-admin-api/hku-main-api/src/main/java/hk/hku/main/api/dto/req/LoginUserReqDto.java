package hk.hku.main.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 余胜军
 * @ClassName LoginUserResDto
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@Data
@ApiModel(value = "登录接口", description = "登录信息")
public class LoginUserReqDto {
    @ApiModelProperty("手机号码")
    private String phoneNumber;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("渠道来源")
    private String channel;
    @ApiModelProperty("设备信息")
    private String equipment;
}  
