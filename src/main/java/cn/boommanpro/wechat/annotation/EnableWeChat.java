package cn.boommanpro.wechat.annotation;

import cn.boommanpro.wechat.WeChatAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author BoomMan
 * @mail 59232311@qq.com
 * @CreateTime 2018/12/1 17:32
 * @Version 1.0
 * @Description TODD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WeChatAutoConfiguration.class)
@Configuration
public @interface EnableWeChat {
}
