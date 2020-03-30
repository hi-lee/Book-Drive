<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/home/style2.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
function chkForm(f){
	var mail1 = f.userEmailId.value;
	var mail2 = f.userEmailAd.value;
	
	var reg_email1 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])+$/i;
	var reg_email2 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	if(f.userID.value.trim()==""){
		alert("아이디는 필수사항입니다.");
		f.userID.focus();
		return false;
	}
	if (!reg_email1.test(mail1)) {
		alert("올바른 이메일 주소가 아닙니다.");
		f.userEmailId.focus();
		return false;
	}
	if (!reg_email2.test(mail2)) {
		alert("올바른 이메일 주소가 아닙니다.");
		f.userEmailAd.focus();
		return false;
	}

	document.pwfindform.submit();
}
function selectEmail(sel) {
	var choiceText = sel.options[sel.selectedIndex].text;

	if (choiceText == '직접입력') {
		document.getElementById("emailform").innerHTML = "<td><input type='text' name='userEmailAd' id='userEmailAd' size='15'/></td>";
	}
}
</script>
</head>
<body>
	<div id="divContentsW">
		<div class="login-page">
			<form class="login-form" name="pwfindform" action="<%=request.getContextPath() %>/mailSend.mail" method="post" onsubmit="return chkForm(this)">
				<h3>비밀번호 찾기</h3>
				<input type="text" id="userID" name="userID" placeholder="아이디" maxlength="20" class="input100"/><br>
				<input type="text" name="userEmailId" id="userEmailId" size="8" placeholder="이메일"/>&nbsp;@
				<span id="emailform">
				<select name="userEmailAd" id="userEmailAd" onchange="selectEmail(this)">
						<option value="gmail.com">gmail.com</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="hotmail.co.kr">hotmail.co.kr</option>
						<option value="null">직접입력</option>
				</select></span>
					<button id="gbutton" style="width: 100%; padding: 10px;" type="submit">인증메일 발송</button>
					<br> <br>
				</form>
			</div>
		</div>
</body>
</html>