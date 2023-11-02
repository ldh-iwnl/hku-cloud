package hk.hku.main.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hk.hku.common.core.utils.RedisUtil;
import hk.hku.main.api.impl.entity.SysUserLoginLog;
import hk.hku.main.api.impl.mapper.SysUserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author kyleli
 * @ClassName LoginLogManage
 */

@Component
@Slf4j
public class LoginLogManage {

    @Autowired
    private SysUserLoginLogMapper sysUserLoginLogMapper;

//    @Async("taskExecutor")
    public void asyncLoginLog(SysUserLoginLog sysUserLoginLog){
        //search the log database
        QueryWrapper<SysUserLoginLog> sysUserLoginLogQueryWrapper = new QueryWrapper<SysUserLoginLog>();
        sysUserLoginLogQueryWrapper.eq("user_id", sysUserLoginLog.getUserId());
        sysUserLoginLogQueryWrapper.eq("is_delete", 0);
        SysUserLoginLog sysUserLoginLog1 = sysUserLoginLogMapper.selectOne(sysUserLoginLogQueryWrapper);
        //delete old redis
        String loginToken = null;
        if(sysUserLoginLog1!=null){
            //acquire last login token
            loginToken = sysUserLoginLog1.getLoginToken();
            // set last login entry to be deleted
            sysUserLoginLog1.setIsDelete(1);
            //update the database
            sysUserLoginLogMapper.updateById(sysUserLoginLog1);
        }
        sysUserLoginLog.setIsDelete(0);
        int insert = sysUserLoginLogMapper.insert(sysUserLoginLog);
        log.info("insert login log result:{}", insert);
        if(insert>0 && !StringUtils.isEmpty(loginToken)) {
            log.info("deleting redis login token:{}", loginToken);
            RedisUtil.delKey(loginToken);
        }
        // send msg using async
        // only login
    }
}
