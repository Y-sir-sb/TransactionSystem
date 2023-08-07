FROM Centos:8 AS builder
#安装jdk 数据库Mysql
RUN dnf -y update && \
        dnf -y install java-17-openjdk-devel maven \
# 设置环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH=$JAVA_HOME/bin:$PATH
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
# 设置工作目录
WORKDIR /TransactionSystem
# 复制项目的 pom.xml 文件
COPY pom.xml .
# 下载项目的依赖项
RUN mvn dependency:go-offline -B
# 复制整个项目到容器中
COPY src ./src
# 使用一个新的基础镜像，只包含 Java 运行时环境
FROM adoptopenjdk:17-jre-hotspot
# 设置工作目录
WORKDIR /TransactionSystem

# 复制构建好的 JAR 文件到容器中
COPY --from=builder /happyPlanet/target/TransactionSystem.jar .

# 暴露应用程序的端口号
EXPOSE 8080

# 定义容器启动时的默认命令
CMD ["java", "-jar", "TransactionSystem.jar"]
