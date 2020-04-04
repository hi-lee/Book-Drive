<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
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
<!-- [통합관리자] 도서관 관리자 목록을 출력하는 페이지 -->
<div id="divContentsW">
   <div id="divContents">
<h2 id="divTitle">도서관 관리자 목록</h2>
<div id="divLocation">
	<ul>
		<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
		<li><a>회원관리</a></li><li><a href="javascript:void();">도서관 관리자 목록</a></li>
	</ul>
</div>
<div id="divContent">
	<!-- Content Header -->
	<h3 class="skip">게시판 안내</h3>
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
</script>