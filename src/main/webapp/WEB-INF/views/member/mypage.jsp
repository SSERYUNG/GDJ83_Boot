<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>My Page</h1>
	
	<!--  sec:authentication는 이게 로그인 안 한 상태며는 오류 나니까 sec:authorize로 감싸거나 접근제한하기 -->
	<sec:authorize access="isAuthenticated()">
	<!--  출력방법 2개  -->
	<sec:authentication property="principal" var="vo"/>
	<h3>${vo.username}</h3>
	<h3>${vo.name}</h3>
	<h3><sec:authentication property="principal.email"/></h3>
	<!--  session 안에 principla과 같이 name이라는 변수명도 존재해서 요거는 이렇게 바로 꺼낼수도 있음  -->
	<h3><sec:authentication property="name"/></h3>
	</sec:authorize>
	
	<a href="./update">회원수정</a>

</body>
</html>