
在spring boot 项目中配置 application.properties




1. 导入相关jar

```
<dependency>
  <groupId>cn.boommanpro</groupId>
  <artifactId>wechat-spring-boot-starter</artifactId>
  <version>1.0.2.RELEASE</version>
</dependency>
```

2. 配置微信参数: on application.properties


```

#小程序ID
wx.appId=

#小程序密钥
wx.secret=

```


3.开启相应注解
@EnableWechat

4.马上使用它
```
@Autowired
private WeChatService weChatService;
```


有任何问题可以提出

if you have any question ,please pull issue or contact  my mail 592323211@qq.com


## 写在最后

如果该项目帮到了你,请作者喝杯咖啡哈!

<img src="https://raw.githubusercontent.com/BoomManPro/java-interview/master/personal/Ali_Pay.jpg" width="350" hegiht="200" align=center />
<img src="https://raw.githubusercontent.com/BoomManPro/java-interview/master/personal/WeChat_Pay.jpg" width="400" hegiht="1000" align=center />






### 上传自己的jar到Maven中央仓库

[如何将自己的jar包上传到Maven仓库](https://blog.csdn.net/boom_man/article/details/84753028)

使用:
mvn clean deploy

不能使用 Lifecycle deploy

原因等待探究哈

一定要装gpg 并且 发布自身密匙到服务端

教程稍后写