<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="./상세정보 _ 경일대학교 도서관_files/cookie.js.다운로드" charset="utf-8"></script>

<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/detail.css">
<link rel="stylesheet" type="text/css"
href="bdstyle/style/ko/home/toastr.min.css">
<script type="text/javascript" src="bdstyle/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/js/common/toastr.min.js"></script>
<div id="divContentsW">
	<div id="divContents">


		<h2 id="divTitle">상세정보</h2>

		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li>상세정보</li>
			</ul>
		</div>

		<div id="divContent">
			<!-- //검색상세 -->
			<div class="searchDetail">
				<div class="searchLink">
					<a class="returnResult"
						href="bookSearchPro.bk?page=${param.page }&search=${param.search}&value=${param.value}"
						title="검색결과 돌아가기"> <img
						src="bdstyle/image/ko/local/bookimages/profileArrow.png"> <span>검색결과
							돌아가기</span></a>

					<form name="frmMylist"
						action="https://cham.kiu.ac.kr/mylist/pop/writeitem" method="post">
						<input type="hidden" name="data" value="CAT000000155481">
					</form>

					<ul class="searchLinkBtn">
						<li class="basketSelect"><a href="javascript:addBookCart(${book.bookNum},'${book.libCode}',${sessionScope.userIndex});">바구니넣기</a></li>
					</ul>
				</div>
				<!-- //서지정보 -->
				<div class="profile">
					<div class="profileHeader">
						<h3>${book.bookName }</h3>
						<p>${book.bookWriter }저</p>
					</div>
					<div class="profileContent">

						<div class="briefInfo">
							<dl>
								<dt>책이미지</dt>
								<dd class="bookImg">
									<img id="coverimage"
										src="${book.bookImage}" width="120"
										height="153" alt="표지이미지" onerror="this.src='bdstyle/admin/image/ko/solution/common/ico/type_m_M.png'">
								</dd>
								<dd class="bookInfoBtn"></dd>
								<dd class="bookInfoBtn2"></dd>
							</dl>
							<span class="bookBg"></span>
						</div>

						<div class="table">
							<div id="divProfile">
								<table class="profiletable">
									<caption class="dpn">상세정보</caption>
									<tbody>
										<tr>
											<th scope="row">자료유형</th>
											<td>도서</td>
										</tr>
										<tr>
											<th scope="row">서명/저자사항</th>
											<td>${book.bookName }/${book.bookWriter }저;옮긴이옮김.</td>
										</tr>

										<tr>
											<th scope="row">발행사항</th>
											<td>${book.bookPub },${book.bookPubDate }.</td>
										</tr>
										<tr>
											<th scope="row">ISBN</th>
											<td>${book.ISBN }<br></td>
										</tr>
										<tr>
											<th scope="row">분류기호</th>
											<td>853 <br></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!-- //소장정보 -->

				<div class="searchInfo">
					<div class="searchHeader">
						<h3 class="open">소장정보</h3>
					</div>
					<div class="searchContents">
						<div class="listTable">
							<table class="searchTable default">

								<caption>메세지가 없습니다</caption>
								<thead>
									<tr>
										<th scope="row" data-class="expand"
											class="footable-first-column">No.</th>
										<th scope="row" data-hide="phone">등록번호</th>
										<th scope="row" data-hide="phone">ISBN</th>
										<th scope="row" data-hide="expand">소장처</th>
										<th scope="row" data-hide="expand">도서상태</th>
										<th scope="row" data-hide="expand">대출</th>
										<th scope="row" data-hide="phone">반납예정일</th>
										<th scope="row" colspan="2">예약</th>
									</tr>
								</thead>
								<tbody>
									<tr class="first">
										<td class="num expand footable-first-column">1</td>
										<td class="accessionNo">${book.bookNum }</td>
										<td class="ISBN">${book.ISBN }</td>
										<td class="location"><c:choose>
												<c:when test="${book.libCode == 'LIB001' }">대구광역시립 중앙도서관</c:when>
												<c:when test="${book.libCode == 'LIB002' }">대구광역시립 남부도서관</c:when>
												<c:otherwise>대구광역시립 수성도서관</c:otherwise>
											</c:choose></td>
										<td><span class="status available"> <!-- 0대출가능 1대출중 2예약가능 3예약중 4예약불가   -->
												<c:choose>
													<c:when test="${book.bookState == '0' }">대출가능</c:when>
													<c:when test="${book.bookState == '1' }">대출중</c:when>
													<c:when test="${book.bookState == '2' }">예약중 </c:when>
													<c:when test="${book.bookState == '4' }">관외대출중 </c:when>
													<c:when test="${book.bookState == '5' }">관외예약중 </c:when>
													<c:when test="${book.bookState == '6' }">대출중(예약중) </c:when>
													<c:when test="${book.bookState == '7' }">대출중(관외예약중) </c:when>
													<c:when test="${book.bookState == '8' }">괸외대출중(관외예약중) </c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
										</span></td>
										<td><span class="status available"> <c:choose>
													<c:when test="${book.bookState == '0' }">
														<a
															href="bookRental.bk?page=${param.page }&search=${param.search}&value=${param.value}&id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${book.bookNum}&libCode=${book.libCode}&state=${book.bookState}">
															대출하기</a>
													</c:when>
													<c:when
														test="${book.bookState == '1' || book.bookState == '2'|| book.bookState == '6'|| book.bookState == '7'|| book.bookState == '8'}">대출불가 </c:when>
												</c:choose>
										</span></td>
										<td class="returnDate">
											<!-- renFlag: brw && DelFlag: 0(대출0반납X) --> <!--<c:if test="${rentalIdv.renFlag == 'brw' && rentalIdv.renIdvDelFlag == '0'}">${rentalIdv.renBrwInvDate } </c:if>-->
											${rentalIdv.renBrwInvDate }
										</td>
										<td>
										<c:choose>
											<c:when test="${book.bookState =='1'}">
												<a
													href="bookRev.bk?page=${param.page }&search=${param.search}&value=${param.value}&id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${book.bookNum}&libCode=${book.libCode}&state=${book.bookState}"
													class="reservation available"> 예약하기 </a>
											</c:when>
											<c:when test="${book.bookState =='6' || book.bookState == '7'|| book.bookState == '8'}">예약불가</c:when>
											</c:choose>
											</td>
										<td><c:if
												test="${book.bookState =='0' || book.bookState =='1' || book.bookState =='4'}">
												<a
													href="bookRev.bk?page=${param.page }&search=${param.search}&value=${param.value}&id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${book.bookNum}&libCode=${book.libCode}&state=${book.bookState}&usage=true"
													class="reservation available"> 관외예약 </a>
											</c:if> <%--	<c:when test="${book.bookState == '2' }">관내예약불가 </c:when>  --%>
										</td>
										
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- //listTable -->
				</div>
				<!-- //searchInfo -->

				<!-- //소장정보 -->

				<!-- //매체정보 -->

				<!-- // 초록 -->

				<!-- //목차정보 -->

				<!-- //NAVER 정보 -->


				<div class="searchInfo mediaContents" id="naverSearchInfo"
					style="display: block;">
					<div class="searchHeader">
						<h3 class="open">책소개</h3>
					</div>
					<div class="searchContents">
						<div class="mediaContent">
							<h4 class="skip">네이버정보 미리보기</h4>
							<div class="brief" id="naverBrief">
								<a href="http://book.naver.com/bookdb/book_detail.php?bid=5186"
									title="새창" target="_blank"># </a>
							</div>
							<p class="bookintroLink" id="naverLink">
								<a href="http://book.naver.com/bookdb/book_detail.php?bid=5186"
									target="_blank" title="새창">[네이버 제공]</a>
							</p>
						</div>
					</div>
				</div>
				<script type="text/javascript">
				function addBookCart(bookNum, libCode, userIndex) {
					$.ajax({
						url : 'myBookCartAdd.bk',
						type : 'POST',
						dataType : 'json',
						data : {
							"bookNum" : bookNum,
							"libCode" : libCode,
							"userIndex" : userIndex
						},
						success : function(data) {
							console.log('성공');
							toastr.options = {
								"closeButton" : false,
								"debug" : false,
								"newestOnTop" : false,
								"progressBar" : true,
								"positionClass" : "toast-bottom-center",
								"preventDuplicates" : false,
								"onclick" : null,
								"showDuration" : "300",
								"hideDuration" : "1000",
								"timeOut" : "3000",
								"extendedTimeOut" : "1000",
								"showEasing" : "swing",
								"hideEasing" : "linear",
								"showMethod" : "fadeIn",
								"hideMethod" : "fadeOut"
							}
							toastr.success("보관함에 추가되었습니다", "보관함");
						},
						error : function(request, status, error) {
							console.log('실패');
							alert('장바구니에 추가가 실패했습니다');
						}
					})
				}
				
					$(document).ready(function() {
						naverBook("데미안", "8937460440");
					});

					function naverBook(keyword, isbn) {
						$
								.ajax({
									contentType : "application/x-www-form-urlencoded; charset=UTF-8",
									type : "POST",
									url : "/openapi/naverBook",
									data : "query="
											+ encodeURIComponent(keyword)
											+ "&display=1&d_isbn="
											+ encodeURIComponent(isbn)
											+ "&target=book_adv",
									dataType : "xml",
									complete : function(xhr, statusText) {
										if (statusText == "success") {
											viewNaverBook(xhr, keyword);
										} else {
											$("#naverBrief")
													.append("API 호출 실패");
										}
									}
								});
					}

					function viewNaverBook(xhr, keyword) {
						$("#naverSearchInfo").hide();
						var xmlObj = xhr.responseXML;
						var arrList = $("item", xmlObj);
						if (arrList != null) {
							var listStr = "";
							var listSize = arrList.length;
							if (listSize == 0) {
								return;
							}
							for (var i = 0; i < listSize; i++) {
								var item = arrList[i];
								listStr = "<a href='"
										+ $("link", item)[0].firstChild.nodeValue
										+ "'  title='새창' target='_blank' >"
										+ $("description", item)[0].firstChild.nodeValue
										+ "</a>";
							}
							if (listStr != "") {
								$("#naverBrief").append(listStr);
								$("#naverLink")
										.append(
												"<a href="
														+ $('link', item)[0].firstChild.nodeValue
														+ " target=\"_blank\" title=\"새창\">[네이버 제공]</a>");
								$("#naverSearchInfo").show();
							}
						}
					}
				</script>
			</div>
		</div>
	</div>
</div>

