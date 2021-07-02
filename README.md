# DBAPP ~

###데이터베이스 생성 방법
```sql
CREATE USER 'korea'@'%' IDENTIFIED BY 'korea1234';
GRANT ALL PRIVILEGES ON *.* TO 'korea'@'%';
CREATE database koreadb;
```

###추가 의존성
```xml
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jasper</artifactId>
    <version>9.0.46</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
	<version>1.2</version>
</dependency>
```

###JSTL teglib
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### application.yml 
```yml
server:
 port: 8000
 servlet:
    encoding:
      charset: UTF-8
      

spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    username: korea
    password: korea1234
    url: jdbc:mysql://localhost:3306/koreadb      
    
    
  jpa:
    hibernate:
      ddl-auto: none #create:, update, none
    show-sql: true
```

