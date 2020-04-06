<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/lib_loginCheck.jsp"/>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
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
			getLaLo();
		}
	}).open();
}
</script>
<style>
table.libModifyInfo tr th {width: 150px;}
</style>
<!-- [도서관관리자] 도서관 정보를 수정하는 페이지 -->
<div id="divContentsW">
	<div id="divContents_info">
		<h2 id="divTitle">도서관 정보수정</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="javascript:void();">도서관 정보수정</a></li>
			</ul>
		</div>
		<form name="f" id="member_form" action="LibraryModifyPro.libL" method="post">
			<input type="hidden" id="code" name="code" value="${library.libCode}">
			<div class="member_table_info_view">
				<table class="libModifyInfo">
					<colgroup>
						<col style="width:120px;">
						<col style="width:;">
						<col style="width:120px;">
						<col style="width:;">
					</colgroup>
					<tr>
						<th>도서관 이름</th>
						<td colspan="3">
							<input type="text" id="name" name="name" class="inputTextType5 sw" value="${library.libName}">
						</td>
					</tr>
					<tr>
						<th>도서관 전화번호</th>
						<td colspan="3">
							<input type="text" id="tel" name="tel" class="inputTextType5 sw" value="${library.libTel}">
						</td>
					</tr>
					<tr>
						<th>도서관 팩스번호</th>
						<td colspan="3">
							<input type="text" id="fax" name="fax" class="inputTextType5 sw" value="${library.libFax}">
						</td>
					</tr>
					<tr>
						<th>도서관 홈페이지</th>
						<td colspan="3">
							<input type="text" id="homepage" name="homepage" class="inputTextType5 sw" value="${library.libHomePage}">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">
							<input type="text" id="zip" name="zip" class="inputTextType5 sw" value="${library.libZip}" style="width: 200px;">&nbsp;<input type="button" class="btnType5" id="addr_search" onclick="openDaumZipAddress();" value="주소검색"><br>
							<input type="text" id="addr1" name="addr1" class="inputTextType5 sw" value="${library.libAddr1}"><br>
							<input type="text" id="addr2" name="addr2" class="inputTextType5 sw" value="${library.libAddr2}">
						</td>
					</tr>
					<tr class="geocode">
						
					</tr>
				</table>
			</div>
			<div class="buttons">
				<a href="javascript:void();" onclick="f.submit();">수정</a>
				<a href="LibraryInfo.libL?libcode=${param.libcode}">돌아가기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function () { //책이름은 기본적으로 노출이 되지 않음.
	$('.geocode').hide();
});

function getLaLo() {
	$.ajax({
        method: "GET",
        url: "https://dapi.kakao.com/v2/local/search/address.json", // 전송 주소
        data: { query: $('#addr1').val() }, // 보낼 데이터
        headers: { Authorization: "KakaoAK 7c28f9da096eaa302f600c9900820d6e" }
    })
    .done(function (msg) { // 응답이 오면 처리를 하는 코드
    	var append = "<th>좌표정보</th><td colspan=3>" + msg.documents[0].x + ", " + msg.documents[0].y + "</td>";
    	append += "<input type=hidden id=lo name=lo value=" + msg.documents[0].x + ">";
    	append += "<input type=hidden id=la name=la value=" + msg.documents[0].y + ">";
    	$('.geocode').empty();
   		$('.geocode').append(append);
   		$('.geocode').show();
    });
}
</script>