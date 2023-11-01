package hk.hku.main.api.impl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kyleli
 * @since 2023-10-31
 */
@TableName("sys_user_login_log")
@ApiModel(value = "SysUserLoginLog对象", description = "")
@Data
public class SysUserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;


    public SysUserLoginLog() {
    }

    public SysUserLoginLog(Integer userId, String loginIp, Date loginTime, String loginToken, String channel, String equipment) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginToken = loginToken;
        this.channel = channel;
        this.equipment = equipment;
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String loginIp;

    private Date loginTime;

    private String loginToken;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("设备信息")
    private String equipment;

    private Integer isDelete;


}
