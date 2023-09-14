[m.shinsCOM](https://m.shinsegaepoint.com/) 클론코딩
📍 프로젝트 소개
본 프로젝트는 스파로스 아카데미 3기에서 진행한 SSG.COM 신세계포인트 클론 코딩 프로젝트입니다.

제2정3(4조)의 백엔드 저장소입니다.
Demo
Project Repositories
프론트엔드
백엔드
 

📍 Notion
📍 시스템 아키텍처
시스템 아키텍처

 

📍 개발환경
> OS : Windows 10, Ubuntu 20.04.4 LTS
> Infra : AWS EC2
> DB : AWS RDS(MySQL 8.0.28)
> Storage : AWS S3
> Backend : Spring Boot 2.6.10, Gradle(빌드), JPA(DB접근)
> Api : Naver SMS API, Naver Login API, KaKao Login API
설치 및 실행
applicatiom.yml 파일의 datasource, security.oauth2, cloud.aws, sms(네이버 SMS API) 수정 => 사용자 계정, Secret Key 등 입력

SsgBeApplication.java main 함수 실행

 

📍 사용 기술
Frontend
           

Backend
     

Deploy
       

Tools
     

 

📍 디렉터리 구조
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─ssg
    │  │          ├─config # config, security, exception, etc.. 관리
    │  │          ├─ssg_be
    │  │          │  ├─point
    │  │          │  │  ├─application # Service 폴더
    │  │          │  │  ├─domain # 해당 도메인의 entity 폴더
    │  │          │  │  ├─dto # DTO 폴더
    │  │          │  │  ├─infrastructure # Repository 폴더
    │  │          │  │  └─presentation # Controller 폴더
    │  │          │  │  └─vo # VO 폴더  
    │  │          │  └─ ... # 나머지 도메인
    │  │          └─utils
    │  │              ├─jwt
    │  │              ├─oauth
    │  │              └─s3
    │  └─resources # application.yml 관리
    └─test
 

📍 개발 산출물
