## FCB社区

## 资料
[Spring](https://spring.io/guides)  
[Spring web](https://spring.io/guides/gs/serving-web-content/)  
[Spring boot](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)  
[ES社区](https://elasticsearch.cn/)  
[Github Deploy Key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[Bootstrap](https://v3.bootcss.com/getting-started/)  
[Github OAuth](https://developer.github.com/apps/buiflding-oauth-apps/creating-an-oauth-app/)  
[菜鸟教程](https://www.runoob.com/mysql/mysql-tutorial.html)  
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)  
[Mybatis](http://www.mybatis.org/mybatis-3/configuration.html)  

## 工具
[Git](https://git-scm.com/downloads)  
[Visual Paradigm](https://www.visual-paradigm.com)  
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://www.projectlombok.org/)  


## 脚本
```sql
create table USER
(
	ID INTEGER auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	BIO VARCHAR(256),
	AVATAR_URL VARCHAR(100),
	constraint USER_PK
		primary key (ID)
);

create table QUESTION
(
	ID INTEGER auto_increment,
	TITLE VARCHAR(50),
	DESCRIPTION CLOB,
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	CREATOR INTEGER,
	COMMENT_COUNT INTEGER default 0,
	VIEW_COUNT INTEGER default 0,
	LIKE_COUNT INTEGER default 0,
	TAG VARCHAR(256),
	constraint QUESTION_PK
		primary key (ID)
);


```

```bash
mvn flyway:migrate
```


