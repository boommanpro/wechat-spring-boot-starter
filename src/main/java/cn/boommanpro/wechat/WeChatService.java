package cn.boommanpro.wechat;


import cn.boommanpro.wechat.bean.Code2Session;
import cn.boommanpro.wechat.bean.WeChatAccountInfo;
import cn.boommanpro.wechat.bean.WeChatDecryptForm;
import cn.boommanpro.wechat.bean.WeChatLoginForm;
import cn.boommanpro.wechat.bean.WeChatProperties;
import cn.boommanpro.wechat.exception.WeChatDecryptException;
import cn.boommanpro.wechat.util.CommonUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

/**
 * @author BoomMan  59232311@qq.com  WeChatService
 */
@Log4j2
public class WeChatService {
    private final static String AES = "AES";

    private final String webAccessTokenHttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Autowired
    private WeChatProperties weChatProperties;

    static {

        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 核心方法
     *
     * @param weChatLoginForm 请求地址
     * @return 返回用户信息
     */
    public WeChatAccountInfo decryptWeChatLoginForm(WeChatLoginForm weChatLoginForm) {
        Code2Session code2Session = decryptWeChatLoginCodeSession(weChatLoginForm.getCode());
        WeChatDecryptForm weChatDecryptForm = new WeChatDecryptForm(weChatLoginForm.getEncryptedData(), weChatLoginForm.getIv(), code2Session.getSessionKey());
        WeChatAccountInfo weChatAccountInfo = JSON.parseObject(decrypt(weChatDecryptForm), WeChatAccountInfo.class);
        weChatAccountInfo.setOpenId(code2Session.getOpenId());
        weChatAccountInfo.setUnionId(code2Session.getOpenId());
        return weChatAccountInfo;
    }

    public Code2Session decryptWeChatLoginCodeSession(String code) {
        String requestUrl = getWebAccess(code);
        Code2Session code2Session = CommonUtil.httpsRequest(requestUrl, "GET", null).toJavaObject(Code2Session.class);
        if (code2Session.getErrcode() != Code2Session.SUCCESS) {
            log.error("解密用户数据异常: from code:{}, errorCode:{}, code2Session:{}", code, code2Session.getErrcode(), code2Session.getErrmsg());
            throw new WeChatDecryptException(String.format("code:%s, errmsg:%s", code2Session.getErrmsg(), code2Session.getErrmsg()));
        }
        return code2Session;
    }


    private String getWebAccess(String code) {
        return String.format(webAccessTokenHttps,
                weChatProperties.getAppId(),
                weChatProperties.getSecret(),
                code);
    }


    private String decrypt(WeChatDecryptForm weChatDecryptForm) {
        //对传入信息进行处理
        String iv = weChatDecryptForm.getIv().replaceAll(" ", "+");
        String encryptedData = weChatDecryptForm.getEncryptedData().replaceAll(" ", "+");
        String sessionKey = weChatDecryptForm.getSessionKey().replaceAll(" ", "+");
        Base64.Decoder decoder = Base64.getDecoder();
        try {

            byte[] content = decoder.decode(encryptedData.getBytes(StandardCharsets.UTF_8));
            byte[] keyByte = decoder.decode(sessionKey.getBytes(StandardCharsets.UTF_8));
            byte[] ivByte = decoder.decode(iv.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec sKeySpec = new SecretKeySpec(keyByte, AES);
            AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            byte[] decryptBytes = cipher.doFinal(content);
            if (null != decryptBytes && decryptBytes.length > 0) {
                return new String(decryptBytes, StandardCharsets.UTF_8);
            }
            return null;
        } catch (NoSuchAlgorithmException | NoSuchProviderException | IllegalBlockSizeException |
                 InvalidParameterSpecException | BadPaddingException | NoSuchPaddingException |
                 InvalidAlgorithmParameterException | InvalidKeyException e) {
            return e.toString();
        }
    }


}
