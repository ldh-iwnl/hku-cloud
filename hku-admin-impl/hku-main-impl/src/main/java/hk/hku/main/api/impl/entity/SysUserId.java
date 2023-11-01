package hk.hku.main.api.impl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 余胜军
 * @ClassName SysUserId
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@Data
@TableName("sys_user_id")
public class SysUserId {
    @TableId(type = IdType.AUTO)
    private Integer userId;
}