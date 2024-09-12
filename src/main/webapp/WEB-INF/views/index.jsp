<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	<spring:message code="hello2" text="기본값"></spring:message>
	
	<sec:authorize access="!isAuthenticated()">
	<h1>로그인 하기 전</h1>
	<a href="/member/login">Login</a>
	<!-- 이 주소는 고정! -->
	<a href="/oauth2/authorization/kakao?prompt=login">Kakao Login</a>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<h1>로그인 했다</h1>
 		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<c:forEach items="${member.vos}" var="v">
			<h3>${v.roleName}</h3>
		</c:forEach>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용입니다</h1>
	</sec:authorize>
	
</body>
</html>