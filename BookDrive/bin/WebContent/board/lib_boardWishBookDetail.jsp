<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../bdstyle/admin/include/lib_loginCheck.jsp" />
<link rel="stylesheet" href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/svp/bbs/admin_list.css">
<link rel="stylesheet" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<link rel="stylesheet"href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<!--  -->
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_detail.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/jquery.mCustomScrollbar.css">

<script type="text/javascript" src="bdstyle/admin/js/common/ajaxCommon.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/jquery.bgiframe.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.mThumbnailScroller.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery-ui.js"></script>
<!--  -->
<script type="text/javascript" src="bdstyle/admin/js/common/AC_RunActiveContent.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/jquery.listbox.js"></script>
<div id="divContentsW">
	<div id="divContents">
		<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js"
			charset="utf-8"></script>
		<h2 id="divTitle">희망도서</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="BoardList3.boardL?page=${page}">희망도서 리스트</a></li>
				<li><a href="javascript:void();">상세보기</a></li>
			</ul>
		</div>
		<div id="divContent">
			<div class="boardDetailW">
				<!-- border view start -->
				<div class="boardDetail">
					<div class="boardInfo">
						<p class="boardInfoTitle">${boardBean.boardSubject}</p>
						<p class="writeInfo">
							${boardBean.boardDate}<span>조회&nbsp;${boardBean.boardRCount}</span>
						</p>
						<dl class="writerInfo">
							<dt class="skip">작성자</dt>
							<dd class="writer">
								<span>${boardBean.boardWriter}</span>
							</dd>
							<dt class="skip">이메일</dt>
							<dd class="writerEmail"></dd>

							<!-- BBS 권한 이용하지 않는 게시판일 경우  연락처,기관명/소속 추가 -->
						</dl>
					</div>
					<div class="boardContent">
						${boardBean.boardContent}
					</div>
				</div>
				<!-- board button start-->
				<div class="buttons">
					<a href="BoardList3.boardL?libcode=${sessionScope.code}&page=${page}" title="목록">목록</a>
				</div>
				<!-- board button end-->
			</div>

			<!-- boardDetailW End -->
		</div>
		<!-- divContent End -->
		<script type="text/javascript" src="bdstyle/admin/js/svp/bbs/detail.js"></script>
	</div>
</div>