<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${username != null}"> <!-- 인증통과된 경우 세션을 만들어줄 예정 -->
		${username} 인증되었습니다
	
	</c:when>
	<c:otherwise>
		<form action="auth" method="post">
		<input type="text" name="email" value="hyesuc_seo@naver.com">
		<input type="submit" value="인증하기">
		</form>
	</c:otherwise>
</c:choose>

	
</body>
</html>