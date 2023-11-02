package hk.hku.main.manage;


import hk.hku.common.core.utils.SimpleDateFormatUtils;
import hk.hku.main.wechat.config.WxMpConfiguration;
import hk.hku.main.wechat.config.WxMpProperties;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 余胜军
 * @ClassName WechatTemplate
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@Component
@Slf4j
public class WechatTemplateManage {
    @Value("${hkuthreadpool.wx.main.login.templateId}")
    private String loginTemplateId;
    @Autowired
    private WxMpProperties wxMpProperties;

    /**
     * 发送登录模板
     */
    public boolean sendLoginTemplate(String openId, String phone, Date loginTime, String loginIp, String equipment) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(loginTemplateId);
        wxMpTemplateMessage.setToUser(openId);
        List<WxMpTemplateData> data = new ArrayList<>();
        data.add(new WxMpTemplateData("first", phone));
        data.add(new WxMpTemplateData("keyword1", SimpleDateFormatUtils.getFormatStrByPatternAndDate(loginTime)));
        data.add(new WxMpTemplateData("keyword2", loginIp));
        data.add(new WxMpTemplateData("keyword3", equipment));
        wxMpTemplateMessage.setData(data);
        try {
            String appId = wxMpProperties.getConfigs().get(0).getAppId();
            WxMpTemplateMsgService templateMsgService = WxMpConfiguration.getMpServices().get(appId).getTemplateMsgService();
            String result = templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
            log.info("result:{}", result);
            return true;
        } catch (Exception e) {
            log.error("e:{}", e);
            return false;
        }
    }
}