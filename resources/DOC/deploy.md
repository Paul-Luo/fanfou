
## 指定容器的时间
docker run -d --name="fanfou-1.0" -p 80:8080 -v /home/centos/app:/usr/local/tomcat/webapps -e "TZ=Asia/Shanghai" tomcat:7.0.63-jre7
