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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8949d9d6402a06b032e2d04afce6af18"></script> <!-- 카카오 지도 API -->
<!-- Toastr관련 -->
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<!-- [통합관리자] 도서관 상세정보를 출력하는 페이지 -->
<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">도서관 상세정보</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>도서관정보</a></li><li><a href="javascript:void();">도서관 상세정보</a></li>
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
					<td style="font-weight: bold;">${library.libName}</td>
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
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">소속 관리자 정보</h2>
		<div class="member_table_view info_list">
			<table class="mobileTable default">
				<caption>도서관관리자 목록</caption>
				<thead>
					<tr>
						<th scope="row" style="border-right-style: none;" class="footable-first-column">이름(도서관이름)</th>
						<th scope="row" style="border-right-style: none;">아이디</th>
						<th scope="row" style="border-right-style: none;">전화번호</th>
						<th scope="row" style="border-right-style: none;">이메일</th>
						<th scope="row" style="border-right-style: none;" class="footable-last-column" data-hide="phone">승인취소</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${!empty libAdminList}">
							<c:forEach var="list" items="${libAdminList}">
								<tr class="ApvRvoList${list.adminNum}">
									<td class="libcode footable-first-column">
										${list.adminName}
									</td>
									<td>${list.adminId}</td>
									<td>${list.adminTel}</td>
									<td>${list.adminEmail}</td>
									<td class="libhomepage footable-last-column">
										<a class="btn bg_red c_fff" href="javascript:callLibApvRvoPro(${list.adminNum}, '${list.adminId}')">승인취소</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">소속 관리자가 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
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

	<script>
function callLibApvRvoPro(adminNum, adminId) {
	if (typeof adminNum == 'undefined' || adminNum == '' || !adminNum) {
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
					var classID = 'ApvRvoList' + adminNum;
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
					toastr.success("승인취소완료", "승인취소가 완료되었습니다");
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
</script>