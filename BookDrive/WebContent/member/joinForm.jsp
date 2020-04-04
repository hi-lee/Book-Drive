<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>회원가입 </title>
<style>
textarea{
	font-size:11px;
	resize: none;
}
</style>

<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/home/join.css">
<script>
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}
var chkId = false;
function chkForm(f){

	var birth = f.userBirth.value;
	var mail1 = f.userEmailId.value;
	var mail2 = f.userEmailAd.value;
	var phone = f.userPhone.value;
	var req = document.joinform.req.checked;
	var num = 0;
	
	var reg_birth = /^\d{8}$/
	var reg_email1 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])+$/i;
	var reg_email2 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	var reg_phone = /^[0-9]{10,11}$/

	if(!chkId){
		alert("아이디 중복 확인이 필요합니다!");
		return false;
	}
	if(f.userPass.value.trim()==""){
		alert("비밀번호는 필수항목입니다.");
		f.userPass.focus();
		return false;
	}
	if(f.userPass.value.trim()!=""){
		if(f.userPass.value!=f.userPassre.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.userPassre.value="";
			f.userPassre.focus();
			return false;
		}
	}
 	if(f.userPass.value.length<6) {
	    alert("비밀번호는 6자리 이상 입력해주세요.");
	    f.userPass.focus();
	    return false;
	}
	if(f.userName.value.trim()==""){
		alert("이름은 필수항목입니다.");
		f.userName.focus();
		return false;
	}
	if (!reg_phone.test(phone)) {
		alert("올바른 번호가 아닙니다.");
		f.userPhone.focus();
		return false;
	}
	if (!reg_birth.test(birth)) {
		alert("올바른 형식이 아닙니다.");
		f.userBirth.focus();
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
	if (!reg_phone.test(phone)) {
		alert("올바른 번호가 아닙니다.");
		f.userPhone.focus();
		return false;
	}
	if(f.userZip.value.length!=5){
		alert("다섯자리 (신)우편번호를 입력해주세요.");
		f.userAddr1.focus();
		return false;
	}
	if(f.userAddr1.value.trim()==""){
		alert("주소는 필수항목입니다.");
		f.userAddr1.focus();
		return false;
	}
	if(f.userAddr2.value.trim()==""){
		alert("주소는 필수항목입니다.");
		f.userAddr2.focus();
		return false;
	}
	if(req == true){
		num = 1;
	}
	if(num!=1){
		alert("개인정보 약관에 동의해주세요.");
		return false;  
	}
		
//	if(doubleSubmitCheck()) return false;
//	else alert('회원가입이 완료되었습니다.');
	
	document.joinform.submit();
}

function selectEmail(sel) {
	var choiceText = sel.options[sel.selectedIndex].text;

	if (choiceText == '직접입력') {
		document.getElementById("emailform").innerHTML = "<td><input type='text' name='userEmailAd' id='userEmailAd'/></td>";
	}
}
</script>
<div id="divContentsW">

<div class="join-page">
	<div class="form">
		<form class="login-form" id="joinform" name="joinform" action="memberJoinProcess.mem" method="post" onsubmit="return chkForm(this)">
			<div id="header">
			<hr color="#4CAF50">
			<br><img src="bdstyle/image/mainlogo.png" height="40px"><br><br><br>
	</div>
				
	<label for="memCheck">회원구분 </label><br><br>
	<div id="memCheck">
		<input type="radio" name="userCheck" value="일반  " checked="checked" id="userCheck"/>&nbsp;일반 사용자  &nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="userCheck" value="관리자 ">&nbsp;관리자 
		<hr color="#dcccac">
	</div><br><br>
	<label for="userID">아이디</label>
	<input type="text" name="userID" id="userID" placeholder="6~20자 영문자 또는 영문자+숫자" class="input100">
	<button class="input100" name="check" id="check" type="button"
	onclick="window.open('member/idCheck.jsp?openInit=true','a','width=500, height=300')">아이디 중복확인 </button><br><br>
	<label for="userPass">비밀번호</label>
	<input type="password" name="userPass" id="userPass" placeholder="6자리 이상" class="input100"/>
	<label for="userPassre">비밀번호확인</label>
	<input type="password" name="userPassre" id="userPassre" class="input100"/>
	<label for="userName">이름</label>
	<input type="text" name="userName" id="userName" class="input100"/>
	<label for="userPhone">연락처</label>
	<input type="text" name="userPhone" id="userPhone" placeholder="ex) -없이 입력해주세요" class="input100"/>
	<label for="userBirth">생년월일</label>
	<input type="text" name="userBirth" id="userBirth" placeholder="ex) 19990303" class="input100"/>
