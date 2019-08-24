# SpringBoot_Board
스프링부트 기반의 게시판 예제입니다.

● Operating System : Windows 10 <br />
● Programming Language : JAVA(jdk1.8.0_211) <br />
● DBMS : ORACLE 11g <br />
● Framework : SpringBoot, Mybatis 3.5.x, Tiles, Bootstrap <br />
● Technology : JDBC, HTML 5.0, CSS, Javascript, JQuery, Ajax, Jstl <br />
● Tool : STS , ERWin, Toad for Oracle <br />
● Other libraries : Gradle 3.x , Apache Tika, jMimeMagic, CKEditdor <br />
● Protocol : HTTPS (SSL 적용)

● 프로젝트 프로세스 : 회원가입 + 로그인 + 게시판 3가지 기능으로 구현

● URL : SSL 적용하여 모든 url이 Https로 접근(https의 포트번호 지정)

● 회원가입<br />
  - Client, Server Validation 체크 구현
  - 이메일 중복체크 구현
  - SHA 256 기반의 패스워드 암호화 구현

● 로그인
  - Client, Server Validation 체크 구현

● 게시판(CRUD)
  - 게시글 LIST
    -> 게시글 검색 및 검색 초기화 구현
    -> 게시글 페이지네이션 구현
  - 게시글 WRITE
    -> Client, Server Validation 체크 구현
    -> 파일업로드 구현(확장자:jpg, png, git)
  - 게시글 DETAIL
    -> 파일 다운로드
    -> 서로 다른 계정으로 게시글 읽었을 때 조회수 Count 구현
  - 게시글 UPDATE
    -> Client, Server Validation 체크 구현
    -> 파일업로드 구현(확장자:jpg, png, git)
  - 게시글 DELETE







