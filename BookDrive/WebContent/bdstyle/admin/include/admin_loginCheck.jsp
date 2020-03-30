<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 로그인을 체크하는 페이지. 통합관리자로 로그인을 해야 session의 adminCheck 값이 true가 된다.-->
<c:if test="${sessionScope.id eq null and sessionScope.adminCheck eq null}">
	<script>
		alert('통합관리자로 로그인을 해주세요.');
		location.href="AdminloginForm.logC";
	</script>
</c:if>