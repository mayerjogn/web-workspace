<%@page import="servlet.model.vo.MemberDTO"%>
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
		<c:when test="${!empty vo}">
			<!--<c:if test="${not empty dto}">-->
			<h1>로그인 정보</h1>
			<ul>
				<li>아이디 : ${vo.id}</li>
				<li>비밀번호 : ${vo.password}</li>
				<li>주소 : ${vo.address}</li>
			</ul>
			<a href="/index.jsp">첫 페이지로 이동</a>
			<!--</c:if>-->
		</c:when>
		<c:otherwise>
			<!--<c:if test="${empty dto}">-->
			<h3>로그인 실패..! 다시 로그인 하세요</h3>
			<a href="views/login.html">login.html</a>
			<!--</c:if>-->
		</c:otherwise>
	</c:choose>
</body>
</html>