源自[Struts 2 + Hibernate integration example](https://www.mkyong.com/struts2/struts-2-hibernate-integration-example/)

### 环境搭建

Java 1.8.0_45, MySQL 5.5.24, Apache Maven 3.3.3

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

### 中文命名

改数据库表与字段名成功:

```sql
DROP TABLE IF EXISTS `演示`.`顾客`;
CREATE TABLE  `演示`.`顾客` (
  `顾客_ID` bigint(20) unsigned COLLATE utf8_unicode_ci NOT NULL AUTO_INCREMENT,
  `姓名` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `地址` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `创建日期` datetime COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`顾客_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;
```

```xml
<hibernate-mapping>
    <class name="com.mkyong.customer.model.Customer"
        table="顾客" catalog="演示">
        <id name="customerId" type="java.lang.Long">
            <column name="顾客_ID" />
            <generator class="identity" />
        </id>
        <property name="name" type="string">
            <column name="姓名" length="45" not-null="true" />
        </property>
        <property name="address" type="string">
            <column name="地址" not-null="true" />
        </property>
        <property name="createdDate" type="timestamp">
            <column name="创建日期" length="19" not-null="true" />
        </property>
    </class>
</hibernate-mapping>
```

#### 未解决问题

修改模型属性名`name`->`姓名`后, 未改`Customer`的get/set方法名和JSP的对应属性名:
```
java.lang.NullPointerException
    com.mkyong.customer.action.CustomerAction.listCustomer(CustomerAction.java:71)
```

添加记录时报错:
```
 org.hibernate.PropertyValueException: not-null property references a null or transient value: com.mkyong.customer.model.Customer.?? 
```

将请求内容设置为UTF-8格式, 原本为ISO-xxxx. 参考[这里](https://stackoverflow.com/questions/12220483/how-to-change-charset-in-struts2-to-utf-8)

struts2-core 从2.1.5升级到2.3.37仍报错:
```
org.hibernate.PropertyValueException: not-null property references a null or transient value: com.mkyong.customer.model.Customer.姓名
```
不确定是否有关:
```
14413 [http-nio-8080-exec-95] WARN  com.opensymphony.xwork2.interceptor.ParametersInterceptor  - Parameter [姓名] didn't match accepted pattern [[\w+((\.\w+)|(\[\d+\])|(\(\d+\))|(\['(\w|[\u4e00-\u9fa5])+'\])|(\('(\w|[\u4e00-\u9fa5])+'\)))*]]!
```
 
### 其他

- Struts 2.3.37升级到2.5.20, 参考[官方文档](https://cwiki.apache.org/confluence/display/WW/Struts+2.3+to+2.5+migration)
- 添加日志, 参考[这里](https://stackoverflow.com/questions/12532339/no-appenders-could-be-found-for-loggerlog4j)