<!--  		
		<label for="userGen">성별</label><br><br>
		<div id="genCheck">
		<input type="radio" name="userGen" value="남" checked="checked" id="userGen"/>&nbsp;남자&nbsp;
		&nbsp;&nbsp;&nbsp;
		<input type="radio" name="userGen" value="여">&nbsp;여자
		<hr color="#dcccac">
		</div>
-->		
	<br>
	<label for="userEmail">이메일</label><br>
	<input type="text" name="userEmailId" id="userEmailId"size="10" />&nbsp;@
	<span id="emailform">
		<select name="userEmailAd" id="userEmailAd" onchange="selectEmail(this)">
			<option value="gmail.com">gmail.com</option>
			<option value="naver.com">naver.com</option>
			<option value="daum.net">daum.net</option>
			<option value="hotmail.co.kr">hotmail.co.kr</option>
			<option value="null">직접입력</option>
		</select>
	</span>
	<label for="userAddr">주소</label><br>
	<input type="text" name="userZip" id="userZip" size="7" />
	<button type="button" onclick="sample6_execDaumPostcode()">주소검색</button><br>
	<input type="text" name="userAddr1" id="userAddr1" class="input100"/ placeholder="주소">
	<input type="text" name="userAddr2" id="userAddr2" class="input100"/ placeholder="상세주소">
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    function sample6_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var fullAddr = ''; // 최종 주소 변수
		                var extraAddr = ''; // 조합형 주소 변수
		
		                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    fullAddr = data.roadAddress;
		
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    fullAddr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
		                if(data.userSelectedType === 'R'){
		                    //법정동명이 있을 경우 추가한다.
		                    if(data.bname !== ''){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있을 경우 추가한다.
		                    if(data.buildingName !== ''){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
		                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('userZip').value = data.zonecode; //5자리 새우편번호 사용
		                document.getElementById('userAddr1').value = fullAddr;
		
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById('userAddr2').focus();
		            }
		        }).open();
		    }
		</script>
		<br>
		
		<label for="userCarNum">차 번호 </label>
		<input type="text" name="userCarNum" id="userCarNum" 
		placeholder="ex)01나4243" class="input100"/>
		<br>
		<p style="font-size:12px;">개인정보 수집 및 이용 동의</p>
		<textarea id="use" rows="10" cols="56" readonly>
1. 개인정보 수집목적 및 이용목적

가. 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 
요금정산 콘텐츠 제공 , 구매 및 요금 결제 , 
물품배송 또는 청구지 등 발송 , 
금융거래 본인 인증 및 금융 서비스, 마케팅

나. 회원 관리
회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 
불량회원의 부정 이용 방지와 비인가 사용 방지 , 
가입 의사 확인 , 연령확인 , 만14세 미만 아동의
개인정보 수집 시 법정 대리인 동의여부 확인, 
불만처리 등 민원처리 , 고지사항 전달


2. 수집하는 개인정보 항목 : 이름 , 생년월일 , 성별 , 
로그인ID , 비밀번호 , 자택 전화번호 , 휴대전화번호 , 
이메일 , 14세미만 가입자의 경우 법정대리인의 정보


3. 개인정보의 보유기간 및 이용기간
원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 
해당 정보를 지체 없이 파기합니다. 
단, 다음의 정보에 대해서는 아래의 이유로 
명시한 기간 동안 보존합니다.

가. 회사 내부 방침에 의한 정보 보유 사유
o 부정거래 방지 및 쇼핑몰 운영방침에 따른 보관 : 30년
나. 관련 법령에 의한 정보보유 사유
o 계약 또는 청약철회 등에 관한 기록
-보존이유 : 전자상거래등에서의소비자보호에관한법률
-보존기간 : 5년
o 대금 결제 및 재화 등의 공급에 관한 기록
-보존이유: 전자상거래등에서의소비자보호에관한법률
-보존기간 : 5년 
o 소비자 불만 또는 분쟁처리에 관한 기록
-보존이유 : 전자상거래등에서의소비자보호에관한법률
-보존기간 : 3년 
o 로그 기록 
-보존이유: 통신비밀보호법
-보존기간 : 3개월

※ 동의는 거부할 수 있으나 거부시 회원 가입이 불가능합니다.
		</textarea>
		<br>
		<input type="checkbox" name="req" id="req">
		<label for="req" style="font-size:12px;">&nbsp;동의합니다</label>
	
		<br><br><br>
				<button class="input100" type="submit">회원가입</button>
				<br><br><br><br>
			<div id="footer">
				&nbsp;&nbsp;<a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">개인정보처리방침</a>
				&nbsp;&nbsp;|&nbsp;&nbsp;<a href="issue.jsp">문제발생</a>&nbsp;&nbsp;
			</div>
			</form>
		</div>
	</div>
</div>