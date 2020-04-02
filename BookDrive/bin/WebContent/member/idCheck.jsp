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
	function init(){
		if(true){
			document.getElementById("id").value = opener.document.getElementById("userID").value;
		}
	}
	function ok(v){
		opener.document.getElementById("userID").value = v;
		opener.chkId = true;
		window.close();
	}
	function chkForm(f){
		
		var id = f.id.value;
		var reg_id = /^[a-z]+[a-z0-9]{5,19}$/g;
		
		if(f.id.value.trim()==""){
			alert("아이디를 입력해주세요.");
			f.id.focus();
			return false;
		}
	
		document.id_check.submit();
	}
</script></head>


<body onload="init()">

<!-- action dulpTest.me -->
<form name="id_check" action="<%=request.getContextPath() %>/idCheck.mem" method="post" onsubmit="return chkForm(this)">
<table id="addRow">
	<tbody><tr>
		<td><h3>아이디 중복검사</h3></td>
	</tr>
	<tr>
		<td><input type="text" name="id" id="id" placeholder="아이디"></td>
	</tr>
	<tr>
		<td>
		<button id="gbutton" style="width:100%;padding:10px;" type="submit">아이디 중복확인</button>
		</td>
	</tr>
</tbody></table>
</form>
<br>

<div id="testform">
	<% 	
		if(request.getAttribute("passibleId")!=null){
			if((boolean)request.getAttribute("passibleId")) {
				out.print(request.getAttribute("id") + "는 사용가능한 아이디입니다. <br><a href='#' onclick=\"ok('" + request.getAttribute("id") + "')\"> 사용하기 </a>");
			} else {
				out.print(request.getAttribute("id") + "는 사용 불가능한 아이디입니다. <br>다시 입력하세요.");
			}
		}
	%>
</div>
</body>
</html>