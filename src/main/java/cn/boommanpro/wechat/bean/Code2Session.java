package cn.boommanpro.wechat.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BoomMan 59232311@qq.com
 *  code2Session 官方文档地址:
 *  https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/code2Session.html
 */
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Code2Session {

    public static final int SUCCESS= 0;

    private int errcode;

    private Integer errmsg;

    @JSONField(name = "unionid")
    private String unionId;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "openid")
    private String openId;

}
