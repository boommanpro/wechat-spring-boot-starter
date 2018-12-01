package cn.boommanpro.wechat;


import cn.boommanpro.wechat.bean.WeChatProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BoomMan
 * @mail 59232311@qq.com
 * @CreateTime 2018/12/1 14:31
 * @Version 1.0
 * @Description TODD
 */
@Configuration
@ConditionalOnClass(WeChatService.class)
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "wx.enable",havingValue = "true")
    WeChatService weChatService(){
        return new WeChatService();
    }
}
