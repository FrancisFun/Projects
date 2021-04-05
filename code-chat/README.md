# CodeChat

## 项目概述

本软件属于即时通讯类软件，其实现的基本功能有：添加好友、聊天（发送文字、表情、图片、文件等）。在此基础上，针对当前主流聊天软件代码发送体验差的问题，我们在发送、显示及编辑代码方面都进行了优化。

在发送代码时，可选择代码语言，针对不同的语言，发送编辑框将自动选择不同的高亮方案，方便用户查看自己的代码。

在收到代码消息时，在消息气泡中将会对代码进行高亮显示，并可通过消息气泡旁的按钮，实现代码的在线编译运行与再次编辑，方便用户进行运行结果的查看及修改代码。

在进行代码编辑时将切换到代码编辑器页面，编辑器可同时编辑多份代码，并通过相应的标签进行切换，代码编辑器将提供代码高亮与基于上下文的智能提示功能，提升代码编辑的效率。对修改完成后的代码亦可以重新发送给好友或在线编译运行。



## 项目结构

CodeChat解决方案内含有五个项目。CAC.client为CodeChat桌面端项目，CodeChatSDK为SDK项目，MonacoEditorComponent为代码编辑器项目，RichTextControls为富文本编辑框项目，SycEditControllerLibrary为同步编辑项目（未完成）。

具体项目结构如下所示。

```c#
├─CAC.client                    // 客户端项目
│  ├─Assets                     // 资源
│  │  ├─Code                    // 代码资源
│  │  ├─fonts                   // 字体资源
│  │  └─icons                   // 图标资源
│  ├─Common                     // 常用类
│  ├─Converters                 // 转换器类
│  ├─CustomControls             // 自定义控件
│  ├─Global                     // 全局类
│  ├─Pages                      // 页面
│  │  ├─AvatarPage              // 头像页面
│  │  ├─CodeEditorPage          // 代码编辑器页面
│  │  ├─ContactPage             // 通讯录页面
│  │  │  ├─ContactDetail        // 联系人详情
│  │  │  └─ContactList          // 联系人列表
│  │  ├─LoginPage               // 注册页面
│  │  ├─MessagePage             // 消息页面
│  │  │  ├─ChatList             // 聊天列表
│  │  │  ├─ChatPanelPage        // 聊天面板
│  │  │  ├─MessageBubbles       // 消息气泡
│  │  │  └─MessageViewer        // 消息查看器
│  │  ├─SettingsPage            // 设置页面
│  │  └─SignupPage              // 注册页面
│  └─Properties                 // 属性文件
├─CodeChatSDK                   // SDK项目(*)
│  ├─Client                     // 客户端类(*)
│  ├─Controllers                // 控制器类(*)
│  ├─EventHandler               // 事件处理器类(*)
│  │  ├─Account                 // 帐户事件(*)
│  │  ├─Disconnect              // 未连接事件(*)
│  │  ├─Login                   // 登陆事件(*)
│  │  ├─Message                 // 消息事件(*)
│  │  ├─Register                // 注册事件(*)
│  │  ├─Subscriber              // 订阅者事件(*)
│  │  └─Topic                   // 话题事件(*)
│  ├─Models                     // 模型类(*)
│  ├─Pbx                        // PBX生成类
│  ├─Repository                 // 仓库接口(*)
│  │  └─Sqlite                  // SQLite实现类(*)
│  └─Utils                      // 工具类(*)
├─doc                           // 项目文档
│  └─etc
├─MonacoEditorComponent         // 代码编辑器项目
├─RichTextControls              // 富文本编辑框项目
└─SycEditControllerLibrary      // 同步编辑项目
```

备注：`(*)`为本人负责部分。