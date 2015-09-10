# Spring Boot MyBatis 

## 버전
- Spring Boot 1.3.0.M5 기준

## 소개
- Spring Boot Project 에서 기본적으로 MyBatis 지원되지 않기 때문에 만듦 
- 간단히 Dependency 추가로 인해 Spring Boot 에서 MyBatis를 사용할 수 있도록 지원 
- 기존의 xml 설정없이 모든 기능을 확장할 수 있도록 구현

## 실행조건
- JDK 1.6 이상 환경 ( JDK 1.7 이상 추천 )
- Maven 설치 (추후 Gradle 지원 예정)
- Spring Boot 개발 환경

## 소스 내려받기
```sh
$ git clone https://github.com/sbcoba/spring-boot-mybatis
```

## 샘플 실행
```sh
$ cd spring-boot-mybatis
$ mvn clean install
# 샘플 실행
$ cd ../spring-boot-mybatis-sample
$ mvn -pl spring-boot-mybatis-sample-default spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-simple spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-xml spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-hybrid spring-boot:run
$ mvn -pl spring-boot-mybatis-sample-groovy spring-boot:run
```

## Maven Dependency 설정
```xml
<dependency>
  <groupId>org.springframework.boot.mybatis</groupId>
  <artifactId>spring-boot-mybatis</artifactId>
  <version>1.3.0.M5</version>
</dependency>
```

## 예제설명
- spring-boot-mybatis-sample-default
	- 기본적이 설정이 없을 때 작동하는 예제
	- 기본적인 설정 Annotation을 사용
- spring-boot-mybatis-sample-simple
	- 가장 간단하게 DB에 접근할 수 있도록 하나의 Class에서 작동하는 예제 
- spring-boot-mybatis-sample-xml
	- XML mapper를 사용하여 작동하는 예제
	- 기본적인 XML 설정 필요
- spring-boot-mybatis-sample-hybrid
	- 기본 Annotation 형태와 XML형태를 동시에 사용하도록 작동하는 예제 
	- 단지 테스트형태이며 실무에서는 지양필요
- spring-boot-mybatis-sample-groovy
	- 기본 Annotation 형태는 XML형태에서 Multiline이 되지 않기 때문에 Groovy의 '''문자열''' 기능을 사용하여 멀티라인 문자열을 구현 하여 쿼리를 편리하게 작성할 수 있도록 구성된 예제
	- 이 부분은 Spring Boot MyBatis와 관계없이 작동하는 예제
