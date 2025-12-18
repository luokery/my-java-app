# Java API Service Starter

This is a minimal Java API service starter based on [Google Cloud Run Quickstart](https://cloud.google.com/run/docs/quickstarts/build-and-deploy/deploy-java-service).

## Getting Started

Server should run automatically when starting a workspace. To run manually, run:

## maven方式运行 (maven run):

```sh
mvn spring-boot:run
mvn clean compile spring-boot:run -Pdev
mvn clean spring-boot:run -Plocal -Dspring.profiles.active=local
```

当要改变profiles运行: 

需要重新编译, 指定-Pdev 或者-Dspring.profiles.active=local

```sh
mvn clean compile spring-boot:run -Pdev
mvn clean spring-boot:run -Plocal -Dspring.profiles.active=local
```

Server should run automatically when starting a workspace. To run test, run:

## maven方式运行测试 (maven run test):

```sh
mvn clean test
mvn clean compile test-compile -Plocal
```
当要改变profiles运行: 

需要重新编译, 指定-Pdev 或者`` `-Dspring.profiles.active=local` ``

```sh
mvn clean compile test-compile -Plocal
```

## 软件包生成 (maven package):

### 生成本地环境的软件包:
```sh
mvn clean package -Plocal
mvn clean package -Dspring.profiles.active=test
```

## 运行软件:
### shell中运行(jar run)

一般来说直接运行:

```sh
java -jar demo-0.0.1-SNAPSHOT-prod.jar
```

```sh
java -jar demo-0.0.1-SNAPSHOT-local.jar
```

注意下面情形, 需要对应的配置文件. 否则使用application.yml中的配置.

```sh
java -jar demo-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=test
java -jar demo-0.0.1-SNAPSHOT.jar -Ptest
```
`内联代码`

### IDE中运行(IDE run):

```
启动: com.example.demo.DemoApplication
方式1.配置参数 program arguments: --spring.profiles.active=local
	这种方式必须config中有对应的配置文件, 如: application-local*.yml;
方式2.修改pom.xml中activeByDefault配置默认激活, 然后编译.
注意: 修改配置需要重新编译.
```

重新编译
指定-P或者-D

```
mvn clean compile -Pdev
```

**注**: 开发环境不切换profiles只是修改application-*.yml, 自动编译就可, 无需手动编译.

---

## 项目待办清单

### 设计阶段
- [x] profiles多环境配置,环境隔离
- [x] i18n
- [ ] 缓存


---

# **可能出现的问题排查**
## 1.编译相关
#### 1-1.运行缺配置: 

	编译时, pom中resource配置文件拷贝不全, 导致运行缺配置. 需要检查pom文件中resource配置.
	
#### 1-2.运行的时候配置不对: 

	目前使用maven多环境打包或者编译, 当前环境为最新编译的配置, 与命令行的-D -P指定profiles无关, 因为打包和编译的时候只拷贝了对应环境配置.
	例如: 打包编译的时候使用 mvn clean package -Plocal 运行的时候指定 -Ptest -Dspring.profiles.active=test 都是不生效的,控制台显示激活为test其实依然是local环境配置, 因为test配置文件并不存在于编译环境和jar包中.
