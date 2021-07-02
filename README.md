# DBAPP

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
```

###teglib
```jstl
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```