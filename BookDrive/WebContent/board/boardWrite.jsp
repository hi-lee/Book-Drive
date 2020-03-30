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
				<li><a>게시판 관리</a></li><li><a href="javascript:void();">글 작성</a></li>
			</ul>
		</div>
		<form name="f" enctype="multipart/form-data" action="freeBoardWritePro.bo" method="post">
			<input type="hidden" id="index" name="index" value="${userIndex}">
			<input type="hidden" id="writer" name="writer" value="${userID}">
			<div class="member_table_info_view">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col style="width:;">
						<col style="width:120px;">
						<col style="width:;">
					</colgroup>
					<tr>
						<th>카테고리</th>
						<td colspan="3">
							<select id="si1" name="boardgubun" title="게시판 선택" class="searchOpt1 selectBox" style="display: none;">
								<option value="4">자유게시판</option>
								<option value="2">QnA</option>
								<option value="3">도서이용신청</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td colspan="3">
							<h3>${sessionScope.userID}</h3>
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
							        .create( document.querySelector( '#content' ) )
							        .catch( error => {
							            console.error( error );
							        } );
						    </script>
						</td>
					</tr>
					<tr>
						<th>파일첨부</th>
						<td colspan="3">
							<input type="file" id="file" name="file" class="inputFileType1">
						</td>
					</tr>
				</table>
				
			</div>
			<div class="buttons" style="padding-bottom: 25px;">
				<a href="javascript:void();" onclick="f.submit();">작성</a>
				<a href="javascript:history.back();">돌아가기</a>
			</div>
		</form>
	</div>
</div>