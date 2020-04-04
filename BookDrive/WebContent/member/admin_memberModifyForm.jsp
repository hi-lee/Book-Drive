<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function openDaumZipAddress() {
	new daum.Postcode({
		oncomplete:function(data) {
			var fullRoadAddr = data.roadAddress; //도로명 주소 변수
			var extraRoadAddr = ''; //도로명 조합형 주소 변수
			
			//법정동명이 있을 경우 추가한다(동, 로, 가)
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			
			//건물명이 있고, 공동주택일 경우 추가한다
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.build);
			}
			
			//도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열 추가
			if(extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			
			//도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
			if (fullRoadAddr !== '') {
				fullRoadAddr += extraRoadAddr;
			}
			
			//우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zip').value = data.zonecode;
			document.getElementById('addr1').value = fullRoadAddr;
			document.getElementById('addr2').focus();
			console.log(data);
		}
	}).open();
}
</script>
<!-- [통합관리자] 도서관 관리자 가입신청을 출력하는 페이지 -->
<div id="divContentsW">
	<div id="divContents_info">
		<h2 id="divTitle">일반회원 정보수정</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>회원관리</a></li><li><a href="javascript:void();">일반회원 정보수정</a></li>
			</ul>
		</div>
		<h2>회원정보 수정</h2>
		<form name="f" id="member_form" action="MemberModifyPro.memA" method="post">
			<input type="hidden" id="index" name="index" value="${member.memIndex}">
			<div class="member_table_info_view">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col style="width:;">
						<col style="width:120px;">
						<col style="width:;">
					</colgroup>
					<tr>
						<th>아이디</th>
						<td colspan="3">
							<input type="text" id="id" name="id" class="inputTextType5 sw" value="${member.memId}">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td colspan="3">
							<input type="text" id="pass" name="pass" class="inputTextType5 sw" value="${member.memPass}">
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td colspan="3">
							<input type="text" id="name" name="name" class="inputTextType5 sw" value="${member.memName}">
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td colspan="3">
							<input type="date" id="birthday" name="birthday" class="inputTextType5 sw" value="${member.memBirth}">
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td colspan="3">
							<input type="text" id="tel" name="tel" class="inputTextType5 sw" value="${member.memTel}">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">
							<input type="text" id="zip" name="zip" class="inputTextType5 sw" value="${member.memZip}" style="width: 200px;">&nbsp;<input type="button" class="btnType5" id="addr_search" onclick="openDaumZipAddress();" value="주소검색"><br>
							<input type="text" id="addr1" name="addr1" class="inputTextType5 sw" value="${member.memAddr1}"><br>
							<input type="text" id="addr2" name="addr2" class="inputTextType5 sw" value="${member.memAddr2}">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td colspan="3">
							<input type="text" id="email" name="email" class="inputTextType5 sw" value="${member.memEmail}">
						</td>
					</tr>
					<tr>
						<th>차번호</th>
						<td colspan="3">
							<input type="text" id="carnum" name="carnum" class="inputTextType5 sw" value="${member.memCarNum}">
						</td>
					</tr>
				</table>
				
			</div>
			<div class="buttons">
				<a href="javascript:void();" onclick="f.submit();">수정</a>
				<a href="MemberInfo.memA?id=${member.memId}&index=${member.memIndex}">돌아가기</a>
			</div>
		</form>
	</div>
</div>