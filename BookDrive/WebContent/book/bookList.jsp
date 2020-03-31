<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<li><a href="https://cham.kiu.ac.kr/search/tot">소장자료검색</a></li>
				<li><a href="https://cham.kiu.ac.kr/search/tot#">전체</a></li>
			</ul>
		</div>

		<!-- content -->
		<div id="divContent">
			<!-- 검색 연간물 제본정보에 추가비고란(홍대 ct_accession ser_remark:결호내용) -->
			<!-- 결호내용(홍대) -->

			<script type="text/javascript">
				//Message
				var imgPath = "/image/ko/";
				var noexistSearchDetailDataMsg = "상세정보가 없습니다.";
				var mediaMsg = "바로가기";
				var abstractMsg = "초록";
				var contentsMsg = "메세지가 없습니다";
				var commentariesMsg = "해제";
				var urlMsg = "URL";
				var vodMsg = "VOD";
				var aodMsg = "AOD";
				var originalMsg = "원문";
				var criticismMsg = "메세지가 없습니다";

				var numberMsg = "No.";
				var bookImageMsg = "책 표지";
				var shelfMsg = "소장처";
				var bookShelfMsg = "메세지가 없습니다";
				var callNoMsg = "청구기호";
				var shelfinfoMsg = "소장사항";
				var loaninfoMsg = "대출정보";
				var bookstateMsg = "도서상태";
				var reservationMsg = "예약";
				var remarkMsg = "비고";
				var returndateMsg = "반납예정일";
				var checkinMsg = "권호";
				var branchLoanMsg = "분관대출";
				var loanreqMsg = "야간대출";
				var checkBindingMsg = "권호·제본정보 보기";
				var latestdateMsg = "최근입수호";
				var missBookMsg = "서가에없는책신고";
				var missBookReportMsg = "신고";
				var nonBookMsg = "메세지가 없습니다";
				var deliveryMsg = "택배배달신청";
				var deliveryTitleMsg = "메세지가 없습니다";
				var printMsg = "인쇄";
				var smsMsg = "SMS발송";
				var selectMsg = "선택";

				var bookbindMsg = "제본정보";
				var detailholdinfoMsg = "권호정보";
				var noItemsMsg = "결과가 없습니다.";
				var subscriptionMsg = "구독";
				var checkinyearMsg = "연도별 권호정보";
				var yearhelpMsg = "연도를 선택하면 해당연도의 상세 권호정보를 보실 수 있습니다.";

				var checkinMsg = "권호";
				var pubdateMsg = "발행일";
				var indateMsg = "입수일";
				var articleMsg = "기사";
				var appendixMsg = "부록";
				var accessionnoMsg = "등록번호";
				var checkinnameMsg = "권호명";
				var densenoMsg = "밀집번호";
				var metsMsg = "매체보기";

				var isUseOpacSearch = "false";
				var serviceMsg = "서비스";
				var preserveMsg = "보존서고도서 신청";

				var MSG_ERM_PRODUCED_BY = "제공처";
				var MSG_ERM_COVERAGE_PERIOD = "원문제공기간";
				var MSG_ERM_RESCNOTE = "주기";

				var isSeriesReserve = "false";
				var CONST_CHECKINVIEW_TYPE = "radio";
				var isUseSerRemark = "false";
				var serRemarkMsg = "결호내용";

				var isDenseNoDisplay = "false";
				var isUseMypreserve = "false";
				var isUseSeriesDelivery = "false";

				var isUseLocCheck = "N";

				var infoServiceMsg = "서비스 이용안내";

				var dbNameMsg = "수록DB명";
				var offerPeriodMsg = "원문제공기간";
				var delayPeriodMsg = "원문지연기간";
				var mediaMsg = "바로가기";
				var msgCheckinButton = "권호정보";
				var msgBindingButton = "제본정보";
			</script>
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
									title="검색어를 입력하세요" placeholder="${ value }"></span> 
								 <input type="submit" value="검색" class="searchBtn">

							</div>
						</fieldset>
					</form>
				</div>
				<!-- //dataSearch -->

				<div class="searchWords">
					<dl class="searchKeyword">
						<dt>검색어</dt>
						<dd><c:set var="search" value=""/>
							<c:choose>
								<c:when test = "${param.search eq 'bookName' }">
									<c:set var="search" value="서명"/>
								</c:when>
								<c:when test = "${param.search eq 'bookWriter' }">
									<c:set var="search" value="저자"/>
								</c:when>
								<c:when test = "${param.search eq 'bookPub' }">
									<c:set var="search" value="출판사"/>
								</c:when>
								<c:when test = "${param.search eq 'ISBN' }">
									<c:set var="search" value="ISBN"/>
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
										<ul>
											<li class="myHistory"><a
												href="/search/service/searchHistory" title="검색 History"><img
													src="bdstyle/image/ko/solution/common/btn/searchBtn03.png"
													alt="검색 History"></a></li>
											<li class="myCart"><a href="/search/basket/list"
												title="바구니보기"><img
													src="bdstyle/image/ko/solution/common/btn/searchBtn04.png"
													alt="바구니보기"><span class="cnt" id="basket_count">1</span>
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

								<div class="searchBtns2">
									<span><input type="checkbox" name="allcheck"
										onclick="checkedAll(this, 'briefFrm', 'data')"
										class="checkbox" title="전체선택" id="selectAll"><label
										for="selectAll">전체선택</label></span> <a
										href="javascript:addBasketSelect();"><img
										src="bdstyle/image/ko/local/bookimages/basket.jpg"
										alt="바구니 담기">바구니 담기</a>
								</div>

								<div class="sortOptions">

									<form id="searchOption" name="searchOption" action="#"
										method="get">
										<fieldset>
											<legend>Search Option</legend>

											<input type="hidden" name="msc" value="10000"> <select
												id="oi" name="oi" class="searchOpt2 selectBox" title="항목선택"
												style="display: none;">
												<option value="">항목선택</option>
												<option value="DISP01">서명</option>
												<option value="DISP02">저자</option>
												<option value="DISP03">출판사</option>
												<option value="DISP04">청구기호</option>
												<option value="DISP06">출판년</option>
											</select> <select id="os" name="os" class="searchOpt2 selectBox"
												title="정렬" style="display: none;">
												<option value="">정렬</option>
												<option value="ASC">오름차순</option>
												<option value="DESC">내림차순</option>
											</select> <select id="cpp" name="cpp" class="searchOpt2 selectBox"
												title="쪽당 출력 건수" style="display: none;"><option
													value="5">5</option>
												<option value="10" selected="selected">10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="50">50</option>
												<option value="100">100</option></select> <a href="#"
												class="selectBox searchOpt2 selectBox-dropdown"
												title="쪽당 출력 건수" tabindex="NaN"
												style="width: 48px; display: inline-block;"><span
												class="selectBox-label" style="width: 25px;">10</span><span
												class="selectBox-arrow"></span></a> <input type="submit"
												value="조회" class="btnType6">
										</fieldset>
									</form>
								</div>
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
												<dt class="title">선택</dt>
												<dd class="dataCheck">
													<input type="checkbox" name="data" value="CAT000000498079"
														title="선택">
												</dd>
												<dt class="title">No</dt>
												<dd class="num">${status.index + ((pageInfo.page-1)*5) + 1 }.</dd>
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
										<a href="#"><img src="bdstyle/image/ko/solution/common/btn/prevPage.gif"> </a></c:when>
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
												<span> &nbsp;${a }  </span>
												</c:when>
												<c:otherwise>
													<a href="bookSearchPro.bk?page=${a }&search=${param.search}&value=${param.value}">&nbsp;${a }</a>&nbsp;
												</c:otherwise>
											</c:choose>
										</c:forEach> </span>
										<!--다음 페이지 생성 조건 --> 
										<c:choose>
											<c:when test="${pageInfo.page >= pageInfo.maxPage }">&nbsp;</c:when>
											<c:otherwise>
												<a href="bookSearchPro.bk?page=${pageInfo.page + 1 }&search=${param.search}&value=${param.value}">
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
				var IS_USE_OPAC_SEARCH = "false";
				var MSG_ERROR_NOT_FIND = "";
				var MSG_VALID_LOGIN = "로그인 후 이용하세요.";
				var MSG_VALID_SEND_SMS = "발송하시겠습니까?";
				var MSG_VALID_COMPLETE_SMS = "발송되었습니다.";
				var SITE_CODE = "A0000001";
				var TITLE_BASKET = "바구니";
				var INFO_BASKET_INPUT = "바구니에 담았습니다.";
				var INFO_BASKET_DEL = "바구니에서 삭제 하였습니다.";
				var WARN_CHECK_FIELDS = "항목을 체크하세요."; //항목을 체크하세요.

				var SELECT_HOLDING_MODULE = '';
				var USE_SERVICE = true;

				var saveBtn = "저장";
				var mailBtn = "메일";
				var printBtn = "인쇄";
				var previewBtn = "미리보기";
				var endNoteBtn = "EndNote";
				var refWorksBtn = "RefWorks";
				var selectBtn = "선택";
				var imagePath = "/image/ko/";
				$.global = {
					resizeMove : null,
					resizeDraw : null,
					resizeSelectBox : null
				};

				var facetYearList = [ '2015', '2009', '2010', '2017', '2005',
						'2006', '2014', '2003', '2004', '2007', '2008', '2013',
						'2018', '2019' ];

				var facetYearCount = {};
				facetYearCount['2015'] = 5;
				facetYearCount['2009'] = 3;
				facetYearCount['2010'] = 3;
				facetYearCount['2017'] = 3;
				facetYearCount['2005'] = 2;
				facetYearCount['2006'] = 2;
				facetYearCount['2014'] = 2;
				facetYearCount['2003'] = 1;
				facetYearCount['2004'] = 1;
				facetYearCount['2007'] = 1;
				facetYearCount['2008'] = 1;
				facetYearCount['2013'] = 1;
				facetYearCount['2018'] = 1;
				facetYearCount['2019'] = 1;
			</script>
			<script type="text/javascript">
				var isUseMypreserveInfo = "false";
				var isUseDeliveryInfo = "true";
				var isUseLoanreqInfo = "false";
				var isUseMissrepoInfo = "false";
				var isUseCampusLoanInfo = "false";
				var isUsePosPrintInfo = "true";
				var isSendSmsInfo = "false";
				var isUseBookShelf = "false";
			</script>
		</div>

		<ul class="resultList resultDetail">

		</ul>
	</div>
</div>

