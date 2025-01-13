<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h3>${id}수정</h3>
		<form action="${contextPath}/modify">
		<input type="text" name="id" value="${id}" readonly><br> 
		<input type="text" name="name" value="${name}"><br> 
		<input type="file" name="file" value="${file}"><br> 
		<input type="submit" value="수정"><br> 
		</form>

</body>
</html>