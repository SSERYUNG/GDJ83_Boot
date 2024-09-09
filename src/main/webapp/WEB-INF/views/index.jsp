<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>SSE</h1>
	<img alt="" src="images/young.png">
	
	<spring:message code="hello"></spring:message>
	
	<c:if test="${empty member}">
	<h1>로그인 하기 전</h1>
	</c:if>
	
	<c:if test="${not empty member}">
		<c:forEach items="${member.vos}" var="v">
			<h1>로그인 했다</h1>
			<h3>${v.roleName}</h3>
		</c:forEach>
	</c:if>
	
</body>
</html>