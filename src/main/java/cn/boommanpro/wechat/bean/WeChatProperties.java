package cn.boommanpro.wechat.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BoomMan
 * @mail 59232311@qq.com
 * @CreateTime 2018/12/1 14:34
 * @Version 1.0
 * @Description TODD
 */
@Data
@ConfigurationProperties( prefix = "wx")
public class WeChatProperties {
    private String appId;
    private String secret;
}
