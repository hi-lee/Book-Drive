<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp" />
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
		<h2 id="divTitle">QnA관리</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/list/2">게시판관리</a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/content/2_5030#">자유게시판</a></li>
			</ul>
		</div>
		<div id="divContent">
			<div class="boardDetailW">
				<!-- border view start -->
				<div class="boardDetail">
					<div class="boardInfo">
						<p class="boardInfoTitle">${boardBean_p.boardSubject}</p>
						<p class="writeInfo">
							${boardBean_p.boardDate}<span>조회&nbsp;${boardBean_p.boardRCount}</span>
						</p>
						<dl class="writerInfo">
							<dt class="skip">작성자</dt>
							<dd class="writer">
								<span>${boardBean_p.boardWriter}</span>
							</dd>
							<dt class="skip">이메일</dt>
							<dd class="writerEmail"></dd>

							<!-- BBS 권한 이용하지 않는 게시판일 경우  연락처,기관명/소속 추가 -->
						</dl>
					</div>
					<div class="boardContent">
						${boardBean_p.boardContent}
					</div>
					<!-- add file start -->
					<!-- add file end -->
				</div>
				<!-- 답글 시작 -->
				<c:choose>
				<c:when test="${!empty boardBean_c}">
					<div class="boardDetail">
						<div class="replyDeatil">
							답변
						</div>
						<div class="boardInfo">
							<p class="boardInfoTitle">${boardBean_c.boardSubject}</p>
							<p class="writeInfo">${boardBean_c.boardDate}</p>
							<dl class="writerInfo">
								<dt class="skip">작성자</dt>
								<dd class="writer">
									<span>${boardBean_c.boardWriter}</span>
								</dd>
							</dl>
						</div>
						<div class="boardContent">
							${boardBean_c.boardContent}
							<div class="replyModify"></div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
				</c:choose>
				<div class="buttons">
					<a href="BoardReplyModifyForm.boardA?p_boardnum=${param.boardnum}&boardnum=${boardBean_c.boardNum}&page=${param.page}">
						<c:choose>
							<c:when test="${!empty boardBean_c}">답변수정</c:when>
							<c:otherwise>답변하기</c:otherwise>
						</c:choose>
					</a>
					<a href="BoardList${boardBean_p.boardFlag}.boardA?page=${param.page}">목록</a>
				</div>
				<!-- board button end-->
			</div>
			<!-- boardDetailW End -->
		</div>
		<!-- divContent End -->
		<script type="text/javascript">
			var MSG_COOL_AGAIN = "이미 추천 하셨습니다.";
			var MSG_FAILED_REPLY_SAVE = "댓글 저장에 실패 했습니다.";
			var MSG_LOGIN_FIRST = "이용자의 신분 및 재직상태에 따라 이용 권한이<br>제한된 서비스입니다.<br><br>로그인 후 이용하세요.";
			var MSG_REQUIRED_GRANT = "이용 권한이 존재하지 않습니다.";
			var MSG_USER_IMAGE = "사용자이미지";
			var MSG_WRITER = "작성자";
			var MSG_MAIL = "메일";
			var MSG_DATE = "작성일";
			var MSG_DEL = "삭제";
			var MSG_FAILED_REPLY_DELETE = "댓글 삭제에 실패 했습니다.";
			var MSG_WARN_DELETE = "삭제하시겠습니까?";
			var MSG_SELECT_NODATA = "선택된 데이타가 없습니다.";
			var MSG_MODIFY = "수정되었습니다.";
			var MSG_DELETE = "삭제되었습니다.";
			var MSG_SAVE = "등록되었습니다.";
			var MSG_GUIDE = "원치 않으실 경우 기입하지 않으셔도 됩니다.";

			var JS_IMGPATH = "/image/ko/";

			var FORBID_DELETE_BOARD_MSG = "해당글은 삭제 할 수 없습니다.";
		</script>
		<script type="text/javascript" src="bdstyle/admin/js/svp/bbs/detail.js"></script>
	</div>
</div>