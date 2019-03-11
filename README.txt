前提条件安装Docker:
	# step 1: 安装必要的一些系统工具
	sudo yum install -y yum-utils device-mapper-persistent-data lvm2
	# Step 2: 添加软件源信息
	sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
	# Step 3: 更新并安装 Docker-CE
	sudo yum makecache fast
	sudo yum -y install docker-ce
	# Step 4: 开启Docker服务
	sudo service docker start
分布式文件系统安装：
1.拉取镜像docker pull morunchang/fastdfs

2.运行tracker服务
docker run -d --name tracker --net=host   morunchang/fastdfs sh tracker.sh

3.运行storage服务
docker run -d --privileged=true --name storage --net=host -e TRACKER_IP=39.107.106.219:22122 -v /opt/fastdfs/storage:/data/fast_data/data  -e GROUP_NAME=wangwei  morunchang/fastdfs sh storage.sh

+++
进入容器（docker exec -it storage /bin/bash）

storage启动时配置的http默认端口为8080，如需修改请进行如下操作
修改/etc/fdfs下边tracker.conf和tracker.conf.sample的http.server_port配置项和
修改 /etc/nginx/conf/nginx.conf 端口为需要的端口

注意开放端口： 22122  23000