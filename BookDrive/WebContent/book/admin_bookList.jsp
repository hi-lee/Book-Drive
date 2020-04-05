<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp" />
<link rel="stylesheet" href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/standard/admin_info.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/search/cat/brief.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/search/cat/basket.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/search/cat/jquery-ui.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/search/cat/Nwagon.css">
<link rel="stylesheet" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<link rel="stylesheet"
	href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css"
	media="screen and (max-width:767px)">

<script type="text/javascript" src="bdstyle/admin/js/common/ajaxCommon.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/search/brief.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/search/media.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/search/mylibrary.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/jquery.bgiframe.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.mThumbnailScroller.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery-ui.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Nwagon.js"></script>
<!-- Toastr관련 -->
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ui/toastr/toastr.min.css">

<div id="divContentsW">
	<div id="divContents">
		<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js"
			charset="utf-8"></script>
		<h2 id="divTitle">도서검색</h2>
		<div id="divLocation">
			<ul>
				<li><a href="Adminmain.logC" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a>도서관리</a></li>
				<li><a href="javascript:void();">도서 목록</a></li>
			</ul>
		</div>
		<!-- content -->
		<div id="divContent">
			<div class="searchBrief">
				<!-- 시작 -->
				<!-- git테스트 -->
				<div class="dataSearch">
					<form name="refineSearch" method="get" action="BookList.bookA">
						<fieldset>
							<select id="si1" name="libcode" title="도서관 선택" class="searchOpt1 selectBox" style="display: none;">
								<option value="">전체</option>
								<c:forEach var="liblist" items="${libNameList}">
									<option id="${liblist.code}" value="${liblist.code}" <c:if test="${param.libcode eq liblist.code}">selected</c:if>>${liblist.name}</option>
								</c:forEach>
							</select>
							<select id="si2" name="search" title="도서 분류 선택" class="searchOpt1 selectBox" style="display: none;">
								<option value="bookName">도서명</option>
								<option value="bookWriter">저자</option>
								<option value="ISBN">ISBN</option>
							</select>
							<select id="si3" name="bookstate" title="도서 상태 선택" class="searchOpt1 selectBox" style="display: none;">
								<option value="">전체</option>
								<c:forEach var="bookstatelist" items="${bookStateList}" varStatus="status">
									<option id="${status.index+1}" value="${status.index+1}" ${(param.bookstate eq status.index+1)?'selected':''}>${bookstatelist}</option>
								</c:forEach>
							</select>
							<div class="searchW">
								<span class="keyword">
									<input type="text" id="keyword" name="keyword" value="${param.keyword}" placeholder="책이름을 입력하세요." title="검색어를 입력하세요">
								</span>
								<input type="submit" value="검색" class="searchBtn">
							</div>
						</fieldset>
					</form>
				</div>
				<!-- //dataSearch -->
				<div class="briefContent">
					<div id="result" class="result">
						<div class="resultHeader">
							<div class="resultInfo">
								<div class="listInfo1">
									<div class="breifPaging">
										<span class="current">${pageinfo.nowPage * 10 - 9}</span> - ${pageinfo.nowPage * 10} <a
											href="BookList.bookA?page=${pageinfo.nowPage+1}&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}"
											class="lastPage" title="다음페이지로"><img
											src="bdstyle/admin/image/ko/solution/common/btn/breifLast.png"
											alt="다음페이지로"></a>
									</div>
								</div>
							</div>
							<div class="briefHeader sort"></div>
						<!-- //listInfo -->
						</div>
						<!-- 책 리스트 출력 부분 -->
						<c:if test="${empty bookList}">
							<h2 style="text-align:center;">검색결과가 없습니다.</h2>
						</c:if>
						<c:set var="replace_hilight" value="<span class=hilight>${param.keyword}</span>"/>
						<c:forEach var="booklist" items="${bookList}" varStatus="status">
							<!-- 카테고리 변경부분 -->
							<c:choose>
								<c:when test="${booklist.bookCategory eq '000'}"><c:set var="category" value="총류"/></c:when>
								<c:when test="${booklist.bookCategory eq '100'}"><c:set var="category" value="철학"/></c:when>
								<c:when test="${booklist.bookCategory eq '200'}"><c:set var="category" value="종교"/></c:when>
								<c:when test="${booklist.bookCategory eq '300'}"><c:set var="category" value="사회과학"/></c:when>
								<c:when test="${booklist.bookCategory eq '400'}"><c:set var="category" value="자연과학"/></c:when>
								<c:when test="${booklist.bookCategory eq '500'}"><c:set var="category" value="기술과학"/></c:when>
								<c:when test="${booklist.bookCategory eq '600'}"><c:set var="category" value="예술"/></c:when>
								<c:when test="${booklist.bookCategory eq '700'}"><c:set var="category" value="언어"/></c:when>
								<c:when test="${booklist.bookCategory eq '800'}"><c:set var="category" value="문학"/></c:when>
								<c:otherwise><c:set var="category" value="역사"/></c:otherwise>
							</c:choose>
							<c:set var="bookname" value="${booklist.bookName}"/>
							<c:set var="bookwriter" value="${booklist.bookWriter}"/>
							<c:set var="isbn" value="${booklist.ISBN}"/>
							<c:choose>
								<c:when test="${param.search eq 'bookName'}">
									<c:set var="bookname" value="${fn:replace(booklist.bookName, param.keyword, replace_hilight)}"/>
								</c:when>
								<c:when test="${param.search eq 'bookWriter'}">
									<c:set var="bookwriter" value="${fn:replace(booklist.bookNum, param.keyword, replace_hilight)}"/>
								</c:when>
								<c:when test="${param.search eq 'ISBN'}">
									<c:set var="isbn" value="${fn:replace(booklist.ISBN, param.keyword, replace_hilight)}"/>
								</c:when>
							</c:choose>
							<fmt:parseNumber value="${booklist.rowNum}" var="rownum"/>
							<ul class="resultList resultDetail">
								<li id="${booklist.bookNum}" class="items">
									<dl>
										<dt class="title">No</dt>
										<dd class="num">${booklist.bookNum}</dd>
										<dt class="title">표지이미지</dt>
										<dd class="book">
											<a href="javascript:callBookInfo(${booklist.bookNum});">
												<img src="${booklist.bookImage}" width="75" height="103" alt="표지이미지" onerror="this.src='bdstyle/admin/image/ko/solution/common/ico/type_m_M.png'">
											</a>
											<p class="selectBtn">
												<a id="basket_CAT000000162393" href="javascript:toggleBasket('CAT000000162393');" class="cartBtn " title="바구니 담기">바구니 담기</a>
											</p>
										</dd>
										<dt class="title">도서명</dt>
										<dd class="title">
											<!-- <a href="javascript:callBookInfo(${booklist.bookNum});">${bookname}</a>  -->
											${bookname}
											<button type="button" title="상세보기" onclick="callBookInfo(${booklist.bookNum});">상세보기</button>
											<!-- <a href="BookInfo.bookA?booknum=${booklist.bookNum}">${bookname}</a> -->
										</dd>
										<dt class="title">저자</dt>
										<dd class="info${booklist.bookNum}">저자 : ${bookwriter}</dd>
										<dt class="title">출판사</dt>
										<dd class="info${booklist.bookNum}">출판사 : ${booklist.bookPub}</dd>
										<dt class="title">출판년도</dt>
										<dd class="info${booklist.bookNum}">출판년도 : ${booklist.bookPubDate}</dd>
										<dt class="title">도서권수</dt>
										<dd class="info${booklist.bookNum}">도서권수 : ${booklist.bookQty}권</dd>
										<!-- 책의 상세정보 확인 -->
										<dd class="preview">
											<div id="prevDetail_${booklist.bookNum}" class="detailInfo hidden">
											<ul>
												<li>
													<dl>
														<dt>도서번호</dt>
														<dd>${booklist.bookNum}</dd>
													</dl>
												</li>
												<li>
													<dl>
														<dt>서명/저자사항</dt>
														<dd>${bookname} / 지은이:${bookwriter}</dd>
													</dl>
												</li>
												<li>
													<dl>
														<dt>출판사/출판년도</dt>
														<dd>${booklist.bookPub} / 지은이:${booklist.bookPubDate}</dd>
													</dl>
												</li>
												<li>
													<dl>
														<dt>ISBN</dt>
														<dd>${isbn}</dd>
													</dl>
												</li>
												<li>
													<dl>
														<dt>카테고리</dt>
														<dd>${booklist.bookCategory}(<c:out value="${category}"/>)</dd>
													</dl>
												</li>
											</ul>
										</div>
										</dd>
										<dt class="title">소장처 정보</dt>
										<dd class="holdingInfo">
											<div class="holding">
												<p class="location">${booklist.libName}
													<span class="availableBtn enabled">
														<c:choose>
															<c:when test="${booklist.bookState eq '0'}">
																<a href="BookModifyState.bookA?libCode=${param.libcode}&bookState=${param.bookstate}&keyword=${param.keyword}&updatestate=3&num=${booklist.bookNum}">대출가능</a>
															</c:when>
															<c:when test="${booklist.bookState eq '1'}">
																대출중
															</c:when>
															<c:when test="${booklist.bookState eq '2'}">
																예약중
															</c:when>
															<c:when test="${booklist.bookState eq '3'}">
																<a href="BookModifyState.bookA?libCode=${param.libcode}&bookState=${param.bookstate}&keyword=${param.keyword}&updatestate=0&num=${booklist.bookNum}">관외보유</a>
															</c:when>
															<c:when test="${booklist.bookState eq '4'}">
																관외대출
															</c:when>
															<c:when test="${booklist.bookState eq '5'}">
																관외예약
															</c:when>
															<c:when test="${booklist.bookState eq '6'}">
																대출중(예약중)
															</c:when>
															<c:when test="${booklist.bookState eq '7'}">
																대출중(관외예약중)
															</c:when>
															<c:otherwise>
																관외대출중(관외예약중)
															</c:otherwise>
														</c:choose>
													</span>
												</p>
												<!-- 대출,예약 가능 여부 확인 -->
												<div id="holdingW_${booklist.bookNum}" class="holdingW hidden">
													<div class="listTable">
														<table class="mobileTable default" summary="" border="1" cellspacing="0">
															<thead>
																<tr>
																	<th scope="row" class="num footable-first-column">대여번호</th>
																	<th scope="row">소장처</th>
																	<th scope="row" data-hide="phone">대여자이름(아이디)</th>
																	<th scope="row" data-hide="phone">대여종류</th>
																	<th scope="row" data-hide="phone">대출일자</th>
																	<th scope="row" data-hide="phone">대출만료일자</th>
																	<th scope="row" data-hide="phone">예약일자</th>
																	<th scope="row" data-hide="phone">예약만료일자</th>
																	<th scope="row" data-hide="phone" class="footable-last-column">반납일자</th>
																</tr>
															</thead>
															<tbody>
																
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</dd>
									</dl>
								</li>
							</ul>
						</c:forEach> <!-- 책 리스트 출력 반복 끝 -->
						<div class="paging">
							<div class="paging">
								<c:choose>
									<c:when test="${pageinfo.nowPage <= 1}">
										
									</c:when>
									<c:otherwise>
										<a href="BookList.bookA?page=1&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}" class="page">
											<img src="bdstyle/admin/image/ko/solution/common/btn/firstPage.gif">
										</a>
										<a href="BookList.bookA?page=${pageinfo.nowPage-1}&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}" class="page">
											<img src="bdstyle/admin/image/ko/solution/common/btn/prevPage.gif">
										</a>
									</c:otherwise>
								</c:choose>
								<span>
									<c:forEach begin="${pageinfo.startPage}" end="${pageinfo.endPage}" step="1" var="a">
										<c:choose>
											<c:when test="${a eq pageinfo.nowPage}">
												<span>${a}</span>
											</c:when>
											<c:otherwise>
												<a href="BookList.bookA?page=${a}&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}">${a}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</span>
								<c:choose>
									<c:when test="${pageinfo.nowPage >= pageinfo.maxPage}">
									
									</c:when>
									<c:otherwise>
										<a href="BookList.bookA?page=${pageinfo.nowPage+1}&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}" class="page">
											<img src="bdstyle/admin/image/ko/solution/common/btn/nextPage.gif">
										</a>
										<a href="BookList.bookA?page=${pageinfo.maxPage}&libcode=${param.libcode}&keyword=${param.keyword}&search=${param.search}&bookstate=${param.bookstate}" class="page">
											<img src="bdstyle/admin/image/ko/solution/common/btn/lastPage.gif">
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<!-- //resultInfo -->
				</div>
			</div>
		</div>
	</div>
