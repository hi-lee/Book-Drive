<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/list.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="divContentsW">
		<div id="divContents">
			<script type="text/javascript" src="/js/common/cookie.js"
				charset="utf-8"></script>
			<h2 id="divTitle">검색 History</h2>

			<div id="divLocation">
				<ul>
					<li><a href="template_main.jsp" title="HOME"><img
							src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
					<li>검색 History</li>
				</ul>
			</div>
			<form id="historyFrm" name="historyFrm" method="post"
				onsubmit="return checked(this)" action="bookSearchPro.bk">
				<input type="hidden" name="search" value=""> <input
					type="hidden" name="value" value="">
				<div id="divContent">
					<div class="listTable">
						<table class="mobileTable default">
							<caption>검색 History</caption>
							<thead>
								<tr>
									<th scope="row" class="footable-first-column">선택</th>
									<th scope="row">No.</th>
									<th scope="row">검색종류</th>
									<th scope="row" data-class="expand">검색어</th>
									<th scope="row" data-hide="phone">검색범위</th>
								</tr>
							</thead>

							<c:choose>
								<c:when test="${searchList != null }">

									<c:forEach items="${searchList }" var="list" varStatus="hlist">
										<input type="hidden" name="tempSearch" value="${list.value.search }">
										<input type="hidden" name="tempValue" value="${list.value.value }">
										<c:set var="search" value="" />
										<c:choose>
											<c:when test="${list.value.search eq 'bookName' }">
												<c:set var="searchV" value="서명" />
											</c:when>
											<c:when test="${list.value.search eq 'bookWriter' }">
												<c:set var="searchV" value="저자" />
											</c:when>
											<c:when test="${list.value.search eq 'bookPub' }">
												<c:set var="searchV" value="출판사" />
											</c:when>
											<c:when test="${list.value.search eq 'ISBN' }">
												<c:set var="searchV" value="ISBN" />
											</c:when>
										</c:choose>
										<tbody>
											<tr>
												<td class="checkbox footable-first-column"><input
													type="radio" name="history" value="" title="검색 History"></td>
												<td class="num">${hlist.index +1}</td>
												<td class="search_kind">키워드</td>
												<td class="display_query expand">[키워드 / ${searchV }:${list.value.value }]</td>
												<td class="search_range">소장자료검색</td>
											</tr>
										</tbody>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tbody>
										<tr>
											<td colspan="6"
												class="footable-last-column footable-first-column">결과가
												없습니다.</td>
										</tr>
									</tbody>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
					<div class="buttons">
						<input type="submit" name="submit" value="검색" title="검색" >
					</div>
				</div>
			</form>
			<!--  -->
			<!-- <script type="text/javascript" src="/js/common/Checkbox.js"></script> -->

			<script type="text/javascript">
				function checked(form) {
					for (var i = 0; i < form.history.length; i++) {
						if(form.history[i].checked){
							form.search.value = form.tempSearch[i].value;
							form.value.value = form.tempValue[i].value;
						}
					}

				}
			</script>
		</div>
	</div>
</body>
</html>