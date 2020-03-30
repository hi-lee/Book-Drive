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
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.selectBox.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
<style>
	.ck-editor__editable {
	       min-height: 400px;
	}
</style>
<div id="divContentsW">
	<div id="divContents_write">
		<h2 id="divTitle">QnA</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="javascript:void();">QnA 답변하기</a></li>
			</ul>
		</div>
		<form name="f" action="BoardRepleWritePro.boardL" method="post">
			<input type="hidden" id="index" name="index" value="${index}">
			<input type="hidden" id="writer" name="writer" value="${id}">
			<input type="hidden" id="boardflag" name="boardflag" value="2">
			<input type="hidden" id="boardnum" name="boardnum" value="${param.boardnum}">
			<input type="hidden" id="page" name="page" value="${param.page}">
			<input type="hidden" id="ref" name="ref" value="${boardBean.boardRef}">
			<input type="hidden" id="lev" name="lev" value="${boardBean.boardLev}">
			<input type="hidden" id="seq" name="seq" value="${boardBean.boardSeq}">
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
						<th>작성자</th>
						<td colspan="3">
							<h3>${sessionScope.id}</h3>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td colspan="3">
							<input type="password" id="pass" name="pass" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea cols="40" rows="8" id="content" name="content"></textarea>
							<script>
							    ClassicEditor
							        .create( document.querySelector( '#content' ) );
						    </script>
						</td>
					</tr>
				</table>
			</div>
			<div class="buttons">
				<a href="javascript:void();" onclick="f.submit();">작성</a>
				<a href="BoardQnADetail.boardL?boardnum=${param.boardnum}&page=${param.page}">돌아가기</a>
			</div>
		</form>
	</div>
</div>