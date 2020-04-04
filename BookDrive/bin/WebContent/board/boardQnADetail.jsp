<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="bdstyle/style/ko/standard/detail.css">
<link rel="stylesheet" href="bdstyle/style/ko/home/toastr.min.css">
<link rel="stylesheet"href="bdstyle/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<!--  -->
<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/standard/admin_detail.css">
<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/jquery.mCustomScrollbar.css">

<script type="text/javascript" src="bdstyle/js/common/ajaxCommon.js"></script>
<script type="text/javascript" src="bdstyle/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/js/common/jquery.bgiframe.js"></script>
<script type="text/javascript" src="bdstyle/js/common/toastr.min.js"></script>
<script type="text/javascript" src="bdstyle/script/jquery.mThumbnailScroller.js"></script>
<script type="text/javascript" src="bdstyle/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/script/jquery-ui.js"></script>
<!--  -->
<script type="text/javascript" src="bdstyle/js/common/AC_RunActiveContent.js"></script>
<script type="text/javascript" src="bdstyle/js/common/jquery.listbox.js"></script>
<div id="divContentsW">
	<div id="divContents">
		<script type="text/javascript" src="bdstyle/js/common/cookie.js"
			charset="utf-8"></script>
		<h2 id="divTitle">도서이용신청</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="wishBoard.do">게시판</a></li>
				<li><a href="javascript:void();">도서이용신청 상세보기</a></li>
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
						<c:when test="${empty boardBean_c}">
							<a href="BoardModifyForm.bo?boardnum=${param.boardNum}&page=${param.page}">질문글수정</a>
							<a href="javascript:deleteBoard(${param.boardNum}, ${param.page}, ${boardBean_p.boardFlag}, ${boardBean_c.boardNum});">질문글삭제</a>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose>
					
					<a href="qnaBoard.bo?page=${param.page}">목록</a>
				</div>
				<!-- board button end-->
			</div>
			<!-- boardDetailW End -->
		</div>
		<!-- divContent End -->
		<script type="text/javascript" src="bdstyle/js/svp/bbs/detail.js"></script>
	</div>
</div>
<script type="text/javascript">
function deleteBoard(boardnum, page, flag) {
	var isDeleteCheck = confirm("글을 삭제하시겠습니까?");
	if (isDeleteCheck) {
		location.href='BoardDeletePro.bo?boardnum='+boardnum+'&page='+page+'&flag='+flag;
	} else {
		toastr.error('취소하였습니다.', '삭제 취소!');
	}
}
</script>