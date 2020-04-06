<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.selectBox.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
<!-- Toastr관련 -->
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<style>
	.ck-editor__editable {
	       min-height: 400px;
	}
</style>
<div id="divContentsW">
	<div id="divContents_write">
		<h2 id="divTitle">글 작성</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>게시판 관리</a></li><li><a href="https://cham.kiu.ac.kr/bbs/list/1#">글 작성</a></li>
			</ul>
		</div>
		<form id="f" name="f" enctype="multipart/form-data" action="BoardModifyPro.bo" method="post">
			<input type="hidden" id="index" name="index" value="${userIndex}">
			<input type="hidden" id="writer" name="writer" value="${userID}">
			<input type="hidden" id="boardnum" name="boardnum" value="${param.boardnum}">
			<input type="hidden" id="page" name="page" value="${param.page}">
			<input type="hidden" id="boardflag" name="boardflag" value="${boardBean.boardFlag}">
			<div class="member_table_info_view">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col style="width:;">
						<col style="width:120px;">
						<col style="width:;">
					</colgroup>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" class="inputTextType5 sw" value="${boardBean.boardSubject}">
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<c:choose>
								<c:when test="${boardBean.boardFlag eq '1'}">
									<h3>공지사항</h3>
								</c:when>
								<c:when test="${boardBean.boardFlag eq '2'}">
									<h3>QnA</h3>
								</c:when>
								<c:when test="${boardBean.boardFlag eq '3'}">
									<h3>희망도서</h3>
								</c:when>
								<c:otherwise>
									<h3>자유게시판</h3>
								</c:otherwise>
							</c:choose>
						</td>
						<th>작성일</th>
						<td>${boardBean.boardDate}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<h3>${boardBean.boardWriter}</h3>	
						</td>
						<th>조회수</th>
						<td>
							<h3>${boardBean.boardRCount}</h3>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td colspan="3">
							<input type="text" id="pass" name="pass" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea cols="40" rows="8" id="content" name="content">${boardBean.boardContent}</textarea>
							<script>
							    ClassicEditor
							        .create( document.querySelector( '#content' ) )
							        .catch( error => {
							            console.error( error );
							        } );
						    </script>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							${boardBean.boardFile}
						</td>
					</tr>
				</table>
				
			</div>
			<div class="buttons">
				<a href="javascript:checkPass('${boardBean.boardPass}');">수정</a>
				<c:choose>
					<c:when test="${boardBean.boardFlag eq '3'}">
						<a href="wishboardDetail.bo?boardNum=${boardBean.boardNum}&page=${param.page}">돌아가기</a>
					</c:when>
					<c:when test="${boardBean.boardFlag eq '2'}">
						<a href="qnaboardDetail.bo?boardNum=${boardBean.boardNum}&page=${param.page}">돌아가기</a>
					</c:when>
					<c:otherwise>
						<a href="freeboardDetail.bo?boardNum=${boardBean.boardNum}&page=${param.page}">돌아가기</a>
					</c:otherwise>
				</c:choose>
				
			</div>
		</form>
	</div>
</div>
<script>
function checkPass(password) {
	var i_pass = $('#pass').val();
	if (i_pass != password) {
		alert('비밀번호가 일치하지 않습니다');
		return false;
	}
	$('#f').submit();
}
</script>