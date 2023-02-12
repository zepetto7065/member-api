## Member API

### 설명
- member-api (가칭)의 경우, 전통적인 계층형 아키텍처 (layered architecture) 스타일과 달리 육각형 아키텍처 (hexagonal architecture)
스타일을 적용하였습니다.
- multi gradle 모듈 형태로 필요 의존성을 분류하였습니다.
- 기본 테스트 데이터 셋팅은 web_api/src/main/resources/data.sql 참고 부탁드립니다.

<hr>

### 구현 범위 & api 설명
- 회원가입 
  - /api/v1/members (POST)
- 로그인
  - /api/v1/login (POST)
  - 로그인 가능시, jwt 토큰 발행
- 내 정보 보기
  - /api/v1/members/{memberId} (GET)
- 비밀번호 재설정
  - /api/v1/members (PUT)

<hr>

### 사용기술
- Multi Gradle Module
- Spring Data JPA
- Springdoc-openapi
- JWT

### 실행
- local의 경우, h2 인메모리 db 를 사용하였습니다. local 실행시 , h2 구동 후 실행하여야합니다.

[build&run]
- ./gradlew clean web_application:build
- java -jar web_application/build/libs/web_application-1.0-SNAPSHOT.jar

<hr>

### swagger
- http://localhost:8080/swagger-ui/index.html

<hr>

### dependency
![](http://www.plantuml.com/plantuml/png/ROz13i8W44Ntd68kq2kOtiJymjGGMvWCqx0mtbr8YL7ZYWVpU8Iv6DIEVNC2VyUDoMTbUZXgi4jEd8n2tX4JeL1mJ_Ss4I36-YDNNgwGsEtQrgzPJAkYc1xU3kMknBLy9fUShSqXVwEoHG-BEPtnVh62fkb2OS3vvE3UTlrWA6q_QEOKsfzV)
- web_application : web api 관련 의존성 취합 및 bootJar 빌드 모듈
- web_api : spring web controller 등 사용자 요청 처리
- infrastructure : 데이터 저장소 처리
- domain : 핵심 비지니스 로직

### 참고
- https://engineering.linecorp.com/ko/blog/port-and-adapter-architecture/
- https://spring.io/guides/gs/multi-module/
- https://docs.gradle.org/current/userguide/multi_project_builds.html
