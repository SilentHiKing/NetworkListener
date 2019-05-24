### NetworkListener
通过注解监听网络变化的工具
### NetworkListener使用
- 添加依赖
 ```
 //在build.gradle
 implementation 'com.hiking.networklistener:networklistener:1.0.0'
 ```
- 全局初始化，如在Application中
 ```
  NetworkManager.getIns().init(this);
 ```
- 注册监听,以及解注册，具体见案例
 ```
  //注册
  NetworkManager.getIns().registerObserver(this);
  //只监听wifi有无，默认监听全部网络变化
  @Network（(type = NetType.WIFI)）
  public void aaa(String neyType) {}
  //解注册
  NetworkManager.getIns().unregisterObserver(this);
 ```
 ### 文档
 ![NetworkListener时序图](https://github.com/SilentHiKing/NetworkListener/blob/master/doc/NetworkListener%E6%97%B6%E5%BA%8F%E5%9B%BE.jpg)
