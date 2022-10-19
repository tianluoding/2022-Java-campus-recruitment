# docker教程

docker容器化技术

## docker架构

* clients
* hosts
* registries

1. image
2. 容器
3. 仓库

配置docker镜像加速，默认从docker hub下，中科大 阿里云等

## docker命令

1. docker服务相关命令

2. docker镜像相关命令

   查看镜像 搜索镜像 拉取镜像 删除镜像

3. docker容器相关命令

   查看容器 创建容器 进入容器 启动容器 停止容器 删除容器 查看容器信息

   * `docker run -it`  `docker run -id`
   * `docker ps` `docker ps -a`
   * `docker exec`
   * `docker stop`
   * `docker start`
   * `docker rm`
   * `docker inspect`

## docker容器的数据卷

* 数据卷概念及作用
* 配置数据卷
* 配置数据卷容器

### 数据卷概念

* docker容器删除后，在容器中产生的数据还在吗？
* docker容器和外部机器可以直接交换文件吗？
* 容器之间想要进行数据交互？

数据卷是宿主机中的一个目录或文件，当容器目录和数据卷目录绑定之后，对方的修改会立即同步。

一个数据卷可以被多个容器同时挂载，一个容器也可以挂载多个数据卷。

作用：

1. 容器数据持久化
2. 外部机器和容器间接通信
3. 容器之间数据交换

### 配置数据卷卷

* 创建容器时，使用-v参数 设置数据卷

  ```dockerfile
  docker run ... -v 宿主机目录:容器内目录 
  ```

  必须写绝对路径

## 数据卷容器

多容器进行数据交换

1. 多个容器挂载在同一个数据卷
2. 数据卷容器

配置数据卷容器

1. 创建数据卷容器

   `docker run it --name=c3 -v /volume ...`

2. 创建容器，设置数据卷

   `docker run -it --name=c1 --volumes-from c3 ...`

## 部署常用应用

* 容器内的网络服务和外部机器不能直接通信
* 外部机器和宿主机可以通信
* 宿主机和容器可以直接通信

端口映射

### mysql部署

需要端口映射、目录映射

```
-v /conf:/etc/mysql/conf.d -v /logs:/logs -v /data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 mysql:5.6
```

### Tomcat部署

![image-20221019155718502](C:\Users\tld\AppData\Roaming\Typora\typora-user-images\image-20221019155718502.png)

## dockerfile

### docker镜像原理

docker镜像本质是什么？

docker镜像为什么这么小？

* docker镜像是由特殊的文件系统叠加而成
* 最低端是bootfs，并使用宿主机的bootfs
* 第二次是root文件系统rootfs，称为base image
* 然后再往上可以叠加其他的镜像文件
* 同一文件系统技术，将不同的层整合成一个文件系统
* 一个镜像可以防止另一个镜像的上面。位于下面的镜像称为父镜像，最底层的镜像成为基础镜像

复用

### 镜像制作

1. 容器转为镜像

   `docker commit 容器id 镜像名称:版本号`

   `docker save -o 压缩文件 ...`

   `docker load -i 压缩文件名称`

2. dockerfile

   * dockerfile是一个文本文件
   * 包含了一条条的指令
   * 每一条指令构建一层，基于基础镜像，最终构建出一个新的镜像
   * 对于开发人员：可以为开发团队提供一个完全一致的开发环境

   `docker build -f ./springboot_dockerfile -t app .`

## Docker服务编排

服务编排

微服务架构的应用系统中一般包含多个微服务，每个服务一般都会部署多个实例，如果每个微服务都要手动启动，非常麻烦。