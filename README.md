# Spring Boot MyBatis 

## 버전
- Spring Boot 1.3.0.M4 기준

## 소개
- Spring Boot Project 에서 기본적으로 MyBatis 지원 프로젝트가 존재하지 않아서 만들게되었습니다.

## 실행조건
- JDK 1.6 이상 환경
- Maven 설치 (추후 Grand 지원 예정)
- Spring Boot 개발 환경

## 소스 내려받기
```sh
$ git clone https://github.com/sbcoba/spring-boot-mybatis
```

## 샘플 실행
```sh
$ cd spring-boot-mybatis
$ mvn install
# 샘플 실행
$ mvn -pl spring-boot-mybatis-sample spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-default spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-xml spring-boot:run
```

## Maven Dependency 설정
```xml
<dependency>
  <groupId>org.springframework.boot.mybatis</groupId>
  <artifactId>spring-boot-mybatis</artifactId>
  <version>1.3.0.M4</version>
</dependency>
```