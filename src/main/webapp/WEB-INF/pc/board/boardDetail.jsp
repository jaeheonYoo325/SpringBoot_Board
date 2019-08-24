<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<link rel="stylesheet" href="<c:url value='/css/board/boardDetail.css' />">

<title>게시판 상세 페이지</title>
	
<!-- 채용공고 상세페이지 화면 구성 -->
<div class="container">
	 <h1 class="page-header">게시판 상세</h1>
	 <table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th colspan="6"><h4>게시물 보기</h4></th>
			</tr>
			<tr>
				<td class="tdStyle"><h5>제목</h5></td>
				<td colspan="5"><h5>${boardVO.title}</h5></td>
			</tr>
			<tr>
				<td class="tdStyle"><h5>작성자</h5></td>
				<td colspan="5"><h5>${boardVO.memberVO.name}</h5></td>
			</tr>
			<tr>				
				<td class="tdStyle"><h5>작성날짜</h5></td>
				<td><h5>${boardVO.wrtDt}</h5></td>
				<td class="tdStyle"><h5>조회수</h5></td>
				<td><h5>${boardVO.hit}</h5></td>
			</tr>
			
			<tr>
				<td class="tdStyleContent"><h5>파일명</h5></td>
				<td colspan="5">
				<c:if test="${not empty boardVO.originFileName}">
					<p>							
						<a href="<c:url value='/board/boardFileDownload.do/${boardVO.boardId}' />">
							${boardVO.originFileName}
						</a>							
					</p>
				</c:if>
				</td>
			</tr>
			<tr>
				<td class="tdStyleContent"><h5>글 내용</h5></td>
				<td colspan="5" style="text-align: left;"><h5>${boardVO.content}</h5></td>
			</tr>
		</thead>
   		<tbody>
   			<tr>
   				<td colspan="6" style="text-align: right;">
   					<a href="<c:url value='/board/boardUpdate.do/${boardVO.boardId}' />" class="btn btn-primary btn-warning" type="button" >수정</a>	         				
        			<a href="<c:url value='/board/boardDelete.do/${boardVO.boardId}' />" class="btn btn-primary btn-warning" type="submit" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>   			        				         				       				         			
   					<a href="<c:url value='/board/boardList.do' />" class="btn btn-primary" type="button" >목록</a>     					     					   					
   				</td>
   			</tr>
   		</tbody>
	</table>
</div>