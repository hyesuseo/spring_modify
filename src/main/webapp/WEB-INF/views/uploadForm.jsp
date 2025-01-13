<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 파일 업로드 </h3>
<form action="${contextPath}/upload" method="post" enctype="multipart/form-data">
<input type="text" name="id"><br>
<input type="text" name="name"><br>
<input type="file" name="file"><br>
<input type="submit" value="업로드"><br>
<hr>
<a href="${contextPath}/views">목록보기</a>

</form>

</body>
</html>