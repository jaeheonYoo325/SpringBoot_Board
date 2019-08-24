# SpringBoot_Board
스프링부트 기반의 게시판 예제입니다.

● Operating System : Windows 10 <br />
● Programming Language : JAVA(jdk1.8.0_211) <br />
● DBMS : ORACLE 11g <br />
● Framework : SpringBoot, Mybatis 3.5.x, Tiles, Bootstrap <br />
● Technology : JDBC, HTML 5.0, CSS, Javascript, JQuery, Ajax, Jstl <br />
● Tool : STS , ERWin, Toad for Oracle <br />
● Other libraries : Gradle 3.x , Apache Tika, jMimeMagic, CKEditdor <br />
● Protocol : HTTPS (SSL 적용) <br /><br />

● 프로젝트 프로세스 : 회원가입 + 로그인 + 게시판 3가지 기능으로 구현<br />

● URL : SSL 적용하여 모든 url이 Https로 접근(https의 포트번호 지정)<br />

● 회원가입 <br />
  ▲ Client, Server Validation 체크 구현 <br />
  ▲ 이메일 중복체크 구현 <br />
  ▲ SHA 256 기반의 패스워드 암호화 구현 <br /><br />

● 로그인 <br />
  ▲ Client, Server Validation 체크 구현 <br /><br />

● 게시판(CRUD) <br />
&nbsp;▲ 게시글 LIST <br />
    → 게시글 검색 및 검색 초기화 구현 <br />
    → 게시글 페이지네이션 구현 <br /><br />
  ▲ 게시글 WRITE <br />
    → Client, Server Validation 체크 구현 <br />
    → 파일업로드 구현(확장자:jpg, png, git) <br />
  ▲ 게시글 DETAIL <br />
    → 파일 다운로드 <br />
    → 서로 다른 계정으로 게시글 읽었을 때 조회수 Count 구현 <br /><br />
  ▲ 게시글 UPDATE <br />
    → Client, Server Validation 체크 구현 <br />
    → 파일업로드 구현(확장자:jpg, png, git) <br /><br />
  ▲ 게시글 DELETE







