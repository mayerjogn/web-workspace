<%@ page import="java.util.Arrays"%>
<%@ page import="servlet.model.MemberVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
</head>
<body>
	<%
	String name = request.getParameter("name");
	//List<MemberVO> voList = (List<MemberVO>) application.getAttribute("vo");
	
	%>
	
	<div class="container">
	<h2>회원조회</h2>
	<form action="search">
		검색할 회원 이름을 입력해주세요.<br>
		<input type="text" name="name"> <input type="submit" value="조회">
	</form>
	
	<hr>

		<h2>전체 회원 정보를 표시합니다</h2>

		<table class="table">
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>

			<%-- 회원 정보 출력 for문 --%>
			<%
			List<MemberVO> voList =(List) request.getAttribute("list");
			if (voList != null) {
				for (int i = 0; i < voList.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=voList.get(i).getName()%></td>
				<td><%=voList.get(i).getAge()%></td>
				<td><%=voList.get(i).getAddr()%></td>
			</tr>
			<%
			}
			}
			%>

			<%-- --<%
	for (MemberVO vo : voList) {
	%>
	<tr>
		<td><%=vo.getName()%></td>
		<td><%=vo.getAge()%></td>
		<td><%=vo.getAddr()%></td>
	</tr>
	<%
	}
	%>--%>
		</table>
	</div>
</body>
</html>