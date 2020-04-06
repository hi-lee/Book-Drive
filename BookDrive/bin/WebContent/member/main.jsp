<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${sessionScope.id !=null }">
		<c:set var="userId" value="${id }" scope="session"/>
	</c:when>
	<c:otherwise>
		<script>
			alert('로그인을하세요!!');
			location.href='loginForm.jsp';
		</script>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 메인 페이지</title>
</head>
<body>
<h3>${id }로 로그인하셨습니다.</h3>
<a href="logout.log">로그아웃</a>&nbsp;&nbsp;
<a href="member_mod.jsp">회원 정보 수정</a>&nbsp;&nbsp;
<c:choose>
	<c:when test="${id == 'admin' }">
		<a href="memberList.mem">관리자모드 접속(회원 목록 보기)</a>
	</c:when>
	<c:when test="${id != null }">
		<a href="member_info.mem?id=${id }">내정보보기</a>		
	</c:when>
</c:choose>
</body>
</html>