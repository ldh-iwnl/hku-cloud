package hk.hku.main.api.impl.manage;

import hk.hku.main.api.impl.entity.SysUserLoginLog;
import hk.hku.main.api.impl.mapper.SysUserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
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

    @Async("taskExecutor")
    public void asyncLoginLog(SysUserLoginLog sysUserLoginLog){
        sysUserLoginLog.setIsDelete(0);
        int insert = sysUserLoginLogMapper.insert(sysUserLoginLog);
        log.info("insert login log result:{}", insert);
    }
}
