package hk.hku.main.api.impl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author kyleli
 * @since 2023-10-30
 */
@TableName("sys_user")
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String deptId;

    private String name;

    private String userName;

    private String email;

    private String phoneNumber;

    private String sex;

    private String avatar;

    private String password;

    private String salt;

    private Date birthday;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private Date createTime;

    private String updateBy;

    private String remark;

    private Integer version;

    public SysUser() {
    }
    public SysUser(String phoneNumber, String password, String salt) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.salt = salt;
    }
}
