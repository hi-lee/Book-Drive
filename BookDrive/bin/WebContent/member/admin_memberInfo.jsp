<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<%@page import="java.util.*"%>
<%@page import="vo.admin.Library"%>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_list.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<!-- Toastr 관련 -->
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<!-- [통합관리자] 도서관 관리자 가입신청을 출력하는 페이지 -->
<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">일반회원 정보</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="MemberList.memA">회원정보</a></li><li><a href="MemberInfo.memA">일반회원 정보</a></li>
			</ul>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">회원정보</h2>
		<div class="member_table_view">
			<table>
				<colgroup>
					<col style="width:120px;">
					<col style="width:;">
					<col style="width:120px;">
					<col style="width:;">
				</colgroup>
				<tr>
					<th>아이디</th>
					<td>${member.memId}</td>
					<th>이름</th>
					<td>${member.memName}</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${member.memBirth}</td>
					<th>전화번호</th>
					<td>${member.memTel}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3">(${member.memZip})&nbsp;${member.memAddr1}&nbsp;${member.memAddr2}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${member.memEmail}</td>
					<th>차번호</th>
					<td>${member.memCarNum}</td>
				</tr>
			</table>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">회원 대출중 정보</h2>
		<!-- List(책 대출중 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름</th>
						<th scope="row">소장처</th>
						<th scope="row">대출일자</th>
						<th scope="row" class="footable-last-column" data-hide="phone">반납일자</th>
					</tr>
				</thead>
				<tbody>
					<!-- 멤버 리스트 출력 -->
					<c:forEach items="${member.memRenFlag}" varStatus="status">
						<c:if test="${member.memRenFlag[status.index] eq 'brw' and member.renIdvDelFlag[status.index] eq '0'}">
							<tr>
								<td class="libcode footable-first-column">
									${member.bookName[status.index]}
								</td>
								<td>${member.libName[status.index]}</td>
								<td>${member.renBrwDate[status.index]}</td>
								<td class="libhomepage footable-last-column">${member.renBrwInvDate[status.index]}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">회원 예약중 정보</h2>
		<!-- List(책 예약중 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름</th>
						<th scope="row">소장처</th>
						<th scope="row">예약일자</th>
						<th scope="row" class="footable-last-column" data-hide="phone">만료일자</th>
					</tr>
				</thead>
				<tbody>
					<!-- 멤버 리스트 출력 -->
					<c:forEach items="${member.memRenFlag}" varStatus="status">
						<c:if test="${member.memRenFlag[status.index] eq 'rev' and member.renIdvDelFlag[status.index] eq '0'}">
							<tr>
								<td class="libcode footable-first-column">
									${member.bookName[status.index]}
								</td>
								<td>${member.libName[status.index]}</td>
								<td>${member.renRevDate[status.index]}</td>
								<td class="libhomepage footable-last-column">${member.renRevInvDate[status.index]}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">회원 대출완료 정보</h2>
		<!-- List(책 대출완료 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름</th>
						<th scope="row">소장처</th>
						<th scope="row">대출일자</th>
						<th scope="row" class="footable-last-column" data-hide="phone">반납일자</th>
					</tr>
				</thead>
				<tbody>
					<!-- 멤버 리스트 출력 -->
					<c:forEach items="${member.memRenFlag}" varStatus="status">
						<c:if test="${member.memRenFlag[status.index] eq 'brw' and member.renIdvDelFlag[status.index] eq '1'}">
							<tr>
								<td class="libcode footable-first-column">
									${member.bookName[status.index]}
								</td>
								<td>${member.libName[status.index]}</td>
								<td>${member.renBrwDate[status.index]}</td>
								<td class="libhomepage footable-last-column">${member.renBrwInvDate[status.index]}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="buttons">
			<a href="MemberModifyForm.memA?index=${member.memIndex}&id=${member.memId}">회원정보수정</a>
			<a href="javascript:deleteMember(${member.memIndex}, '${member.memName}');">회원삭제</a>
			<a href="MemberList.memA">목록</a>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
	$("a#memBookName").click(function() {
		if($("tr#memRev").is(':visible') == false) {
			$("tr#memRev").show();
		} else {
			$("tr#memRev").hide();
		}
	})
})
$(document).ready(function () { //책이름은 기본적으로 노출이 되지 않음.
	$("tr#memRev").hide();
});

var year_jan = '년 1월';
var year_feb = '년 2월';
var year_mar = '년 3월';
var year_apr = '년 4월';
var year_may = '년 5월';
var year_jun = '년 6월';
var year_jul = '년 7월';
var year_aug = '년 8월';
var year_sep = '년 9월';
var year_oct = '년 10월';
var year_nov = '년 11월';
var year_dec = '년 12월';

var sun = '일';
var mon = '월';
var tue = '화';
var wed = '수';
var thu = '목';
var fri = '금';
var sat = '토';

var policy_content = '1';

$.datepicker.setDefaults({
    monthNames: [year_jan,year_feb,year_mar,year_apr,year_may,year_jun,year_jul,year_aug,year_sep,year_oct,year_nov,year_dec],
    dayNamesMin: [sun, mon, tue, wed, thu, fri, sat],
	showMonthAfterYear:true,
	dateFormat: 'yymmdd',
	duration: 0
	//buttonImageOnly: true,
	//buttonText: "달력",
	//buttonImage: "/ui/images/calendar.gif",
});

$(document).ready(function(){
	//$("#dateFrom").val($.datepicker.formatDate('yymmdd', new Date(2009, 1 - 1, 18)));
	// $("#dateFrom").datepicker({defaultDate: new Date(2009, 1 - 1, 18)});
	//$("#dateTo").val($.datepicker.formatDate('yymmdd', new Date(2009, 1 - 1, 27)));
	//$("#dateTo").datepicker({defaultDate: new Date(2009, 1 - 1, 27)});
	$("#dateFrom").datepicker();
	$("#dateTo").datepicker();

	var subjectUse = 'N';
	if(subjectUse == 'Y') {
		var subjectCode = '';
		$("#subject_code > option[value=" + subjectCode + "]").attr("selected", "true");
	}
	
	$("#ui-datepicker-div").css('z-index','1000');
	/*
	var policyYn = 'N';
	if (policyYn == 'Y' && policy_content){
		//alert("게시판 이용 시 '주민등록번호'등과 같은 개인정보를 등록할 경우 제3자에 의해 피해를 입을 수 있습니다. 개인정보 등록 여부를 확인 하셨습니까?");
		alert(policy_content);
	}
	*/
});

function deleteMember(memIndex, memName) {
	var isDeleteCheck = confirm(memName + " 회원을 삭제하시겠습니까?");
	if (isDeleteCheck) {
		location.href='MemberDeletePro.memA?memindex='+memIndex;
	} else {
		toastr.error('취소하였습니다.', '삭제 취소!');
	}
}
//읽기 권한이 없는경우
function cantReadContent(){
	alert('읽을 권한이 없습니다.');
	return;
}

//비공개글일 경우
function getNoPublicContent(){
	alert('이 글은 비공개로 설정되어 있습니다.');
	return;
}

function validate(fname){
    var digits="0123456789";
    var temp;
    
    var tsc = eval("document.searchFrm."+fname);
        for (var i=0;i<tsc.value.length;i++){
            temp=tsc.value.substring(i,i+1);
        if (digits.indexOf(temp)==-1){
            alert("숫자만 입력 하세요"); 
            tsc.value = "";
            return false;
            }
         }
        return true; 
}   
</script>