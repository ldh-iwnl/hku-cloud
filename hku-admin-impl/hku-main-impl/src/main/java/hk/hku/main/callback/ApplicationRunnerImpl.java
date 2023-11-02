package hk.hku.main.callback;


import hk.hku.common.core.utils.DesensitizationUtil;
import hk.hku.main.api.impl.entity.SysUser;
import hk.hku.main.api.impl.entity.SysUserLoginLog;
import hk.hku.main.container.AsyncLogWrapper;
import hk.hku.main.container.HkuLogContainer;
import hk.hku.main.manage.LoginLogManage;
import hk.hku.main.manage.WechatTemplateManage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * springboot启动成功之后 回调执行方法
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Value("${hkuthreadpool.log.threads}")
    private Integer mayiktLogThreads;
    @Value("${hkuthreadpool.log.threadName}")
    private String threadName;
    @Autowired
    private LoginLogManage loginLogManage;

    @Autowired
    private HkuLogContainer HkuLogContainer;

    @Autowired
    private WechatTemplateManage wechatTemplateManage;

    /**
     * springboot启动完成之后走到该回调方法
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("<springboot启动成功....>");
        initLogThread();
    }

    private void initLogThread() {
        for (int i = 0; i < mayiktLogThreads; i++) {
            // 一定要指定线程名称 为了避免在生产环境中发生了cpu飙高的问题
            new Thread(new LogThread(), threadName).start();
            // 开始启动异步登录后续线程
        }
    }

    class LogThread implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                /**
                 * 作业题  批量的形式获取队列中 数据 改成批量插入数据
                 * 周六完成 整合spring批量
                 */
                AsyncLogWrapper asyncLogWrapper = HkuLogContainer.getAsynLogWrapperDeque().poll();
                if (asyncLogWrapper != null) {
                    // 登录后续流程处理 写入登录日志
                    SysUserLoginLog sysUserLoginLog = asyncLogWrapper.getSysUserLoginLog();
                    loginLogManage.asyncLoginLog(sysUserLoginLog);
                    SysUser sysUser = asyncLogWrapper.getSysUser();

                    wechatTemplateManage.sendLoginTemplate("oItn76T_CGm6YH7AdFzJNgkW_lIY",
                            DesensitizationUtil.mobileEncrypt(sysUser.getPhoneNumber()),
                            sysUserLoginLog.getLoginTime(),
                            sysUserLoginLog.getLoginIp(),
                            sysUserLoginLog.getEquipment());
                }
                // 为了避免空转问题 导致cpu飙高
                Thread.sleep(30);
            }
        }
    }


}
