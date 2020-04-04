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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8949d9d6402a06b032e2d04afce6af18"></script> <!-- 카카오 지도 API -->
<!-- [도서관관리자] 소속 도서관 정보를 출력하는 페이지 -->
<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">도서관 상세정보</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="javascript:void();">도서관 상세정보</a></li>
			</ul>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">도서관정보</h2>
		<div class="member_table_view">
			<table>
				<colgroup>
					<col style="width:120px;">
					<col style="width:;">
					<col style="width:120px;">
					<col style="width:;">
				</colgroup>
				<tr>
					<th>도서관이름</th>
					<td>${library.libName}</td>
					<th>도서관코드</th>
					<td>${library.libCode}</td>
				</tr>
				<tr>
					<th>도서관주소</th>
					<td colspan="3">
						(${library.libZip})&nbsp;${library.libAddr1}&nbsp;${library.libAddr2}
					</td>
				</tr>
				<tr>
					<th>도서관연락처</th>
					<td>${library.libTel}</td>
					<th>도서관홈페이지</th>
					<td><a href="${library.libHomePage}">${library.libHomePage}</a></td>
				</tr>
				<tr>
					<th>지도보기</th>
					<td colspan="3">
						<div id="map" style="width: 500px; height: 400px"></div>
					</td>
				</tr>
			</table>
			<div class="buttons">
				<a href="LibraryModifyForm.libL?libcode=${param.libcode}">정보수정</a>
			</div>
		</div>
	</div>
	<script>
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(${library.libLa}, ${library.libLo}), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};
		
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		var markerPosition  = new kakao.maps.LatLng(${library.libLa}, ${library.libLo}); // 마커가 표시될 위치 
		
		var marker = new kakao.maps.Marker({// 마커를 생성
		    position: markerPosition
		});
	
		marker.setMap(map);// 마커가 지도 위에 표시되도록 설정
	</script>
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