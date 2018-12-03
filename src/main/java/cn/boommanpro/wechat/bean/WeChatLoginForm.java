package cn.boommanpro.wechat.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author BoomMan 59232311@qq.com
 * 前端需要传递的解密数据
 */
@Getter
@Setter
@ToString
public class WeChatLoginForm {
    /**
     * 微信code
     **/
    private String code;
    /**
     * 用户加密信息
     **/
    private String encryptedData;
    /**
     * 加密算法的初始向量
     **/
    private String iv;
}
