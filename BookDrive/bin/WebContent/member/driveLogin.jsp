<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/login.css">
<style>
.form {
	text-align: left;
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 0;
	padding: 45px 45px 45px 45px;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}
#divContentsW {
	padding-bottom: 105px;
}
</style>
<script>
	window.onload = function() {
		if (getCookie("id")) {
			document.loginform.userID.value = getCookie("id");
			document.loginform.id_rem.checked = true;
		}
	}

	function setCookie(name, value, expiredays) {
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() + expiredays);
		document.cookie = name + "=" + escape(value) + "; path=/; expires="
				+ todayDate.toGMTString() + ";"
	}

	function getCookie(Name) {
		var search = Name + "=";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1)
					end = document.cookie.length;
				return unescape(document.cookie.substring(offset, end));
			}
		}
	}

	function chkForm(f) {
		if (f.memIndex.value.trim() == "") {
			alert("회원번호는 필수항목입니다.");
			f.userID.focus();
			return false;
		}
		if (document.loginform.id_rem.checked == true) {
			setCookie("id", document.loginform.userID.value, 7);
		} else {
			setCookie("id", document.loginform.userID.value, 0);
		}

		//if(f.pass.value.trim()!=f.passChk.value.trim()){f.pass.value="";}
		document.loginform.submit();
	}
</script>
<!-- 컨텐츠 영역 시작  -->
<div id="divContentsW">
	<h2 id="divTitle"><br></h2>
	<div class="login-page">
		<div class="form">
			<form class="login-form" id="loginform" name="loginform"
				action="driveLogin.log" method="post" onsubmit="return chkForm(this)">
				<div id="header">
					<hr color="#4CAF50">
					<h3>회원인증</h3>
					<br><br>
				</div>
				<input type="text" id="memIndex" name="memIndex"
					placeholder="회원번호 또는 차량번호 입력" maxlength="20" class="input100"
					value="" />
				<button type="submit" class="input100">회원인증</button>
				<br> <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>
</div>