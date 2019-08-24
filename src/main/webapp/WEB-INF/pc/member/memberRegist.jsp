<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<link rel="stylesheet" href="<c:url value='/css/member/memberRegist.css' />">
<title>회원가입 페이지</title>
<script src="<c:url value='/js/common/common.js' />"></script>
<script src="<c:url value='/js/member/memberRegist.js' />"></script>	

<div class="container">
	<div class="login-box well">
		<form:form	id="registForm"
               		modelAttribute="memberVO"
               		autocomplete="off">
               		
            	<legend class="legend">회원가입</legend>
          		<div class="form-group has-feedback">
	            <div class="input-group">
     					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
     					<input id="email" type="text" class="form-control" name="email" placeholder="이메일" value="${memberVO.email}">
   				</div>
	            <span id="emailMessage" class="help-block">올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.</span>
           		<span class="glyphicon glyphicon-ok form-control-feedback"></span>
           		<form:errors id="errorsEmail" cssStyle="color: red;" path="email" />
       		</div>
       		
       		<div class="form-group has-feedback">
	            <div class="input-group">
     					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
     					<input id="password" type="password" class="form-control" name="password" placeholder="비밀번호" maxlength="20" value="${memberVO.password}">
   				</div>
	            <span id="passwordMessage" class="help-block">대/소문자, 특수문자 8글자 이상 입력하세요.</span>
	            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
	            <form:errors id="errorsPassword" cssStyle="color: red;" path="password" />
       		</div>
       		
       		<div class="form-group has-feedback">
	            <div class="input-group">
     					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
     					<input id="passwordConfirm" type="password" class="form-control" name="passwordConfirm" placeholder="비밀번호 확인" maxlength="20" value="${memberVO.passwordConfirm}">
   				</div>
	            <span id="passwordConfirmMessage" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</span>
	            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
	            <form:errors id="errorsPasswordConfirm" cssStyle="color: red;" path="passwordConfirm" />
       		</div>
       		
       		<div class="form-group has-feedback">
	            <div class="input-group">
     					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
     					<input id="name" type="text" class="form-control" name="name" placeholder="이름" value="${memberVO.name}">
   				</div>
	            <span id="nameMessage" class="help-block">이름을 입력해주세요.</span>
           		<span class="glyphicon glyphicon-ok form-control-feedback"></span>
           		<form:errors id="errorsName" cssStyle="color: red;" path="name" />
       		</div>
       		<div class="buttonDiv" style="text-align: center;">
       			<input type="button" id="registBtn" class="btn btn-primary btn-warning" value="등록">        			
       			<a href="<c:url value='/main/index.do' />" class="btn btn-primary" type="button" >취소</a>        			        			
       		</div>
           </form:form>
	</div>
</div>
<div class="alert alert-success" id="successMessage">
	<strong>축하합니다. 회원가입 완료되었습니다.</strong>
</div>