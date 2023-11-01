package hk.hku.main.api.dto.res;

import hk.hku.common.core.api.BaseApiService;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.common.core.utils.DesensitizationUtil;
import hk.hku.common.core.utils.RedisUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 余胜军
 * @ClassName SysUserResDto
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@Data
@ApiModel(value = "返回用户信息", description = "返回用户信息")

public class UserResDto {
    @ApiModelProperty("用户ID")
    private Integer id;


    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("登陆名称")
    private String userName;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phoneNumber;

    @ApiModelProperty("用户性别（0男 1女 2未知）")
    private String sex;

    @ApiModelProperty("头像地址")
    private String avatar;


    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;


    @ApiModelProperty("最后登录IP")
    private String loginIp;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime loginDate;
    public String getPhoneNumber() {
        return DesensitizationUtil.mobileEncrypt(this.phoneNumber);
    }
}

