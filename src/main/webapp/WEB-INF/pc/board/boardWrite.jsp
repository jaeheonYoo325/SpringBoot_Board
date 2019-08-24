<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>     

	<link rel="stylesheet" href="<c:url value='/css/board/boardWrite.css' />">

	<title>게시판 작성 페이지</title>
	
	<script src="<c:url value='/ckeditor5/ckeditor.js' />"></script>
	<script src="<c:url value='/js/common/common.js' />"></script>
	<script src="<c:url value='/js/board/boardWrite.js' />"></script>		

	<div class="container">
		<h1 class="page-header">게시글 작성</h1>		
		<form:form id="boardWriteForm"
				   modelAttribute="boardVO"
				   autocomplete="off">
				   			
			<div>
				<form:errors id="errorsTitle" cssStyle="color: red;" path="title" />
			</div>
			<div>
				<form:errors id="errorsContent" cssStyle="color: red;" path="content" />
			</div>		
							   
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th colspan="2"><h4>게시물 작성</h4></th>
					</tr>
				</thead>
				<tbody>					
					<tr>
						<td class="tdStyle"><h5>글 제목</h5></td>
						<td><input class="form-control" type="text" id="title" name="title" placeholder="글 제목을 입력하세요." value="${boardVO.title}"></td>
						
					</tr>
					<tr>
						<td class="tdStyle"><h5>글 내용</h5></td>
						<td><textarea id="content" class="form-control" rows="10" name="content" placeholder="글 내용을 입력하세요.">${boardVO.content}</textarea></td>
						
					</tr>
					<tr>
						<td class="tdStyle"><h5>파일 업로드</h5></td>
						<td colspan="2">
							<input type="file" name="file" class="file">
							<div class="input-group col-xs-12">
								<span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
								<input type="text" class="form-control input-lg" disabled placeholder="파일을 업로드하세요.">
								<span class="input-group-btn">
									<button class="browse btn btn-primary input-lg" id="fileSearchBtn" type="button"><i class="glyphicon glyphicon-search"></i>파일 찾기</button>
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td class="tdBtnStyle" colspan="4">
							<input type="button" id="createBtn" class="btn btn-primary btn-warning" value="등록" />
							<a href="<c:url value='/board/boardList.do' />" class="btn btn-primary" type="button" >목록</a>													
						</td>
					</tr>			
				</tbody>
			</table>				   	
		</form:form>
	</div>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content panel-warning">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							오류 메시지
						</h4>
					</div>
					<div id="checkMessage" class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="alert alert-success" id="successMessage">
		<strong>글 작성이 성공적으로 처리되었습니다.</strong>
	</div>