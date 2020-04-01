<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/brief.css">
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/toastr.min.css">
<script type="text/javascript" src="bdstyle/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/js/common/toastr.min.js"></script>

<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">전체</h2>
		<div id="divLocation">
			<ul>
				<li><a href="template_main.jsp" title="HOME"> <img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="#">소장자료검색</a></li>
				<li><a href="#">전체</a></li>
			</ul>
		</div>

		<!-- content -->
		<div id="divContent">
			<div class="searchBrief">
				<div class="dataSearch">
					<form name="refineSearch" method="get" action="bookSearchPro.bk">
						<fieldset>

							<legend>검색항목</legend>

							<select id="si1" name="search" title="검색 항목을 선택하는곳"
								class="searchOpt1 selectBox" style="display: none;">
								<option value="TOTAL">전체</option>
								<option value="bookName" selected="selected">서명</option>
								<option value="bookWriter">저자</option>
								<option value="bookPub">출판사</option>
								<option value="ISBN">ISBN</option>
							</select> <a href="#" class="selectBox searchOpt1 selectBox-dropdown hide"
								title="검색 항목을 선택하는곳" tabindex="NaN" name="search"
								style="width: 118px; display: block;" disabled="disabled"><span
								class="selectBox-label" style="width: 74px;">전체</span><span
								class="selectBox-arrow"></span></a> <select id="si2" name="search"
								title="검색 항목을 선택하는곳" class="searchOpt1 hide selectBox"
								disabled="disabled" style="display: none;">
								<option value="TOTAL">전체</option>
								<option value="bookName" selected="selected">서명</option>
								<option value="bookWriter">저자</option>
								<option value="bookPub">출판사</option>
								<option value="ISBN">ISBN</option>
							</select><a href="#"
								class="selectBox searchOpt1 hide selectBox-disabled selectBox-dropdown"
								title="검색 항목을 선택하는곳" tabindex="NaN"
								style="width: 118px; display: none;" disabled="disabled"><span
								class="selectBox-label" style="width: 74px;">전체</span><span
								class="selectBox-arrow"></span></a>
							<div class="searchW">

								<span class="keyword"><input type="text" name="value"
									title="검색어를 입력하세요" placeholder="${ value }"></span> <input
									type="submit" value="검색" class="searchBtn">

							</div>
						</fieldset>
					</form>
				</div>
				<!-- //dataSearch -->

				<div class="searchWords">
					<dl class="searchKeyword">
						<dt>검색어</dt>
						<dd>
							<c:set var="search" value="" />
							<c:choose>
								<c:when test="${param.search eq 'bookName' }">
									<c:set var="search" value="서명" />
								</c:when>
								<c:when test="${param.search eq 'bookWriter' }">
									<c:set var="search" value="저자" />
								</c:when>
								<c:when test="${param.search eq 'bookPub' }">
									<c:set var="search" value="출판사" />
								</c:when>
								<c:when test="${param.search eq 'ISBN' }">
									<c:set var="search" value="ISBN" />
								</c:when>
							</c:choose>
							<span class="keyword">[키워드 / ${search }:${param.value }]</span>
						</dd>
					</dl>
					<ul class="selectedFacet">
					</ul>
				</div>

				<div class="briefContent">

					<!-- //facet -->

					<div class="result">
						<div class="resultHeader">
							<p class="searchCnt">

								총<strong> ${pageInfo.listCount }</strong>건 출력
							</p>
							<div class="resultInfo">
								<div class="listInfo1">
									<div class="breifPaging">

										<span class="current">${((pageInfo.page-1)*5) + 1 }</span> -
										${((pageInfo.page-1)*5) + 6 } <a
											href="bookSearchPro.bk?page=${pageInfo.page + 1 }&search=${param.search}&value=${param.value}"
											class="lastPage" title="다음페이지로"> <img
											src="bdstyle/image/ko/local/bookimages/breifLast.png"
											alt="다음페이지로"></a>

									</div>
								</div>
								<div class="listInfo2">
									<div class="searchBtns">
										<c:set var="cartNum" value="00" />
										<c:choose>
											<c:when test="${cartList != null }">
												<c:set var="cartNum" value="${fn:length(cartList) }" />
											</c:when>
											<c:otherwise>
												<c:set var="cartNum" value="0" />
											</c:otherwise>
										</c:choose>

										<ul>
											<li class="myHistory"><a
												href="/search/service/searchHistory" title="검색 History"><img
													src="bdstyle/image/ko/solution/common/btn/searchBtn03.png"
													alt="검색 History"></a></li>
											<li class="myCart"><a
												href="bookCartList.bk?index=${sessionScope.userIndex }&page=${param.page }&search=${param.search}&value=${param.value}"
												title="도서보관함 "> <img
													src="bdstyle/image/ko/solution/common/btn/searchBtn04.png"
													alt="바구니보기"><span class="cnt" id="basket_count">${cartNum }</span>
											</a></li>
										</ul>
									</div>
									<div class="viewOptions">
										<p class="typeSelect">
											<a href="javascript:void(0);"> <img
												src="/image/ko/solution/common/ico/calNormalOn.png" alt="목록">

												<img src="/image/ko/solution/common/ico/calTypeOpen.png"
												alt="열기"></a>
										</p>

									</div>
								</div>
							</div>
							<div class="briefHeader sort">

								<div class="searchBtns2"></div>

								<div class="sortOptions"></div>
							</div>
							<!-- //listInfo -->
						</div>
						<form name="briefFrm" method="post"
							action="https://cham.kiu.ac.kr/search/brief/service"
							onsubmit="return checked(this);">
							<fieldset>
								<legend>Service Form</legend>
								<ul class="resultList resultDetail">
									<c:set var="replace_hilight"
										value="<span class=hilight>${param.value}</span>" />
									<c:forEach var="list" items="${bookList }" varStatus="status">
										<c:set var="bookname"
											value="${fn:replace(list.bookName, param.value, replace_hilight)}" />
										<li id="item_CATTOT000000498079" class="items">
											<dl>
												<dt class="title">No</dt>
												<dd class="dataCheck">${status.index + ((pageInfo.page-1)*5) + 1 }.</dd>
												<dt class="title">표지이미지</dt>

												<dd class="book">
													<a href="#"> <img id="bookImg_CATTOT000000498079"
														src="${list.bookImage }" width="75" height="103"
														alt="표지이미지">
													</a>

													<p class="selectBtn">
														<a id="basket_CAT000000498079"
															href="javascript:addBookCart('${list.bookNum}','${list.libCode}','${sessionScope.userIndex}');"
															class="cartBtn " title="바구니 담기">바구니 담기</a>
													</p>
												</dd>

												<dt class="title">서명</dt>
												<dd class="title">
													<a
														href="bookInfo.bk?page=${pageInfo.page }&bookNum=${list.bookNum}&search=${param.search}&value=${param.value}">
														${bookname } : ${list.bookWriter }


														<button type="button" title="상세보기"
															onclick="previewDetail('CATTOT000000498079')">상세보기</button>

														<input type="button" class="addItem btnType2"
														onclick="addItem('data=CAT000000498079');" title="추가"
														value="추가" style="display: none;">
												</dd>
												<!-- 	<dt class="bkNum">책번호</dt>
											<dd class="info">${list.bookNum }</dd>  -->
												<dt class="title">저자</dt>
												<dd class="info">지은이 : ${list.bookWriter }</dd>
												<dt class="title">출판사</dt>
												<dd class="info">${list.bookPub }</dd>
												<dt class="title">청구기호</dt>
												<dt class="title">출판년</dt>
												<dd class="info">${list.bookPubDate }</dd>
												<dt class="title">미리보기</dt>
												<dd class="preview">
													<div id="prevDetail_CATTOT000000498079" class="detailInfo"
														style="display: none;"></div>
												</dd>
												<dt class="title">자료유형</dt>
												<dd class="type info">
													<img alt="도서" title="도서"
														src="bdstyle/image/ko/solution/common/ico/type_m.png">
													도서
												</dd>

												<dt class="title">매체정보</dt>
												<dd class="media"></dd>
												<dt class="title">소장처 정보</dt>
												<dd class="holdingInfo">

													<div class="holding">
														<p class="location">
															<a href="#)"> <c:choose>
																	<c:when test="${list.libCode == 'LIB001' }">대구광역시립 중앙도서관</c:when>
																	<c:when test="${list.libCode == 'LIB002' }">대구광역시립 남부도서관</c:when>
																	<c:otherwise>대구광역시립 수성도서관</c:otherwise>
																</c:choose> <span id="availableButton_CAT000000498079_1"
																class="availableBtn enabled"> <c:choose>
																		<c:when test="${list.bookState == '0' }">대출가능</c:when>
																		<c:when test="${list.bookState == '1' }">대출중</c:when>
																		<c:when test="${list.bookState == '2' }">예약중 </c:when>
																		<c:when test="${list.bookState == '3' }">관외보관중 </c:when>
																		<c:when test="${list.bookState == '4' }">관외대출중 </c:when>
																		<c:when test="${list.bookState == '5' }">관외예약중 </c:when>
																		<c:when test="${list.bookState == '6' }">대출중(예약중) </c:when>
																		<c:when test="${list.bookState == '7' }">대출중(관외예약중) </c:when>
																		<c:when test="${list.bookState == '8' }">괸외대출중(관외예약중) </c:when>
																		<c:otherwise>
																		</c:otherwise>
																	</c:choose>
															</span></a>
														</p>
														<div id="holdingW_CAT000000498079_1" class="holdingW"
															style="display: none;"></div>
													</div>
												</dd>
											</dl>
										</li>
									</c:forEach>
								</ul>
								<div class="paging">
									<!-- 	<section id="pageList">  -->

									<!--  이전 페이지 생성 조건 -->


									<c:choose>
										<c:when test="${pageInfo.page <= 1 }">
											<a href="#"><img
												src="bdstyle/image/ko/solution/common/btn/prevPage.gif">
											</a>
										</c:when>
										<c:otherwise>
											<a href="bookSearchPro.bk?page=${pageInfo.page - 1 }"> <img
												src="bdstyle/image/ko/solution/common/btn/prevPage.gif"></a>&nbsp;
										</c:otherwise>
									</c:choose>
									<!-- 페이지 번호&이동 생성 조 -->
									<span> <c:forEach begin="${pageInfo.startPage }"
											end="${pageInfo.endPage }" var="a" step="1">
											<c:choose>
												<c:when test="${a == pageInfo.page }">
													<span> &nbsp;${a } </span>
												</c:when>
												<c:otherwise>
													<a
														href="bookSearchPro.bk?page=${a }&search=${param.search}&value=${param.value}">&nbsp;${a }</a>&nbsp;
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</span>
									<!--다음 페이지 생성 조건 -->
									<c:choose>
										<c:when test="${pageInfo.page >= pageInfo.maxPage }">&nbsp;</c:when>
										<c:otherwise>
											<a
												href="bookSearchPro.bk?page=${pageInfo.page + 1 }&search=${param.search}&value=${param.value}">
												<img src="bdstyle/image/ko/solution/common/btn/nextPage.gif">
											</a>
										</c:otherwise>
									</c:choose>
									<!-- 	</section>  -->

								</div>
							</fieldset>
						</form>
					</div>
					<!-- //resultInfo -->
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
			</script>
		</div>
		<ul class="resultList resultDetail">
		</ul>
	</div>
</div>

