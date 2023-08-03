<%--
JSP Element
1) 지시어 %@ % : 컨테이너에게 알려줄 내용을... 지정
2) 스클릿틀릿 % % : 자바코드는 이 안에 지정
3) 출력문 %= % : 출력할려는 내용 지정
 --%>
 <!-- HTML 주석은 페이지 소스에서 보이지만 JSP 문법으로 만든 주석은 페이지 소스에서 안보임 -->
<%@ page import="servlet.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- % % :: Scriptlet Elemnt -->
<%
	MemberVO vo = (MemberVO) application.getAttribute("vo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
	<!-- %= % :: Expression Element -->
	<h2>회원님의 정보를 출력합니다..</h2>
	<p> 
		이름 : <%= vo.getName()%> <br>나이 :<%=vo.getAge() %> <br>주소:
		<%=vo.getAddr()%>
	</p>

</body>
</html>