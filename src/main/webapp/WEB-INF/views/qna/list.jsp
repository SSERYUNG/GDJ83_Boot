<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>

<h1><spring:message code="board.name"></spring:message></h1>
<table>
	<thead>
		<tr>
			<th>Num</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Date</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${list}" var="vo">
			<tr>
			<th>${vo.boardNum}</th>
			<th><a href="./detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a></th>
			<th>${vo.boardWriter}</th>
			<th>${vo.createDate}</th>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="./add">Add 화면으로 가기</a>

</body>
</html>