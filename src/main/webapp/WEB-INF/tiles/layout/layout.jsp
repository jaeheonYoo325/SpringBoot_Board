<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1">	
<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap.css' />">		
<link rel="stylesheet" href="<c:url value='/css/common/layout.css' />">			
<link rel="stylesheet" href="<c:url value='/css/common/footer.css' />">	
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="<c:url value='/bootstrap/js/bootstrap.js' />"></script>

<div id="pageWrapper">
	<div id="header">
		<tiles:insertAttribute name="sessionHeader"/>
	</div>
	
	<tiles:insertAttribute name="content"/>

	<div id="footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>