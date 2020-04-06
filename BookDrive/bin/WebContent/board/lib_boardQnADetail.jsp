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
		<h2 id="divTitle">QnA</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="BoardList2.boardL?page=${param.page}&libcode=${sessionScope.code}">QnA 리스트</a></li>
				<li><a href="javascript:void();">상세보기</a></li>
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
					<div class="boardDetail">
						<div class="replyDeatil" style="text-align: center;">
							답변이 없습니다.
						</div>
					</div>
				</c:otherwise>
				</c:choose>
				<div class="buttons">
					<c:choose>
						<c:when test="${!empty boardBean_c}">
							<a href="BoardReplyModifyForm.boardL?p_boardnum=${param.boardnum}&boardnum=${boardBean_c.boardNum}&page=${param.page}">답변수정</a>
						</c:when>
						<c:otherwise>
							<a href="BoardRepleWriteForm.boardL?boardnum=${param.boardnum}&page=${param.page}">답변하기</a>
						</c:otherwise>
					</c:choose>
					<a href="BoardList${boardBean_p.boardFlag}.boardL?libcode=${sessionScope.code}&page=${param.page}">목록</a>
				</div>
				<!-- board button end-->
			</div>
			<!-- boardDetailW End -->
		</div>
		<!-- divContent End -->
		<script type="text/javascript">
		function deleteBoard(boardnum, page, flag, c_boardnum) { //본문글과 답글 함께 삭제
			alert(boardnum, page, flag, c_boardnum);
			let isDeleteCheck = confirm("글을 삭제하시겠습니까?\n(답글도 같이 삭제됩니다)");
			if (isDeleteCheck) {
				location.href='BoardDeletePro.boardA?boardnum='+boardnum+'&page='+page+'&flag='+flag+'&cboardnum='+c_boardnum;
				console.log('c_boardnum'+c_boardnum);
			} else {
				toastr.error('취소하였습니다.', '삭제 취소!');
			}
		}
		</script>
		<script type="text/javascript" src="bdstyle/admin/js/svp/bbs/detail.js"></script>
	</div>
</div>