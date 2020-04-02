<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
}

#testform {
	width: 300px;
	text-align: center;
	margin: 0 auto;
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
</style>
</head>
<body>

	<script>
		function chkForm(f) {
	
			var memIndex = f.index.value;
			
			if (f.memIndex.value.trim() == "") {
				alert("회원번호/차량번호를 입력해주세요.");
				f.memIndex.focus();
				return false;
			}
			document.bookReturn.submit();
		}
	</script>
</head>


<body onload="init()">

	<!-- action dulpTest.me -->
	<form name="bookReturn"
		action="<%=request.getContextPath()%>/driveRental.bk"
		method="post" onsubmit="return chkForm(this)">
		<table id="addRow">
			<tbody>
				<tr>
					<td><h3>예약대출하기</h3></td>
				</tr>
				<tr>
					<td><input type="text" name="memIndex" id="memIndex"
						placeholder="회원번호/차량번호를 입력하세요"></td>
				</tr>
				<tr>
					<td>
						<button id="gbutton" style="width: 100%; padding: 10px;"
							type="submit">번호 입력</button>
							<input type="hidden" name="usage" value="1">
							<input type="hidden" name="driveRev" value="true">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<br>
	<%--
	<div id="testform">
		<%
			if (request.getAttribute("returned") != null) {
				if ((boolean) request.getAttribute("returned")) {
					out.print("<br>반납 완료되었습니다. <br><a href='#' onclick='window.close'>");
				} else {
					out.print("<br>반납실패 <br>");
				}
			}
		%>
	</div>
	 --%>

</body>
</html>