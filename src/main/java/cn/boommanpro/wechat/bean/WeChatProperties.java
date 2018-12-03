package cn.boommanpro.wechat.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BoomMan
 */
@Data
@ConfigurationProperties( prefix = "wx")
public class WeChatProperties {
    private String appId;
    private String secret;
}
