<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp" />
<link rel="stylesheet" href="bdstyle/admin/style/ko/standard/admin_list.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/svp/bbs/admin_list.css">
<link rel="stylesheet" href="bdstyle/admin/style/ui/toastr/toastr.min.css">
<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_mobiletable.css" media="screen and (max-width:767px)">

<script type="text/javascript" src="bdstyle/admin/js/common/ajaxCommon.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/Checkbox.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/jquery.bgiframe.js"></script>
<script type="text/javascript" src="bdstyle/admin/js/common/toastr.min.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.mThumbnailScroller.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/jquery-ui.js"></script>
<script type="text/javascript" src="bdstyle/admin/script/admin_list.js"></script>
<div id="divContentsW">
	<div id="divContents">
		<script type="text/javascript"
			src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
		<h2 id="divTitle">자유게시판관리</h2>
		<div id="divLocation">
			<ul>
				<li><a href="https://cham.kiu.ac.kr/" title="HOME"><img
						src="bdstyle/admin/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/list/2">게시판관리</a></li>
				<li><a href="https://cham.kiu.ac.kr/bbs/list/2?pn=1#">자유게시판관리</a></li>
			</ul>
		</div>
		<!-- sns share기능 사용여부 -->

		<div id="divContent">
			<!-- Content Header -->
			<h3 class="skip">게시판 안내</h3>
			<!-- Content Search -->
			<div class="searchArea">
				<p class="limitSearchBtn">
					<a href="https://cham.kiu.ac.kr/bbs/list/2?pn=1#">선택조건으로 조회</a>
				</p>
				<form name="searchFrm" id="searchFrm" method="get" action="BoardList1.boardA">
					<fieldset>
						<legend>검색</legend>
						<span class="bunch">
							<label for="searchKind" class="skip">검색어</label>
							<select id="kind" name="kind" class="selectBox1">
								<option value="title" <c:if test="${param.kind eq 'title'}"> selected</c:if>>제목</option>
								<option value="writer" <c:if test="${param.kind eq 'writer'}"> selected</c:if>>작성자</option>
								<option value="content" <c:if test="${param.kind eq 'content'}"> selected</c:if>>내용</option>
							</select>
							<input type="text" name="keyword" class="inputTextType3 sw" maxlength="100" title="검색어" placeholder="검색어를 입력하세요" value="${param.keyword}">
						</span>
						<span class="bunch">
							<label for="os" class="skip">정렬</label>
							<select id="os" name="os" class="selectBox1" title="정렬">
								<option value="asc" <c:if test="${param.os eq 'asc'}"> selected</c:if>>오름차순</option>
								<option value="desc" <c:if test="${param.os eq 'desc'}"> selected</c:if>>내림차순</option>
							</select>
						</span>
						<span class="bunch">
						<label for="countPerPage" class="skip">쪽당</label>
						<select id="countPerPage" name="countPerPage" class="selectBox1">
							<option value="10" <c:if test="${param.countPerPage eq '10'}"> selected</c:if>>10건</option>
							<option value="15" <c:if test="${param.countPerPage eq '15'}"> selected</c:if>>15건</option>
							<option value="20" <c:if test="${param.countPerPage eq '20'}"> selected</c:if>>20건</option>
							<option value="30" <c:if test="${param.countPerPage eq '30'}"> selected</c:if>>30건</option>
						</select>
						</span>
						<input type="submit" class="btnType5" value="검색">
					</fieldset>
				</form>
			</div>
			<!-- Content Num Of Search Result -->
			<div class="listInfo">
				<div class="listInfo1">
					<p class="totalCnt">
						총 <span class="totalcount">42</span> 건
					</p>
					<p class="pageNum">
						,<span>1</span>/3페이지
					</p>
				</div>
				<div class="listInfo2">
					<div class="fileSend">
						<ul>
							<li>
							 	<a class="btnType1" href="BoardWriteForm.boardA?flag=1" title="공지사항 작성">
							 		글작성
							 	</a>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<form name="boardList" method="post"
				action="https://cham.kiu.ac.kr/bbs/boardMove/2"
				onsubmit="return checked(this);">
				<!-- List -->
				<div class="listTable">
					<table class="mobileTable default">
						<caption>게시판 목록</caption>
						<thead>
							<tr>
								<th scope="row" class="footable-first-column">No.</th>
								<th scope="row" data-class="expand">제목</th>
								<th scope="row" data-hide="phone">작성자</th>
								<th scope="row" data-hide="phone">작성일</th>
								<th scope="row" data-hide="phone">조회수</th>
								<th scope="row" data-hide="phone" class="footable-last-column">첨부파일</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty boardList}">
									<c:forEach var="list" items="${boardList}">
										<tr>
											<td class="num footable-first-column">${list.boardNum}</td>
											<td class="title expand">
											<a href="BoardModifyForm.boardA?boardnum=${list.boardNum}&page=${pageinfo.nowPage}">
												${list.boardSubject}
											</a>
											</td>
											<td class="writer">${list.boardWriter}</td>
											<td class="reportDate">${list.boardDate}</td>
											<td class="view_cnt">${list.boardRCount}</td>
											<td class="footable-last-column">
												<c:choose>
													<c:when test="${list.boardFile eq ''}">
														없음
													</c:when>
													<c:otherwise>
														<a href="fileDown.boardA?downFile=${list.boardFile}">
															<img class="addedFile" src="bdstyle/admin/image/ko/solution/common/ico/clip.png" title="${list.boardFile}" alt="${list.boardFile}">
														</a>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="8" style="text-align: center" class="footable-last-column footable-first-column">결과가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<!-- 특정 게시판의 게시물 이동 -->
				<input type="submit" name="submit" value="submit"
					style="display: none;">
			</form>
			<!-- Paging -->
			<div class="paging">
				<div class="paging">
					<c:choose>
						<c:when test="${pageinfo.nowPage <= 1}">
							
						</c:when>
						<c:otherwise>
							<a href="BoardList1.boardA?page=1&kind=${param.kind}&keyword=${param.keyword}&os=${param.os}&countPerPage=${param.countPerPage}" class="page">
								<img src="bdstyle/admin/image/ko/solution/common/btn/firstPage.gif">
							</a>
							<a href="BoardList1.boardA?page=${pageinfo.nowPage-1}&kind=${param.kind}&keyword=${param.keyword}&os=${param.os}&countPerPage=${param.countPerPage}" class="page">
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
									<a href="BoardList1.boardA?page=${a}&kind=${param.kind}&keyword=${param.keyword}&os=${param.os}&countPerPage=${param.countPerPage}">${a}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</span>
					<c:choose>
						<c:when test="${pageinfo.nowPage >= pageinfo.maxPage}">
						
						</c:when>
						<c:otherwise>
							<a href="BoardList1.boardA?page=${pageinfo.nowPage+1}&kind=${param.kind}&keyword=${param.keyword}&os=${param.os}&countPerPage=${param.countPerPage}" class="page">
								<img src="bdstyle/admin/image/ko/solution/common/btn/nextPage.gif">
							</a>
							<a href="BoardList1.boardA?page=${pageinfo.maxPage}&kind=${param.kind}&keyword=${param.keyword}&os=${param.os}&countPerPage=${param.countPerPage}" class="page">
								<img src="bdstyle/admin/image/ko/solution/common/btn/lastPage.gif">
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>