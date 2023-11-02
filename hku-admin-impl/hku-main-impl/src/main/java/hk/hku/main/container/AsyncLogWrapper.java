package hk.hku.main.container;

import hk.hku.main.api.impl.entity.SysUser;
import hk.hku.main.api.impl.entity.SysUserLoginLog;
import lombok.Data;

/**
 * @author kyleli
 * @ClassName AsyncLogWrapper
 */
@Data
public class AsyncLogWrapper {
    private SysUserLoginLog sysUserLoginLog;
    private SysUser sysUser;

    public AsyncLogWrapper() {
    }

    public AsyncLogWrapper(SysUserLoginLog sysUserLoginLog, SysUser sysUser) {
        this.sysUserLoginLog = sysUserLoginLog;
        this.sysUser = sysUser;
    }
}
