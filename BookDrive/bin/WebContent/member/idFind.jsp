<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>
form {
	padding: 10px 45px 45px 45px;
	color: #4c4c4c;
}

table {
	margin: auto;
	width: 300px;
	text-align: center;
}

input {
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	padding: 10px;
	box-sizing: border-box;
	font-size: 12px;
	margin: 0 0 15px;
}

#testform {
	width: 300px;
	text-align: center;
	margin: 0 auto;
	color: #4c4c4c;
}

a img {
	height: 16px;
}

a, a:hover, a:active {
	text-decoration: none;
	color: black;
}

#gbutton {
	font-family: "Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: #dcccac;
	border: 0;
	width: 60px;
	padding: 3px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

#g button:hover, .form button:active, .form button:focus {
	background: #c7b895;
}

#footer {
    text-align: center;
    font-size: 12px;
    color: #dcccac;
}
</style>


<script>
	
	
	function chkForm(f) {
		var age = f.userAge.value;
		var reg_age = /^\d{8}$/

		if (f.userName.value.trim() == "") {
			alert("이름은 필수사항입니다.");
			f.userName.focus();
			return false;
		}
		if (!reg_age.test(age)) {
			alert("올바른 형식이 아닙니다.");
			f.userAge.focus();
			return false;
		}
		//if(f.pass.value.trim()!=f.passChk.value.trim()){f.pass.value="";}
		document.idfindform.submit();
	}
</script>
</head>
<body>
	<div id="divContentsW">
		<div class="login-page">
			<div class="form">
				<form class="login-form" name="idfindform" action="<%=request.getContextPath() %>/idFind.mem"
					method="post" onsubmit="return chkForm(this)">
					<h3>아이디 찾기</h3>
					<input type="text" id="userName" name="userName" placeholder="이름"
						maxlength="10" class="input100" /> <input type="text"
						name="userBirth" id="userBirth" placeholder="생년월일   ex) 19990303"
						class="input100" />
					<button id="gbutton" style="width: 100%; padding: 10px;" type="submit">찾기</button>
					<br> <br>
					<div id="testform">
						<c:choose>
								<c:when test="${memberId ne null }">
									${memberId.name }님의 아이디는 ${memberId.id }입니다. 
								</c:when>
								<c:otherwise>
									이름, 생년월일과 일치하는 아이디가 없습니다.
								</c:otherwise>
						</c:choose><br><br>
					</div>
					<div id="footer">
						<a href="pwFind.jsp">비밀번호 찾기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>