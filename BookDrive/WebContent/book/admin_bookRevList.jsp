<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/jquery.ui.custom.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_info.css">
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_list.css">
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.core.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/ui.datepicker.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<!-- [통합관리자] 도서 예약 목록을 보여주는 페이지 -->
<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">도서예약내역</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logC" title="HOME"><img src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>도서관리</a></li><li><a href="javascript:void();">도서예약내역</a></li>
			</ul>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">예약 진행중</h2>
		<!-- List(책 예약중 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름/저자</th>
						<th scope="row">소장처</th>
						<th scope="row">예약자아이디</th>
						<th scope="row">예약자이름</th>
						<th scope="row">예약일</th>
						<th scope="row">예약만료일</th>
						<th scope="row" class="footable-last-column" data-hide="phone">예약상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${bookRevList}">
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
								<td class="libhomepage footable-last-column">진행중</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h2 style="margin: 28px 0 14px 0;color:#4b4b4b;font-size:16px;font-weight:600;">예약완료</h2>
		<!-- List(책 예약완료 리스트) -->
		<div class="listTable">
			<table class="mobileTable default">
				<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th scope="row" class="footable-first-column">책이름/저자</th>
						<th scope="row">소장처</th>
						<th scope="row">예약자아이디</th>
						<th scope="row">예약자이름</th>
						<th scope="row">예약일</th>
						<th scope="row" class="footable-last-column" data-hide="phone">예약상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${bookRevList}">
						<c:if test="${list.renIdvDelFlag eq '1'}">
							<tr>
								<td class="libcode footable-first-column">
									${list.bookName}&nbsp;/&nbsp;${list.bookWriter}
								</td>
								<td>${list.libName}</td>
								<td>${list.memId}</td>
								<td>${list.memName}</td>
								<td>${list.renDate}</td>
								<td class="libhomepage footable-last-column">예약만료</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>