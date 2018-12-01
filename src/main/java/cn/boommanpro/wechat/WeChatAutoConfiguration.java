package cn.boommanpro.wechat;


import cn.boommanpro.wechat.annotation.EnableWeChat;
import cn.boommanpro.wechat.bean.WeChatProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

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
public class WeChatAutoConfiguration implements ImportAware {


    @Bean
    @ConditionalOnMissingBean
    WeChatService weChatService() {
        return new WeChatService();
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        Map<String, Object> attributeMap = importMetadata.getAnnotationAttributes(EnableWeChat.class.getName());
    }
}
