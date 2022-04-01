# ForJava_Ben

开发环境
1. Windows10
2. jdk1.8.0_101

多人/终端 进行文件/文档进行简单的共同编辑 项目

本项目所用技术栈：
1. 前端：thymeleaf
2. 后端：Springboot+WebSocket+RMI

（代码中还包含RabbitMQ的部分代码，这是因为原本打算利用RabbitMQ来实现消息的传输，但后来发现WebSocket比较容易上手，所以转用WebSocket）

本项目实现功能：
1. 登录登出功能及登录界面；
2. 共享文档编辑功能及编辑界面；
3. 在线人数统计及显示功能；
4. 清屏功能
5. 显示时间功能
6. 保存文件功能

本次开发顺序是：
1. 首先是创建Springboot项目，添加依赖，配置pom文件（但有些依赖暂时还没什么用）；
2. 利用WebSocket来实现文档信息的传输，同时采用Springboot提供的前端模板引擎进行登陆界面及文档编辑界面的实现；
3. 利用RMI来构建服务端和客户端，实现远程服务调用功能来获取当前时间并插入文档中。

本项目源代码下载地址：
BenSuslo/ForJava_Ben at online_document_MW
