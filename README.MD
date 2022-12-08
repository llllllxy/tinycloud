tinycloud微服务框架

tinycloud-gateway     服务，网关（网关层鉴权，还是分布到各个组件呢，分布到各个组件呢的话则需要tinycloud-auth-client组件）
tinycloud-common      公共组件jar包（公共方法等）
tinycloud-api         公共组件jar包（openfeign调用声明接口类）
tinycloud-user        服务，用户中心
tinycloud-ability     服务，能力中心
tinycloud-message     服务，消息中心


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
