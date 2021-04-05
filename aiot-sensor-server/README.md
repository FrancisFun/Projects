# AIOT-SENORT-SERVER

## 项目概述

本项目是AIoT项目中用于处理传感器数据的服务端，从消息队列中获取消息并进行解析，解析后存储数据到数据库中。



## 项目结构

具体项目结构如下所示。

```java
└─src
    ├─main
      ├─java
      │  └─com
      │      └─aiot
      │          └─sensor
      │              ├─equipment		// 设备
      │              │  ├─dao			// 数据库访问类
      │              │  ├─entity		// 实体类
      │              │  └─service		// service接口
      │              │      └─impl		// service实现类
      │              ├─home				// 概览
      │              │  ├─dao			// 数据库访问类
      │              │  ├─entity		// 实体类
      │              │  └─service		// service接口
      │              │      └─impl		// service实现类
      │              ├─message			// 消息
      |              │  ├─entity		// 消息实体类
      │              ├─statistics		// 数据
      │              │  ├─dao			// 数据库访问类
      │              │  ├─entity		// 实体类
      │              │  └─service		// service接口
      │              │      └─impl		// service实现类
      │              └─util				// 工具类
      └─resources
          └─mapper						// mapper文件
```

