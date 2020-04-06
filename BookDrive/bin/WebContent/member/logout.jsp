<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:remove var="id" scope="session"/>
<c:if test="${userID eq null }">
	<script>	
	alert('로그아웃 되었습니다.');
	location.href="loginForm.log";
	</script>	
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>