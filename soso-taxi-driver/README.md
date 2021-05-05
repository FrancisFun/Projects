# SoSo-Taxi-Driver
## 项目概述

本项目为嗖嗖出行司机端，主要面向司机用户。提供听单、接单、导航、计费、评分、查看历史订单等功能。

针对司机用户的使用场景，我们在界面设计上采用了大字体，辅以强调色突出主要信息；使用滑动按钮避免误触；在功能上，我们采用了语言播报功能，可以大幅减少行驶途中查看手机的频率，提高乘客与司机的安全性。



## 项目结构

具体项目结构如下所示。

```java
├─app
│  ├─libs                                       // 第三方库
│  └─src
│      ├─main
│      │  ├─assets                              // 资源
│      │  ├─java
│      │  │  └─com
│      │  │      └─sosotaxi
│      │  │          └─driver
│      │  │              ├─adapter              // 适配器类
│      │  │              ├─application          // 应用类(*)
│      │  │              ├─common               // 通用类
│      │  │              ├─model                // 模型类
│      │  │              │  └─message           // 消息类
│      │  │              ├─service              // 服务类(*)
│      │  │              │  └─net               // 网络服务类(*)
│      │  │              ├─ui                   // 界面
│      │  │              │  ├─driverOrder       // 司机订单(*)
│      │  │              │  ├─home              // 主页
│      │  │              │  ├─login             // 登陆(*)
│      │  │              │  ├─main              // 主页面
│      │  │              │  ├─navigation        // 导航
│      │  │              │  ├─overlay           // 轨迹绘制(*)
│      │  │              │  ├─todayRoute        // 今日行程
│      │  │              │  ├─userInformation   // 用户信息
│      │  │              │  │  ├─order          // 订单
│      │  │              │  │  ├─personData     // 个人信息
│      │  │              │  │  └─wallet         // 钱包
│      │  │              │  └─widget            // 组件(*)
│      │  │              ├─utils                // 工具类(*)
│      │  │              └─viewModel            // ViewModel类(*)
│      │  └─res
│      │      ├─animator                        // 动画
│      │      ├─drawable                        // 图标
│      │      ├─layout                          // 布局
│      │      ├─menu                            // 菜单
│      │      ├─navigation                      // 导航栏
│      │      ├─values                          // 值配置
├─NaviTts                                       // 导航包
└─onsdk_all                                     // 导航包
```

