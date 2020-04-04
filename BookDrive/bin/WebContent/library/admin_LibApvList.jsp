<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp" />
<%@page import="java.util.*"%>
<%@page import="vo.admin.LibAdminApv"%>

<link rel="stylesheet"
	href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css"
	media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css"
	href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" type="text/css"
	href="bdstyle/admin/style/ko/svp/bbs/admin_list.css">
<link rel="stylesheet" type="text/css"
	href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js"
	charset="utf-8"></script>
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
		<h2 id="divTitle">도서관 관리자 가입신청 확인</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/list/2">도서관정보</a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/list/1#">도서관 관리자
						가입신청 확인</a></li>
			</ul>
		</div>
		<div id="divContent">
			<!-- Content Header -->
			<h3 class="skip">게시판 안내</h3>
			<!-- Content Num Of Search Result -->
			<div class="listInfo">
				<div class="listInfo1">
					<p class="totalCnt">
						총 <span class="totalcount">102</span> 건
					</p>
					<p class="pageNum">
						,<span>1</span>/6페이지
					</p>
				</div>
			</div>
			<!-- List -->
			<div class="listTable">
				<table class="mobileTable default">
					<caption>게시판 목록</caption>
					<thead>
						<tr>
							<th scope="row" class="footable-first-column">도서관코드</th>
							<th scope="row" data-hide="phone">도서관명</th>
							<th scope="row" data-hide="phone">관리자이름</th>
							<th scope="row" data-hide="phone">관리자아이디</th>
							<th scope="row" data-hide="phone">관리자전화번호</th>
							<th scope="row" class="footable-last-column" data-hide="phone">승인</th>
						</tr>
					</thead>
					<tbody>
						<!-- 도서관 리스트 출력 -->
						<c:forEach var="list" items="${libraryApvList}">
							<tr class="apvlist${list.adminNum}">
								<td class="libcode footable-first-column">${list.libCode}</td>
								<td class="libname expand"><a
									href="LibraryInfo.lib?libcode=${list.libCode}">${list.libName}</a>&nbsp;
								</td>
								<td class="libaddr">${list.adminName}</td>
								<td class="libtel">${list.adminId}</td>
								<td class="libtel">${list.adminTel}</td>
								<td class="libhomepage footable-last-column"><a
									class="btn bg_red c_fff"
									href="javascript:callLibApvPro('${list.adminNum}', '${list.adminId}');">승인</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
function callLibApvPro(adminNum, adminId) {
	if (typeof adminNum == 'undefined' || adminNum == '' || !adminNum) {
		console.log('회원번호가 없습니다.');
		return false;
	}
	
	if (typeof adminId == 'undefined' || adminId == '' || !adminId) {
		console.log('회원아이디가 없습니다.');
		return false;
	}
	$.ajax({
		url : "LibraryApvPro.lib",
		type : "POST",
		dataType : "json",
		data : {
			"adminNum" : adminNum,
			"adminId" : adminId
		},
		success : function(data) {
			if (data != null) {
				if (data.result) {
					var classID = 'apvlist' + adminNum;
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
					toastr.success("승인완료", "승인이 완료되었습니다");
					$('.' + classID).hide();
				}
			}
		},
		error : function(request, status, error) {
			console.log(error);
			toastr.success('승인이 실패했습니다.');
		}
	});
};
</script>