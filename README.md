源自[Struts 2 + Hibernate integration example](https://www.mkyong.com/struts2/struts-2-hibernate-integration-example/)

### 环境搭建

1. 构建生成WAR包: `$ mvn install`
2. 拷贝到tomcat的webapps下
3. 启动tomcat

#### 遇到的问题

1. catalina.out中只有此错误:
```
org.apache.catalina.core.StandardContext.startInternal One or more listeners failed to start. Full details will be found in the appropriate container log file
```
参考[这里](https://stackoverflow.com/questions/48639816/tomcat-one-or-more-listeners-failed-to-start), 在`src/main/resources`下添加logging.properties. 之后重启

2. 日志中看到错误: `java.lang.NoClassDefFoundError: org/slf4j/impl/StaticLoggerBinder`
参考[这里](https://www.mkyong.com/wicket/java-lang-classnotfoundexception-org-slf4j-impl-staticloggerbinder/), 在`pom.xml`添加`slf4j`依赖. 另[官方文档](https://www.slf4j.org/codes.html#StaticLoggerBinder)

3. 启动成功后, 访问[网页](http://localhost:8080/Struts2Example/listCustomerAction)时报错: `No suitable driver found for jdbc:mysql://localhost:3306/...`

参考[Tomcat does not recognize the MySQL .jar library](https://stackoverflow.com/a/26704870/1536803)
- 添加mysql-connector-java-5.1.9.jar到`apache-tomcat-8.5.16/lib/`
- 添加`bin/setenv.sh`, 内容:
```
CLASSPATH=lib/mysql-connector-java-5.1.9.jar:$CLASSPATH
```
- 重启Tomcat