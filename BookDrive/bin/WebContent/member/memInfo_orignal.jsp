<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${id == null}">
	<script>
		alert('로그인하세요!!');
		location.href = "loginForm.log";
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자모드(회원 정보 보기)</title>
<style>
table {
	margin: auto;
	width: 400px;
	border: 1px solid gray;
	text-align: center;
}
</style>
</head>
<body>
	<div id="divContentsW">
		<table>
			<tr>
				<td>아이디 :</td>
				<td>${member.id }</td>
			</tr>
			<tr>
				<td>비밀번호 :</td>
				<td>${member.password }</td>
			</tr>
			<tr>
				<td>이름 :</td>
				<td>${member.name }</td>
			</tr>
		
			<tr>
				<td>이메일 주소 :</td>
				<td>${member.email }</td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${id eq 'admin' }">
							<a href="memberList.mem">리스트로 돌아가기</a>
						</c:when>
						<c:otherwise>
							<c:set var="pagefile" value="member/main.jsp" scope="session" />

							<a href="template.jsp">메인으로 돌아가기</a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</div>
</body>
</html>
