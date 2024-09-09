<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>My Page</h1>
	
	<h3>${member.username}</h3>
	<h3>${member.name}</h3>
	<h3>${member.email}</h3>
	<h3>${member.birth}</h3>

	<a href="./update">회원수정</a>

</body>
</html>