package cn.boommanpro.wechat.annotation;

import cn.boommanpro.wechat.WeChatAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author BoomMan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WeChatAutoConfiguration.class)
@Configuration
public @interface EnableWeChat {
}
