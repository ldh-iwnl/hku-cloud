package hk.hku.main.api.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import hk.hku.main.api.impl.entity.SysUserId;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author 余胜军
 * @ClassName SysUserIdMapper
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
public interface SysUserIdMapper extends BaseMapper<SysUserId> {


    @Insert("INSERT INTO sys_user_id    VALUES(null);")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int getUserId(SysUserId sysUserId);

}