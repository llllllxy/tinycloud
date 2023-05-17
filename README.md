## tinycloud微服务框架

### 服务模块说明
1. tinycloud-gateway     服务，网关（网关层统一路由转发，统一鉴权，只有网关层服务暴露给前端调用，其他均为内部服务）
2. tinycloud-common      公共组件jar包（公共配置和工具类等）
3. tinycloud-api         公共组件jar包（openfeign调用声明接口类）
4. tinycloud-bean        公共组件jar包（所有pojo的声明都存在此类，包括dto，entity，vo，param等）
5. tinycloud-user        服务，用户中心
6. tinycloud-ability     服务，能力中心
7. tinycloud-message     服务，消息中心
7. tinycloud-auth        服务，认证中心

### nacos说明
对应nacos版本1.4.2，docker中运行脚本如下
```
docker pull nacos/nacos-server:1.4.2

docker run -d -e MODE=standalone -e PREFER_HOST_MODE=hostname -e SPRING_DATASOURCE_PLATFORM=mysql -e MYSQL_SERVICE_HOST=10.110.34.93 -e MYSQL_SERVICE_PORT=3306 -e MYSQL_SERVICE_USER=root -e MYSQL_SERVICE_PASSWORD=Wladmin?93 -e MYSQL_SERVICE_DB_NAME=nacos_config -p 8848:8848 --name nacos1.4.2 --restart=always nacos/nacos-server:1.4.2

```

参数说明：
- 单节点模式 MODE=standalone
- 数据库地址 MYSQL_SERVICE_HOST
- 数据库用户名 MYSQL_SERVICE_USER
- 数据库密码 MYSQL_SERVICE_PASSWORD
- 需连接的数据库名称 MYSQL_SERVICE_DB_NAME
- 端口映射 -p 8848:8848
- 任意时候重启容器，开机就能自动启动容器（需设置docker为开机自启）–restart=always


### 服务模块和包名风格
#### tinycloud-common
- src\main\java\org\liuxingyu\tinycloud\common 存储公共组件封装

#### tinycloud-gateway
- src\main\java\org\liuxingyu\tinycloud\gateway 服务网关，负责路由转发，统一鉴权，流量控制

#### tinycloud-api
- src\main\java\org\liuxingyu\tinycloud\api 存储feign调用接口

#### tinycloud-bean
- src\main\java\com\liuxingyu\tinycloud\bean 存储公共POJO类
    - dto        存放DTO对象，用于服务间调用返回
    - entity     存放DO对象，和数据表一一对应
    - param      存放PARAM对象，用于服务间调用和前端调用传参
    - vo         存放VO对象，用于返回给web前端

#### tinycloud-***
- src\main\java\org\liuxingyu\tinycloud\*** 提供内部服务和前端web服务
    - constant   常量、枚举、自定义枚举错误码
    - controller 控制层，主要针对于前端web服务接口
    - provider   控制层，主要针对于服务内部调用接口(feign)
    - service    服务层
    - mapper     持久层
    - mq         消息队列
    - job        定时任务（基于xxl-job）


#### 技术栈

| 框架                        | 版本号         | 描述                                                         |
| -------------------------- | ------------- | ------------------------------------------------------------ |
| springcloud               | Hoxton.SR9    |                                                              |
| springcloud-alibaba       | 2.2.6.RELEASE |
| nacos                      | 1.4.2         | 配置中心、注册中心              |
| springboot                | 2.3.6.RELEASE |                                                              |
| openfeign                  | 2.2.6.RELEASE | 微服务接口调用组件                                           |
| mybatis-plus               | 3.5.3.1       | 增强MyBatis                                                             |
| druid                      | 1.2.16        |                                                              |
| redisson                   | 3.16.0        | redisson实现分布式锁                                         |
| jjwt                       | 0.9.1         | 生成jwt toke认证                                             | |
| .........                  |               |                                                              |

