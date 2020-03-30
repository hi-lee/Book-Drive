<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.id ne null}">
	<script>
		alert('로그인 상태입니다. 메인화면으로 돌아갑니다.');
		location.href="Adminmain.logL";
	</script>
</c:if>
<!-- CSS -->
<link rel="stylesheet" href="bdstyle/admin/style/ko/home/admin_login.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/home/admin_message.css">
<div id="divContentsW">
		<div id="divContents">
		<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<h2 id="divTitle">관리자 로그인</h2>
<div id="divLocation">
	<ul>
		<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
		<li>로그인</li>
	</ul>
</div>
<div class="loginW">
			<form id="login" name="login" action="Adminlogin.logL" method="post">
			<fieldset>
			<legend>로그인</legend>
			<div class="loginContent">
				<div class="intro">
					<img src="bdstyle/admin/image/ko/local/loginIco.png" alt="MEMBER LOGIN">
					<p class="welcome"><strong>BookDrive <span>도서관 관리자</span>로</strong> 로그인 해주세요.</p>
				</div>
				<dl>
					<dt><label for="id">아이디</label></dt>
					<dd>
						<input id="id" name="id" class="inputTextType1" title="아이디입력" type="text" value="" size="20" maxlength="15" alt="아이디입력">
					</dd>
				</dl>
				<dl>
					<dt><label for="pass">비밀번호</label></dt>
					<dd>
						<input id="pass" name="pass" class="inputTextType1" title="비밀번호 입력" type="password" value="" size="20" maxlength="20" alt="비밀번호 입력">
					</dd>
				</dl>
				<p class="loginBtn"><input type="submit" value="로그인"></p>
			</div>
		</fieldset>
		</form>
</div>
<script type="text/javascript">
	jQuery(document).ready(function(){
	
	jQuery('#id').focus();
	
});
</script>
	</div>
</div>