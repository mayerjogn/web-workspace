<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
	<c:when test="${param.num eq '1'}">
		봉주르
	</c:when>
	
	<c:when test="${param.num eq '2'}">
		하지메 마시떼
	</c:when>
	
	<c:otherwise>
		후아유 
	</c:otherwise>
	</c:choose>
</body>
</html>