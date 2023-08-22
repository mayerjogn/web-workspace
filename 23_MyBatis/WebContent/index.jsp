<%@page import="model.service.StudentService"%>
<%@page import="model.vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    <!-- 
    첫 페이지에 리스트 뿌려주기 <% %> 자바 문법활용
    service 호출해서 list에 담아
    request.setAttribute로 바인딩
     -->
     <%
     	List<StudentVO> list = new StudentService().showStudent(null);
      	request.setAttribute("list", list);
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>머학생 호구조사</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style>
.container .row{
	margin-top: 50px;
	margin-bottom: 30px;
}
</style>
</head>

<body>

  <div class="container">
  <div class="row"> 
  	<div class="col">
  		<input id="name" name="name"type="text" class="form-contorl">
  	</div>
  	<div class="col">
  		<input id="searchBtn" value="검색"type="button" class="btn btn-danger">
  	</div>
  </div>
  	<table class="table">
  	<thead>
  	<tr>
    	<td>학번</td>
    	<td>이름</td>
    	<td>주소</td>
    	<td>학과</td>
    	<td>계열</td>
    </tr>
    </thead>
    <tbody>
     <c:forEach items="${list}" var="item"> 	
    	<tr>
			<td>${item.studentNo}</td>
			<td>${item.studentName}</td>
			<td>${item.studentAddress}</td>
			<td>${item.department.departmentName}</td>
			<td>${item.department.category}</td>
		</tr>
    </c:forEach>
    </tbody>
  	</table>
  </div>
  <script>
  	$('#searchBtn').click(function(){
  		const word = $('#name').val();
  		$.ajax({
  			type:'get',
  			url:'find.do', // 검색은 get방식 많이사용 get방식에서 url이 find.do가 필요함 
  			data:'name='+word,
  			dataType:'json',
  			success:function(data){
  				const result=eval(data.result);
  				let resultHtml='';
  				console.log(result);
  				for(let item of result){
  					console.log(item);
  					resultHtml+="<tr>"+
  								 "<td>"+item.studentNo+"</td>"+
  								 "<td>"+item.studentName+"</td>"+
  								 "<td>"+item.studentAddress+"</td>"+
  								 "<td>"+item.department.departmentName+"</td>"+
  								 "<td>"+item.department.category+"</td>"+
  								 "</tr>";
  				}
  				$('tbody').html(resultHtml);
  			}
  		});
  	});
  </script>
</body>
</html>