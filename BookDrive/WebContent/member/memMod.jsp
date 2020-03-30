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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 내정보 수정 컨텐츠 영역 시작  -->
<div id="divContentsW">
	<div class="login-page">
		<div class="form">
			<form class="login-form" id="loginform" name="modifyform" action="memModPro.mem" method="post" onsubmit="return chkForm(this)">
				<img src="/bdstyle/image/Book.png" width="150px">
				<br><h3>내정보 수정  </h3><br><br>
					 <label for="userID">아이디</label>
					 <input type="text" name="userID" id="userID" value="${member.id }" class="input100" readonly="readonly"> 
						<label for="userPass">비밀번호</label> 
						<input type="password" name="userPass" id="userPass" value="${member.password }" class="input100" required="required"/>
						<label for="userName">이름</label>
						<input type="text" name="userName" id="userName" value="${member.name }" class="input100" readonly="readonly"/>
						<label for="userPhone">연락처</label>
						<input type="text" name="userPhone" id="userPhone" value="${member.tel }" class="input100" />	
						<label for="userBirth">생년월일</label>
						<input type="text" name="userBirth" id="userBirth" value="${member.birth }" class="input100" />
						<%
						String userId = request.getParameter("userEmailId");
						String userEmailid[] = userId.split("@");
						pageContext.setAttribute("userEmailid", userEmailid[0]);
						System.out.println("userEmailid :::: " + userEmailid[0] );
						
						%>
						<label for="userEmail">이메일</label><br>
						<input type="text" name="userEmailId" id="userEmailId"size="10" value="${userEmailid }" />&nbsp;@
						<span id="emailform">
							<select name="userEmailAd" id="userEmailAd" onchange="selectEmail(this)">
								<option value="gmail.com">gmail.com</option>
								<option value="naver.com">naver.com</option>
								<option value="daum.net">daum.net</option>
								<option value="hotmail.co.kr">hotmail.co.kr</option>
								<option value="null">직접입력</option>
							</select>
						</span>
						<br>
						<!-- 
						<label for="userAddr">주소</label>
						<input type="text" name="userAddr1" id="userAddr1" class="input100" />
						 -->
						 
						 <label for="userAddr">주소</label><br>
							<input type="text" name="userZip" id="userZip" value="${member.zip }" size="7" />
							<button type="button" onclick="sample6_execDaumPostcode()">주소검색</button><br>
							<input type="text" name="userAddr1" id="userAddr1" value="${member.addr1 }" class="input100"/ placeholder="주소">
							<input type="text" name="userAddr2" id="userAddr2" value="${member.addr2 }" class="input100"/ placeholder="상세주소">
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
						<input type="text" name="userCarNum" id="userCarNum" value="${member.carNum }"class="input100" />
						<br><br><br>
				<button class="input100" type="submit">수정하기  </button>
				<a href="javascript:modifyform.reset()"><h3>입력 초기화</h3></a>&nbsp;&nbsp;
				<a href="javascript:modifyform.onclick=location='memInfo.mem?id=${userID }'"><h3>내정보보기</h3> </a>
				<br><br><br><br>
				</form>
			</div>
		</div>
	</div>
</body>
</html>