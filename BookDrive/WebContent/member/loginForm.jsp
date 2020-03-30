<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/login.css">
<style>
#header {
    text-align: center;
    margin: 0 auto;
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
		if (f.userID.value.trim() == "") {
			alert("아이디는 필수항목입니다.");
			f.userID.focus();
			return false;
		}
		if (f.userPass.value.trim() == "") {
			alert("비밀번호는 필수항목입니다.");
			f.userPass.focus();
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
	<div id="divContents">
		<h2 id="divTitle">로그인</h2>
		<div id="divLocation">
			<ul>
				<li><a href="template_main.jsp" title="HOME"><img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="loginForm.log">로그인 </a></li>
			</ul>
		</div>


		<div class="login-page">
			<div class="form">
				<form class="login-form" id="loginform" name="loginform"
					action="login.log" method="post" onsubmit="return chkForm(this)"><br>
					<div id="header">
					<img src="bdstyle/image/ko/solution/common/bkicon.png" width="30px"><h3>Book-Drive</h3></div>
					<br>
					<br>
					<!--  a href="/FarmParm/main.im" id="logo"><img src="../images/farm_logo.png" width="150px"></a><br><br>							
					<input type="hidden" id="returnURI" name="returnURI" value="http://118.131.179.138/FarmParm/main.im">
				 -->

					<input type="text" id="userID" name="userID" placeholder="아이디"
						maxlength="20" class="input100" value="" /> <input type="password"
						name="userPass" id="userPass" placeholder="비밀번호" class="input100" />
					<div style="float: right;">
						<input type="checkbox" id="id_rem" name="id_rem" /><label
							for="id_rem" style="font-size: 12px;">&nbsp;아이디 기억하기</label><br>
					</div>
					<button type="submit" class="input100">로그인</button>
					<br>
					<br>
					<div id="footer">
						<a href="#"
							onclick="window.open('member/idFind.jsp?openInit=true','a','width=400, height=400');">아이디
							찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href="#"
							onclick="window.open('member/pwFind.jsp?openInit=true','a','width=400, height=400');">비밀번호
							찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href="joinForm.mem">회원가입</a>&nbsp;&nbsp;
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</form>
			</div>
		</div>
	</div>
</div>