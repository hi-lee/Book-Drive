<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.input100 {
	width: 30%;
	font-family: 'NanumBarunGothic', sans-serif;
	font-weight: 200;
	text-transform: uppercase;
	outline: 0;
	border: 3px solid #dcccac;
	padding: 70px;
	color: #4c4c4c;
	font-size: 30px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}
form {
	text-align: center;
    border-top-width: 50px;
    padding-top: 50px;
	
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="divContentsW">
		<div id="divContents">
			<form name="driveform" action="login.log" method="post" onsubmit="return chkForm(this)">
				<button class="input100" type="button" onclick="location.href='driveLogin.bk'">검색대출<br>Check Out</button>
				<button class="input100" type="button" onclick="window.open('book/driveRental.jsp?openInit=true', 'a', 'width=500, height=300')">예약대출<br>Check Out</button>
				<button class="input100" type="button" onclick="window.open('book/returnCheck.jsp?openInit=true', 'a', 'width=500, height=300')">반납<br>Return</button>
			</form>
		</div>
	</div>
</body>
</html>