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
		<h2 id="divTitle">자유게시판</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="freeBoard.do">게시판</a></li>
				<li><a href="javascript:void();">자유게시판 상세보기</a></li>
			</ul>
		</div>
		<div id="divContent">
			<div class="boardDetailW">
				<!-- border view start -->
				<div class="boardDetail">
					<div class="boardInfo">
						<p class="boardInfoTitle">${board.boardSubject }</p>
						<p class="writeInfo">
							${board.boardDate }<span>조회&nbsp;${board.boardRCount }</span>
						</p>
						<dl class="writerInfo">
							<dt class="skip">작성자</dt>
							<dd class="writer">
								<span>${board.boardWriter }</span>
							</dd>
							<dt class="skip">이메일</dt>
							<dd class="writerEmail"></dd>

							<!-- BBS 권한 이용하지 않는 게시판일 경우  연락처,기관명/소속 추가 -->
						</dl>
					</div>
					<div class="boardContent">
						${board.boardContent }
					</div>
					<!-- add file start -->
					<c:if test="${board.boardFile ne ''}">
						<div class="additionalItems">
							<div>
								<ul class="addFiles">
									<li><a href="fileDown.boardA?downFile=${board.boardFile}" onclick="window.open(this.href);return false;" title="문서첨부파일">${board.boardFile}</a></li>
								</ul>
							</div>
						</div>
					</c:if>
					<!-- add file end -->
				</div>
				<!-- writer list start -->
				<div class="boardList">
				<c:set var="loop_flag1" value="false" />
				<c:set var="loop_flag2" value="false" />
					<ul>
						<li class="prev">
							<span class="prevWriting">이전글</span>
							<c:forEach var="prev" items="${list}">
								<c:if test="${not loop_flag1}">
									<c:choose>
										<c:when test="${prev.boardNum < param.boardNum}">
											<a href="boardDetail.bo?boardNum=${prev.boardNum}&page=${param.page}" title="이전글">${prev.boardSubject}</a>
											<span class="writeListDate">${prev.boardDate}</span>
											<c:set var="loop_flag1" value="true" />
										</c:when>
										<c:otherwise>
											이전글이 없습니다.
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</li>
						<li class="next">
							<span class="nextWriting">다음글</span>
							<c:forEach var="next" items="${list}">
								<c:if test="${not loop_flag2}">
									<c:choose>
										<c:when test="${next.boardNum > param.boardNum}">
											<a href="boardDetail.bo?boardNum=${next.boardNum}&page=${param.page}" title="다음글">${prev.boardSubject}</a>
											<span class="writeListDate">${prev.boardDate}</span>
											<c:set var="loop_flag2" value="true" />
										</c:when>
										<c:otherwise>
											다음글이 없습니다.
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</li>
					</ul>
				</div>
				<!-- writer list end -->
				<!-- board button start-->
				<div class="buttons">
					<c:choose>
						<c:when test="${board.boardWriter eq sessionScope.userID}">
							<a href="BoardModifyForm.bo?boardnum=${board.boardNum}&page=${param.page}">수정</a>
							<a href="javascript:deleteBoard(${board.boardNum}, ${param.page}, ${board.boardFlag});">삭제</a>
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
					</c:choose>
					<a href="freeBoard.bo?page=${param.page}" title="목록">목록</a>
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