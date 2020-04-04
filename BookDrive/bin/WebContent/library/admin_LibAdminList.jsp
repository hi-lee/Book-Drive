<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<%@page import="java.util.*"%>
<%@page import="vo.admin.Library"%>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/svp/bbs/admin_list.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<!-- Toastr관련 -->
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<!-- [통합관리자] 도서관 관리자 가입신청을 출력하는 페이지 -->
<div id="divContentsW">
   <div id="divContents">
<h2 id="divTitle">도서관관리자 리스트</h2>
<div id="divLocation">
	<ul>
		<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
		<li><a href="https://cham.kiu.ac.kr/bbs/list/2">회원정보</a></li><li><a href="https://cham.kiu.ac.kr/bbs/list/1#">도서관관리자 리스트</a></li>
	</ul>
</div>
<div id="divContent">
	<!-- Content Header -->
	<h3 class="skip">게시판 안내</h3>
	<!-- Content Num Of Search Result -->
	<div class="listInfo">
		<div class="listInfo1">
			<p class="totalCnt">총 <span class="totalcount">102</span> 건</p>
			<p class="pageNum">,<span>1</span>/6페이지</p>
		</div>
	</div>
	<!-- List -->
	<div class="listTable">
		<table class="mobileTable default">
			<caption>게시판 목록</caption>
			<thead>
				<tr>
					<th scope="row" class="footable-first-column">이름(도서관이름)</th>
					<th scope="row">도서관코드</th>
					<th scope="row">아이디</th>
					<th scope="row">전화번호</th>
					<th scope="row">이메일</th>
					<th scope="row" class="footable-last-column" data-hide="phone">승인취소</th>
				</tr>
			</thead>
			<tbody>
				<!-- 멤버 리스트 출력, hashtable을 사용해도 일반 list를 사용한 것처럼 쓰면됨(key값만 정확히 적어줄 것) -->
				<c:forEach var="list" items="${libAdminList}">
						<tr class="adminlist${list.adminNum}">
							<td class="libcode footable-first-column">
								${list.adminName}&nbsp;(${list.libName})
							</td>
							<td>${list.libCode}</td>
							<td>${list.adminId}</td>
							<td>${list.adminTel}</td>
							<td>${list.adminEmail}</td>
							<td class="libhomepage footable-last-column">
								<a class="btn bg_red c_fff" href="javascript:callLibApvRvoPro('${list.adminNum}', '${list.adminId}');" onclick="">승인취소</a>
							</td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
function callLibApvRvoPro(adminNum, adminId) {
	if (typeof adminNum == 'undefined' || adminNum == ''
			|| !adminNum) {
		console.log('회원번호가 없습니다.');
		return false;
	}

	if (typeof adminId == 'undefined' || adminId == '' || !adminId) {
		console.log('회원아이디가 없습니다.');
		return false;
	}
	$.ajax({
		url : "LibraryApvRvoPro.lib",
		type : "POST",
		dataType : "json",
		data : {
			"adminNum" : adminNum,
			"adminId" : adminId
		},
		success : function(data) {
			if (data != null) {
				if (data.result) {
					var classID = 'adminlist' + adminNum;
					toastr.options = {
						"closeButton" : false,
						"debug" : false,
						"newestOnTop" : false,
						"progressBar" : true,
						"positionClass" : "toast-bottom-center",
						"preventDuplicates" : false,
						"onclick" : null,
						"showDuration" : "300",
						"hideDuration" : "1000",
						"timeOut" : "3000",
						"extendedTimeOut" : "1000",
						"showEasing" : "swing",
						"hideEasing" : "linear",
						"showMethod" : "fadeIn",
						"hideMethod" : "fadeOut"
					}
					toastr.success("승인취소완료", "승인이 취소되었습니다");
					$('.' + classID).hide();
				}
			}
		},
		error : function(request, status, error) {
			console.log(error);
			toastr.success('승인취소가 실패했습니다.');
		}
	});
};
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
	</div>
</div>