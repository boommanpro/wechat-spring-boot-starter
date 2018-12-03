
在spring boot 项目中配置 application.properties
```

#小程序ID
wx.appId=

#小程序密钥
wx.secret=

#获取Web_access_tokenhttps的请求地址 默认不用修改
wx.webAccessTokenHttps=https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code

```

现在采用@EnableWeChat开启WeChat服务

正在为上传到Maven仓库做准备