</div>
<!-- DIV 레이어 팝업(BookInfo) -->
<div class="js_layer layer hidden" style="display: none;">
	<div id="divContentsW">
		<div id="divContents layer">
			<div class="member_table_view">
				<table>
					<colgroup>
						<col style="width:120px;">
						<col style="width:;">
						<col style="width:120px;">
						<col style="width:;">
					</colgroup>
					<tr>
						<th>책이름</th>
						<td id="bookname" colspan="3"></td>
					</tr>
					<tr>
						<th>저자</th>
						<td id="bookwriter"></td>
						<th>출판사</th>
						<td id="bookpub"></td>
					</tr>
					<tr>
						<th>소장권수</th>
						<td id="bookqty"></td>
						<th>출판년도</th>
						<td id="bookpubdate"></td>
					</tr>
					<tr>
						<th>ISBN</th>
						<td id="bookisbn"></td>
						<th>등록일</th>
						<td id="bookregdate"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- 레이어 팝업 영역 밖 mask -->
<div id="black_mask"></div>
<script type="text/javascript">
//책이름 클릭 상세페이지 호출
function callBookInfo(bookNum) {
	var $holding = $('#holdingW_'+bookNum);
	var $detail = $('#prevDetail_'+bookNum);
	var $info = $('.info'+bookNum);
	if ($info.is(':visible') == false) { //close
		$info.show();
		$holding.addClass('hidden');
		$detail.addClass('hidden');
		$('.resultList .holding .availableBtn').removeClass('on');
		return false;
	} else { //open
		if( typeof bookNum == 'undefined' || bookNum == '' || !bookNum ) {
			console.log('책번호가 없습니다');
			return false;
		}

		$.ajax({
			url: "BookInfo.bookA",
			type: "POST",
			dataType: "json",
			data: {"booknum":bookNum},
			success:
				function(data) {
					console.log(data[0]);
					$holding.removeClass('hidden');
					$detail.removeClass('hidden');
					$info.hide();
					$('.resultList .holding .availableBtn').addClass('on');
					var gumoon = "";
					$('#holdingW_'+bookNum+' .listTable .mobileTable tbody').empty();
					if( data == "" || data == null || data == undefined || ( data != null && typeof data == "object" && !Object.keys(data).length ) ){
						gumoon += "<tr><td colspan=9>대여내역이 없습니다.</td></tr>";
					} else {
						$.each(data, function (key, value) {
							
							if (value.memName == undefined) { value.memName = '탈퇴자'; }
							if (value.memId == undefined) { value.memId = '';}
							if (value.renFlag == 'rev') { value.renFlag = '예약' }
							else if (value.renFlag == 'brw') { value.renFlag = '대출' }
							else if (value.renFlag == 'outrev') { value.renFlag = '관외예약' }
							else if (value.renFlag == 'outbrw') { value.renFlag = '관외대출' }
							gumoon += "<tr id=" + value.renIdvNum + ">";
							gumoon += "<td id=IdvNum>" + value.renIdvNum + "</td>";
							gumoon += "<td id=lib>" + value.libName + "</td>";
							gumoon += "<td id=name>" + value.memName + "(" + value.memId + ")" + "</td>";
							gumoon += "<td id=flag>" + value.renFlag + "</td>";
							gumoon += "<td id=Brw>" + value.renBrwDate + "</td>";
							gumoon += "<td id=BrwInv>" + value.renBrwInvDate + "</td>";
							gumoon += "<td id=Rev>" + value.renRevDate + "</td>";
							gumoon += "<td id=RevInv>" + value.renRevInvDate + "</td>";
							gumoon += "<td id=Return>" + value.renReturnDate + "</td>";
							gumoon += "</tr></br>";
						});
					}
					$('#holdingW_'+bookNum+' .listTable .mobileTable tbody').append(gumoon);
			},
			error:
				function(request, status, error) {
				console.log(error);
				alert('통신실패');
			}
		});
	}
	
} //책 상세페이지 함수 끝
var winWidth = window.innerWidth || document.documentElement.clientWidth;
$(document).ready(function() {
	//search selectBox 셀렉트
	if ('${param.search}' == 'bookName') $('#si2 option:eq(0)').prop('selected', true);
	if ('${param.search}' == 'bookWriter') $('#si2 option:eq(1)').prop('selected', true);
	if ('${param.search}' == 'ISBN') $('#si2 option:eq(2)').prop('selected', true);
	//화면 밖 영역 클릭시 팝업 없애기
	$('#black_mask').click(function() {
		$(this).hide();
		$('.js_layer').addClass('hidden');
	}) //팝업 없애기 끝
	
	$('.mobileTable').footable({
		breakpoints: {
			phone: 1024,
			tablet: 1024
		}
	});
	
	$(window).resize(function(){
        winWidth = window.innerWidth || document.documentElement.clientWidth;
        if(winWidth < 768){
                  $('.typeList ul').hide();
                  $('.typeSelect a img').eq(1).attr({'src':'/image/ko/solution/common/ico/calTypeOpen.png'},{'alt':'닫기'});
                  $('.typeSelect a').toggle(function(){
                             $(this).parent().next().children().show();
                             $(this).children().eq(1).attr({'src':'/image/ko/solution/common/ico/calTypeClose.png'},{'alt':'닫기'});
                  },function(){
                             $(this).parent().next().children().hide();
                             $(this).children().eq(1).attr({'src':'/image/ko/solution/common/ico/calTypeOpen.png'},{'alt':'열기'});
                  });
        }else{
                  $('.typeList ul').show();
        }
});

	// 모바일 좌측메뉴 이벤트

	if($(window).width() > 767){
	
		$(".facet .listOpenBtn").unbind(".listOpen");
		$(".facet .listOpenBtn").bind("click.listOpen", function() {
			
			if($(".facet .listOpenBtn").hasClass("close"))
			{
				$(".facetList dd").show();
				$(".facet dt a").removeClass();
				$(".facet dt a").attr("class","open");
				$(".facet .listOpenBtn").toggleClass("close");
			}
			else
			{
				$(".facetList dd").hide();
				$(".facet dt a").removeClass();
				$(".facet .listOpenBtn").toggleClass("close");
			}
		});
		
		$(".facetList > ul").show();
		$(".facetList dd").show();
		$(".facet dt a").removeClass();
		$(".facet dt a").attr("class","open");
	}else{
		$(".facet .listOpenBtn").unbind(".listOpen");
		$(".facet .listOpenBtn").bind("click.listOpen", function() {
			$(".facetList > ul").toggle();
		});
		$(".facetList > ul").hide();
	}
	$(window).resize(function(){
        if($(window).width() > 767){
        	$(".facet .listOpenBtn").unbind(".listOpen");
        	$(".facet .listOpenBtn").bind("click.listOpen", function() {
        		if($(".facet .listOpenBtn").hasClass("close"))
    			{
    				$(".facet .listOpenBtn").toggleClass("close");
    				$(".facetList dd").hide();
    				$(".facet dt a").removeClass();
    				$(".facet dt a").attr("class","open");
    			}
    			else
    			{
    				$(".facet .listOpenBtn").toggleClass("close");
    				$(".facetList dd").show();
    				$(".facet dt a").removeClass();
    			}
       		});
            $(".facetList > ul").show();
        }else{
        	$(".facet .listOpenBtn").unbind(".listOpen");
        	$(".facet .listOpenBtn").bind("click.listOpen", function() {
        		$(".facetList > ul").toggle();
        	});
        }
	});
	
	$(".facet dt a").bind("click", function() {
	        $(this).parent().next().toggle();
	        $(this).toggleClass("open");
	        return false;
	});


    
    $( "#exportForm" ).submit(function( event ) {
    	
    	$('#exportForm > input[name=data]').remove();
    	
    	$("input:checkbox[name=data]:checked").each(function() {
    		$('<input>').attr({
    			type: 'hidden',
    			name: 'data',
    			value: this.value
    		}).appendTo("#exportForm");
    	});
    	
    	if ($( 'input:checked[type="radio"]' ).val()  == "html") {
    		$( 'input[type="submit"]' ).val(saveBtn);
    	} else if($( 'input:checked[type="radio"]' ).val() == "email") {
    		$( 'input[type="submit"]' ).val(mailBtn);
    	} else if($( 'input:checked[type="radio"]' ).val() == "print") {
    		$( 'input[type="submit"]' ).val(printBtn);
    	} else if($( 'input:checked[type="radio"]' ).val() == "preview") {
    		$( 'input[type="submit"]' ).val(previewBtn);
    	} else if($( 'input:checked[type="radio"]' ).val() == "endnote") {
    		$( 'input[type="submit"]' ).val(endNoteBtn);
    		$( "#exportForm" ).attr("action","/search/endnote/export");        
        } else if($( 'input:checked[type="radio"]' ).val() == "refworks") {
        	$( 'input[type="submit"]' ).val(refWorksBtn);
        	$( "#exportForm" ).attr("action","/search/refworks/export");
        }
    	
    	$("#export").toggle();
    });
    
    $(".export input[type='reset']").click(function() {
        $("#export, .export .mail, .export .notice").hide();
        $(".export .range").show();
        $(".searchLink .mail").removeClass("on");
    });
    
	// 서비스 이용안내 클릭 이벤트
	$("div.service dt").click(function() {
		$(this).next().toggle();
	});
	
	//도서관 선택 select 크기
	$("input:radio[name='st']").change(function(){
		idx=$(this).parent().index();
		$('.dataSearch .searchOpt1').hide().addClass('hide');
		$('.dataSearch .searchOpt1').eq(idx).removeClass('hide');
		$('.dataSearch a.searchOpt1').hide();
		$('.dataSearch .searchOpt1').selectBox('disable');

		if ($("input:radio[name='st']:checked").val() == 'EXCT') {
            $('.dataSearch .searchOpt1').hide().addClass('hide');
            $('.dataSearch .searchOpt1').eq(1).removeClass('hide');
            $('.dataSearch a.searchOpt1').hide();
            $('.dataSearch .searchOpt1').selectBox('disable');
 		}
		
		if ($("input:radio[name='st']:checked").val() == 'KWRD') {
			$('.dataSearch a.searchOpt1').eq(0).show();
			$('.dataSearch select.searchOpt1').eq(0).selectBox('enable');
	    } else {
			$('.dataSearch a.searchOpt1').eq(1).show();
			$('.dataSearch select.searchOpt1').eq(1).selectBox('enable');
	    }
		
		var ua = window.navigator.userAgent.toLowerCase();
		if ( /iphone/.test(ua) || /android/.test(ua) || /opera/.test(ua) || /bada/.test(ua) || /ipad/.test(ua) || /ipod/.test(ua) ) {		    
			if ($("input:radio[name='st']:checked").val() == 'KWRD') {
				$('.dataSearch .searchOpt1').eq(0).show();
				$('.dataSearch .searchOpt1').eq(1).hide();
		    } else {
				$('.dataSearch .searchOpt1').eq(1).show();
				$('.dataSearch .searchOpt1').eq(0).hide();
		    }
		}
	});
});


$(window).resize(function() {
	
	var stval = $("input:radio[name='st']:checked").val();
	
	if (stval == 'KWRD') {
		$('.searchSelect span').first().children().eq(0).attr('checked','checked');
		$('.dataSearch select.searchOpt1').eq(0).selectBox('enable');
	}
	else {
		$('.searchSelect span').first().children().eq(1).attr('checked','checked');
		$('.dataSearch select.searchOpt1').eq(1).selectBox('enable');
	}
	$(".searchOpt1").selectBox('destroy').selectBox();
	$('.dataSearch a.hide').hide();
	$("#stKWRD").change();
});

var isUseDdsLink = false;
var isUseHopeBookLink = false;
</script>

<script>
function addLinkPop (query) {
	document.frmDigicolPopup.action="/digicol/pop/writeitem";
	document.frmDigicolPopup.data.value=query;
	var options = "width=600,height=550,resizable=no,top=100,left=200,scrollbars=yes";
	window.open('',"digicolSave",options);
	
	document.frmDigicolPopup.target="digicolSave";
	document.frmDigicolPopup.submit();
}
</script>
