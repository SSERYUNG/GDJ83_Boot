<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>List Practice</h1>

<%-- 	<form action="" method="post" enctype="multipart/form-data">
			<input type="text" name="boardTitle">
			<input type="text" name="boardWriter">
			<textarea rows="" cols="" name="boardContents"></textarea>
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<button>등록</button>
		</form> --%>
	
	<form:form modelAttribute="qnaVO">
		<form:errors path="boardTitle"></form:errors><br>
		<form:input path="boardTitle"/><br>
		<form:errors path="boardWriter"></form:errors><br>
		<form:input path="boardWriter"/><br>
		<form:textarea path="boardContents"/><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<button type="submit">등록22</button>
	</form:form>
	
</body>
</html>