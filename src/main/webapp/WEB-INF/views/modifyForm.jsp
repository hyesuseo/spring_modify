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

		<h3>파일수정</h3>
		<form action="${contextPath}/modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="origin" value="${info.imgFileName}"><br> 
		<input type="text" name="id" value="${info.id}" readonly><br> 
		<input type="text" name="name" value="${info.name}"><br> 
		<input type="file" name="file"><br> 
		<input type="submit" value="수정"><br> 
		</form>

</body>
</html>