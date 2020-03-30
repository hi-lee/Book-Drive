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
<!-- [도서관관리자] 도서를 등록하는 페이지 -->
<div id="divContentsW">
	<div id="divContents_write">
		<h2 id="divTitle">도서관리</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="BookInsertForm.bookL">도서등록</a></li>
			</ul>
		</div>
			<h1 style="text-align:center;">ISBN번호를 입력해주세요.</h1>
			<div style="text-align: center; margin-bottom: 5px;">
				<input type="text" id="searchisbn" name="searchisbn" class="btnType6">
				<a id="searchISBN" class="btnType5" style="height:35px;">찾기</a>
			</div>
		<form id="f" name="f" action="BookInsert.bookL" method="post">
			<input type="hidden" id="isbn" name="isbn">
			<input type="hidden" id="libcode" name="libcode" value="${sessionScope.code}">
			<input type="hidden" id="bookimage" name="bookimage">
			<div class="member_table_info_view">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col style=";">
						<col style="width:120px;">
						<col style="width:200px;">
					</colgroup>
					<tr>
						<th>카테고리</th>
						<td>
							<select id="si1" name="bookcategory" title="카테고리" class="searchOpt1 selectBox" style="display: none;">
								<option value="000">총류</option>
								<option value="100">철학</option>
								<option value="200">종교</option>
								<option value="300">사회과학</option>
								<option value="400">자연과학</option>
								<option value="500">기술과학</option>
								<option value="600">예술</option>
								<option value="700">언어</option>
								<option value="800">문학</option>
								<option value="900">역사</option>
							</select>
						</td>
						<th rowspan="3">도서이미지</th>
						<td rowspan="3" style="padding:5px;">
							<img src="" id="bookimagesrc" width="100%" height="100%" alt="표지이미지" onerror="this.src='bdstyle/admin/image/ko/solution/common/ico/type_m_M.png'">
						</td>
					</tr>
					<tr>
						<th>도서 이름</th>
						<td>
							<input type="text" id="bookname" name="bookname" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>도서 저자</th>
						<td>
							<input type="text" id="bookwriter" name="bookwriter" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>도서 출판사</th>
						<td colspan="3">
							<input type="text" id="bookpub" name="bookpub" class="inputTextType5 sw">
						</td>
					</tr>
					<tr>
						<th>도서 출판년도</th>
						<td colspan="3">
							<input type="text" id="bookpubdate" name="bookpubdate" class="inputTextType5 sw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
						</td>
					</tr>
				</table>
				
			</div>
			<div class="buttons">
				<a href="javascript:void();" onclick="javascript:checkForm();">작성</a>
				<a href="BoardList${param.flag}.boardA">돌아가기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(function() {
	$('#searchISBN').click(function() {
		if ($('#searchisbn').val() !== undefined && $('#searchisbn').val() !== "") {
	        $.ajax({
	            method: "GET",
	            url: "https://dapi.kakao.com/v3/search/book?target=isbn", // 전송 주소
	            data: { query: $("#searchisbn").val() }, // 보낼 데이터
	            headers: { Authorization: "KakaoAK 7c28f9da096eaa302f600c9900820d6e" },
	            success : function(msg) {
	            	console.log(msg);
	            	if (msg.documents.length !== 0) {
		            	$('#bookname').attr('value', msg.documents[0].title);
		            	$('#bookpub').attr('value', msg.documents[0].publisher);
		            	var pubdate = (msg.documents[0].datetime).substring(0, 4);
		            	$('#bookpubdate').attr('value', pubdate);
		            	var bookwriter = "";
		            	for (var i = 0; i < msg.documents[0].authors.length; i++) { //저자는 배열로 넘어옴
		            		if (i == (msg.documents[0].authors.length - 1)) {
		            			bookwriter += msg.documents[0].authors[i];
		            		} else {
		            			bookwriter += msg.documents[0].authors[i] + ";";
		            		}
		            		$('#bookwriter').attr('value', bookwriter);
		            	}
		            	$('#isbn').attr('value', $('#searchisbn').val());
		            	$('#bookimagesrc').attr('src', msg.documents[0].thumbnail);
		            	$('#bookimage').attr('value', msg.documents[0].thumbnail);
	            	} else {
	            		alert('등록된 도서가 없습니다.');
	            	}
	            },
	            error : function(request, status, error) {
	            	alert(error);
	            }
	        });
		} else {
			alert('ISBN번호를 입력해주세요');
		}
	});
});

function checkForm() {
	if ($("input#bookname").val().trim() == "") {
		alert("도서 이름을 입력해주세요.");
		$("input#bookname").focus();
		return false;
	}
	
	if ($("input#bookwriter").val() == "") {
		alert("도서 저자를 입력해주세요.");
		$("input#bookwriter").focus();
		return false;
	}
	
	if ($("input#bookpub").val() == "") {
		alert("도서 출판사를 입력해주세요.");
		$("input#bookpub").focus();
		return false;
	}
	
	if ($("input#bookpubdate").val() == "") {
		alert("도서 출판년도를 입력해주세요.");
		$("input#bookpubdate").focus();
		return false;
	}
	
	$('#f').submit();
}
</script>