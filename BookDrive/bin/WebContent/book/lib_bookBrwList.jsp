<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/lib_loginCheck.jsp"/>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_list.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<!-- [도서관관리자] 도서 대출 목록을 보여주는 페이지 -->
<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">도서관리</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logL" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="BookBrwList.bookL">도서대출내역</a></li>
			</ul>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">대출 진행중</h2>
		<!-- List(책 대출중 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름/저자</th>
						<th scope="row">소장처</th>
						<th scope="row">대출자아이디</th>
						<th scope="row">대출자이름</th>
						<th scope="row">대출일</th>
						<th scope="row">반납예정일</th>
						<th scope="row" class="footable-last-column" data-hide="phone">대출상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${bookBrwList}">
						<c:if test="${list.renIdvDelFlag eq '0'}" >
							<tr>
								<td class="libcode footable-first-column">
									${list.bookName}&nbsp;/&nbsp;${list.bookWriter}
								</td>
								<td>${list.libName}</td>
								<td>${list.memId}</td>
								<td>${list.memName}</td>
								<td>${list.renDate}</td>
								<td>${list.renIdvDate}</td>
								<td class="libhomepage footable-last-column">대출중</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">대출완료</h2>
		<!-- List(책 대출완료 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름/저자</th>
						<th scope="row">소장처</th>
						<th scope="row">대출자아이디</th>
						<th scope="row">대출자이름</th>
						<th scope="row">대출일</th>
						<th scope="row">반납예정일</th>
						<th scope="row" class="footable-last-column" data-hide="phone">대출상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${bookBrwList}">
						<c:if test="${list.renIdvDelFlag eq '1'}">
							<tr>
								<td class="libcode footable-first-column">
									${list.bookName}&nbsp;/&nbsp;${list.bookWriter}
								</td>
								<td>${list.libName}</td>
								<td>${list.memId}</td>
								<td>${list.memName}</td>
								<td>${list.renDate}</td>
								<td>${list.renIdvDate}</td>
								<td class="libhomepage footable-last-column">대출완료</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>