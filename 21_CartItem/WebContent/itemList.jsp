<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Fruit Total List</h1>

	<c:forEach items="${list}" var="item">
		<tr>
			<td>${item.itemId}</td>
			<td>${item.itemName}</td>
			<td>${item.price}</td>
			<td>${item.description}</td>
			<td>${item.pictureUrl}</td>
		</tr>
	</c:forEach>
</body>
</html>