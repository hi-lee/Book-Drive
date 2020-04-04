<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<%@page import="java.util.*"%>
<%@page import="vo.admin.Library"%>
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
<!-- [통합관리자] 도서관 관리자 가입신청을 출력하는 페이지 -->
<div id="divContentsW">
	<div id="divContents_write">
		<h2 id="divTitle">글 작성</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>게시판 관리</a></li><li><a href="https://cham.kiu.ac.kr/bbs/list/1#">글 작성</a></li>
			</ul>
		</div>
		<form name="f" enctype="multipart/form-data" action="BoardModifyPro.boardA" method="post">
			<input type="hidden" id="index" name="index" value="${index}">
			<input type="hidden" id="writer" name="writer" value="${id}">
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
							<input type="text" id="pass" name="pass" class="inputTextType5 sw" value="${boardBean.boardPass}">
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
				</table>
				
			</div>
			<div class="buttons">
				<a href="javascript:void();" onclick="f.submit();">수정</a>
				<a href="javascript:deleteBoard(${param.boardnum}, ${param.page}, ${boardBean.boardFlag});">삭제</a>
				<a href="BoardList${boardBean.boardFlag}.boardA?page=${param.page}">돌아가기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
function deleteBoard(boardnum, page, flag) {
	var isDeleteCheck = confirm("글을 삭제하시겠습니까?");
	if (isDeleteCheck) {
		location.href='BoardDeletePro.boardA?boardnum='+boardnum+'&page='+page+'&flag='+flag;
	} else {
		toastr.error('취소하였습니다.', '삭제 취소!');
	}
}
$(function() {
	$("a#memBookName").click(function() {
		if($("tr#memRev").is(':visible') == false) {
			$("tr#memRev").show();
		} else {
			$("tr#memRev").hide();
		}
	})
})
$(document).ready(function () { //책이름은 기본적으로 노출이 되지 않음.
	$("tr#memRev").hide();
});

$(document).ready(function(){
	//$("#dateFrom").val($.datepicker.formatDate('yymmdd', new Date(2009, 1 - 1, 18)));
	// $("#dateFrom").datepicker({defaultDate: new Date(2009, 1 - 1, 18)});
	//$("#dateTo").val($.datepicker.formatDate('yymmdd', new Date(2009, 1 - 1, 27)));
	//$("#dateTo").datepicker({defaultDate: new Date(2009, 1 - 1, 27)});
	$("#dateFrom").datepicker();
	$("#dateTo").datepicker();

	var subjectUse = 'N';
	if(subjectUse == 'Y') {
		var subjectCode = '';
		$("#subject_code > option[value=" + subjectCode + "]").attr("selected", "true");
	}
	
	$("#ui-datepicker-div").css('z-index','1000');
	/*
	var policyYn = 'N';
	if (policyYn == 'Y' && policy_content){
		//alert("게시판 이용 시 '주민등록번호'등과 같은 개인정보를 등록할 경우 제3자에 의해 피해를 입을 수 있습니다. 개인정보 등록 여부를 확인 하셨습니까?");
		alert(policy_content);
	}
	*/
});

//읽기 권한이 없는경우
function cantReadContent(){
	alert('읽을 권한이 없습니다.');
	return;
}

//비공개글일 경우
function getNoPublicContent(){
	alert('이 글은 비공개로 설정되어 있습니다.');
	return;
}

function validate(fname){
    var digits="0123456789";
    var temp;
    
    var tsc = eval("document.searchFrm."+fname);
        for (var i=0;i<tsc.value.length;i++){
            temp=tsc.value.substring(i,i+1);
        if (digits.indexOf(temp)==-1){
            alert("숫자만 입력 하세요"); 
            tsc.value = "";
            return false;
            }
         }
        return true; 
}   
</script>