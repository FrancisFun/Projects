# AIoT-Web-Server
## 项目概述

本项目为AIoT项目中的网页管理系统服务端。

为网页端提供登陆、注册、查询数据、查询设备状态、更改设备状态、管理用户、管理个人信息等API接口。

服务端采用JWT+Spring Security框架进行鉴权。



## 项目结构

具体项目结构如下所示。

```java
└─src
    ├─main
       ├─java
       │  └─com
       │      └─aiot
       │          └─web
       │              ├─auth                // 鉴权
       │              │  ├─filter           // 过滤器类
       │              │  └─service          // service
       │              ├─equipment           // 设备
       │              │  ├─controller       // 控制器类
       │              │  ├─dao              // 数据库访问类
       │              │  ├─entity           // 实体类
       │              │  └─service          // service接口
       │              │      └─impl         // service实现类
       │              ├─file                // 文件
       │              │  ├─controller       // 控制器类
       │              │  └─entity           // 实体类
       │              ├─home                // 农场概览
       │              │  ├─controller       // 控制器类
       │              │  ├─dao              // 数据库访问类
       │              │  ├─entity           // 实体类
       │              │  └─service          // service接口
       │              │      └─impl         // service实现类
       │              ├─statistics          // 统计
       │              │  ├─controller       // 控制器类
       │              │  ├─dao              // 数据库访问类
       │              │  ├─entity           // 实体类
       │              │  └─service          // service接口
       │              │      └─impl         // service实现类   
       │              ├─user                // 用户
       │              │  ├─controller       // 控制器类
       │              │  ├─dao              // 数据库访问类
       │              │  ├─entity           // 实体类
       │              │  └─service          // service接口
       │              │      └─impl         // service实现类
       │              └─util                // 工具类
       └─resources
           └─mapper                         // mapper文件
```

