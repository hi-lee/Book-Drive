<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/svp/bbs/admin_list.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<style>
.linkA {
	text-decoration: underline;
	font-weight: bold;
}
</style>
<!-- [통합관리자] 도서관 목록을 출력하는 페이지 -->
<div id="divContentsW">
   <div id="divContents">
<h2 id="divTitle">도서관 목록</h2>
<div id="divLocation">
	<ul>
		<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
		<li><a>도서관정보</a></li><li><a href="javascript:void();">도서관 목록</a></li>
	</ul>
</div>
<div id="divContent">
	<!-- Content Header -->
	<h3 class="skip">게시판 안내</h3>
	<!-- List -->
	<div class="listTable">
		<table class="mobileTable default">
			<caption>게시판 목록</caption>
			<thead>
				<tr>
					<th scope="row" class="footable-first-column">도서관코드</th>
					<th scope="row" data-hide="phone">도서관명</th>
					<th scope="row" data-hide="phone">도서관주소</th>
					<th scope="row" data-hide="phone">대표번호</th>
					<th scope="row" class="footable-last-column" data-hide="phone">홈페이지</th>
				</tr>
			</thead>
			<tbody>
				<!-- 도서관 리스트 출력 -->
				<c:forEach var="list" items="${libraryList}">
					<tr>
						<td class="libcode footable-first-column">
							${list.libCode}
						</td>
						<td class="libname expand">
							<a class="linkA" href="LibraryInfo.lib?libcode=${list.libCode}">${list.libName}</a>&nbsp;
						</td>
						<td class="libaddr">
							(${list.libZip})&nbsp;${list.libAddr1}&nbsp;${list.libAddr2}
						</td>
						<td class="libtel">
							${list.libTel}
						</td>
						<td class="libhomepage footable-last-column">
							${list.libHomePage}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	</div>
</div>