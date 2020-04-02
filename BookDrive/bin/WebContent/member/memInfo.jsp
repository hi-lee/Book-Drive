<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${userID == null}">
	<script>
		alert('로그인하세요!');
		location.href = "loginForm.log";
	</script>
</c:if>
<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/home/join.css">
<style>
.form {
	width: 450px;
	max-width: 500px;
}
</style>

	<!-- 컨텐츠 영역 시작  -->
<div id="divContentsW">
	<div class="login-page">
		<div class="form">
			<form class="login-form" id="loginform" name="loginform" action="memMod.mem" method="post" onsubmit="return chkForm(this)">
				<img src="bdstyle/image/ko/solution/common/bkicon.png" width="30px">
				<h3>내정보보기 </h3><br><br>
					 <label for="userID">아이디</label>
					 <input type="text" name="userID" id="userID" value="${member.id }" class="input100" readonly="readonly"> 
						<label for="userPass">비밀번호</label> 
						<input type="password" name="userPass" id="userPass" value="${member.password }" class="input100" readonly="readonly"/>
						<label for="userName">이름</label>
						<input type="text" name="userName" id="userName" value="${member.name }" class="input100" readonly="readonly"/>
						<label for="userPhone">연락처</label>
						<input type="text" name="userPhone" id="userPhone" value="${member.tel }" class="input100" readonly="readonly"/>	
						<label for="userBirth">생년월일</label>
						<input type="text" name="userBirth" id="userBirth" value="${member.birth }" class="input100" readonly="readonly"/>
						<label for="userEmail">이메일</label>
						<input type="text" name="userEmailId" id="userEmailId" value="${member.email }" class="input100" readonly="readonly"/>
						
						<label for="userAddr">주소</label>
						<input type="text" name="userZip" id="userZip" value="${member.zip }" class="input100" readonly="readonly"/>
						<input type="text" name="userAddr1" id="userAddr2" value="${member.addr1 }" class="input100" readonly="readonly"/>
						<input type="text" name="userAddr2" id="userAddr2" value="${member.addr2 }" class="input100" readonly="readonly"/>
						
						<label for="userCarNum">차 번호 </label>
						<input type="text" name="userCarNum" id="userCarNum" value="${member.carNum }"class="input100" readonly="readonly"/>
						<br><br><br>
				<button class="input100" type="submit">내정보 수정 </button>
				<br><br><br><br>
				</form>
			</div>
		</div>
	</div>
