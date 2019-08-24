<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>        

	
<link rel="stylesheet" href="<c:url value='/css/member/memberLogin.css' />">

<title>로그인 페이지</title>
<script src="<c:url value='/js/common/jquery-form.js' />"></script>
<script src="<c:url value='/js/common/common.js' />"></script>
<script src="<c:url value='/js/member/memberLogin.js' />"></script>

<!-- 로그인 화면 구성 -->
<div class="container" style="width: 30%;">
	<div class="login-box well">
		 <form:form id="loginForm"
               		modelAttribute="memberVO">
               <legend style="text-align: center;">로그인</legend>
               <div class="form-group has-feedback">
		        <div class="input-group">
      				<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
      				<input id="email" type="text" class="form-control" name="email" placeholder="이메일" value="${recruitMemberVO.email}">
    			</div>
		        <span id="emailMessage" class="help-block">올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.</span>
            	<span class="glyphicon glyphicon-ok form-control-feedback"></span>
            	<form:errors cssStyle="color: red;" path="email" />
       		</div>
                   
               <div class="form-group has-feedback">
		        <div class="input-group">
      				<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
      				<input id="password" type="password" class="form-control" name="password" placeholder="비밀번호" maxlength="20" value="${recruitMemberVO.password}">
    			</div>
		        <span id="passwordMessage" class="help-block">비밀번호를 입력해주세요.</span>
		        <span class="glyphicon glyphicon-ok form-control-feedback"></span>
		        <form:errors cssStyle="color: red;" path="password" />
       		</div>
               <div>
               	<input type="button" id="loginBtn" class="btn btn-primary form-control" value="로그인">
               </div>
               <br />
               <div class="text-center">
               	<p><a href="<c:url value='/member/memberRegist.do' />">회원가입</a></p>               	
               </div>
           </form:form>
	</div>
</div>
<div class="alert alert-success" id="successMessage">
	<strong>로그인이 정상적으로 처리되었습니다.</strong>
</div>