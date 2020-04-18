<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/detail.css">
<c:choose>
	<c:when test="${userID eq null}">
		<script>
			alert('로그인이 필요한 페이지입니다.');
			location.href = "loginForm.log";
		</script>
	</c:when>
	<c:otherwise>

		<style>
.empty {
	text-align: center;
}
</style>
		<div id="divContentsW">
			<div id="divContents">
				<div class="searchInfo">
					<div class="searchHeader" style="margin-top: 30px;">
						<h3 class="open">대출중인 내역</h3>
					</div>
					<!--  if(renFlag== 'brw' -> 'isBrw'='true', if(isBrw=='true')-> select: renFlag:'brw' -->
					<div class="searchContents">
						<div class="listTable">
							<table class="searchTable default">
								<c:set var="isBrw" value="false" />
								<c:forEach items="${rentalList }" var="list" varStatus="status">
									<c:if
										test="${(list.renFlag eq 'brw' or list.renFlag eq 'outbrw') and list.renIdvDelFlag eq '0'}">
										<c:set var="isBrw" value="true" />
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${isBrw eq 'true' }">
										<c:set var="Num" value="0" />
										<thead>
											<tr>
												<th scope="row" data-class="expand"
													class="footable-first-column">No.</th>
												<th scope="row" data-hide="phone">자료정보</th>
												<th scope="row" data-hide="phone">등록번호</th>
												<th scope="row" data-hide="phone">대출일자</th>
												<th scope="row" data-hide="expand">대출기한</th>
												<th scope="row" data-hide="expand">상태</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${rentalList }" var="list"
												varStatus="status">
												<c:if
													test="${(list.renFlag eq 'brw' or list.renFlag eq 'outbrw') and list.renIdvDelFlag eq '0'}">
													<c:set var="Num" value="${Num+1}" />
													<tr class="first">
														<td class="num expand footable-first-column">${Num }</td>
														<td class="type">${list.bookName }</td>
														<td class="bookNum">${list.bookNum}</td>
														<td class="renDate">${list.renBrwDate }</td>
														<td><span class="renBrwInvDAte">
																${list.renBrwInvDate } </span></td>
														<td><span class="status"> <c:choose>
																	<c:when test="${list.renFlag == 'brw' }">대출중
													<a href="bookReturn.bk?id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${list.bookNum}&state=${list.bookState}&renNum=${list.renIdvNum}" style="font-weight: bold;">
																			(반납하기)</a>
																	</c:when>
																	<c:when test="${list.renFlag eq 'outbrw' }">
																관외대출중
															</c:when>
																</c:choose>
														</span>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</c:when>
									<c:otherwise>
										<h3>
											<span class="empty"> 대출 내역이 없습니다. </span>
										</h3>
									</c:otherwise>
								</c:choose>
								</tbody>
							</table>
						</div>
					</div>
					<br> <br> <br>
					<!-- rentalList -->


					<div class="searchHeader">
						<h3 class="open">예약 내역</h3>
					</div>
					<div class="searchContents">

						<!--  if(renFlag== 'rev' -> 'isRev'='true', if(isRev=='true')-> select: renFlag:'rev' -->

						<c:set var="isRev" value="false" />
						<c:forEach items="${rentalList }" var="list" varStatus="status">
							<c:if
								test="${((list.renFlag eq 'rev') or (list.renFlag eq 'outrev')) and list.renIdvDelFlag eq '0' }">
								<c:set var="isRev" value="true" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${isRev eq 'true' }">
								<c:set var="Num" value="0" />

								<div class="listTable">
									<table class="searchTable default">
										<tbody>
										<thead>
											<tr>
												<th scope="row" data-class="expand"
													class="footable-first-column">No.</th>
												<th scope="row" data-hide="phone">자료정보</th>
												<th scope="row" data-hide="phone">등록번호</th>
												<th scope="row" data-hide="phone">예약일자</th>
												<th scope="row" data-hide="expand">예약만기일</th>
												<th scope="row" data-hide="expand">도서관</th>
												<th scope="row" colspan="2" data-hide="expand">예약상태</th>
											</tr>
										</thead>
										<c:forEach items="${rentalList }" var="list"
											varStatus="status">
											<c:if
												test="${((list.renFlag eq 'rev') or (list.renFlag eq 'outrev')) and list.renIdvDelFlag eq '0'}">
												<c:set var="Num" value="${Num+1}" />
												<tr class="first">
													<td class="num expand footable-first-column">${Num}</td>
													<td class="type">${list.bookName }</td>
													<td class="bookNum">${list.bookNum}</td>
													<td class="renDate">${list.renRevDate }</td>
													<td><span class="renBrwInvDAte">
															${list.renRevInvDate } </span></td>
													<td><span class="libCode"> <c:choose>
																<c:when test="${book.libCode == 'LIB001' }">대구광역시립 중앙도서관</c:when>
																<c:when test="${book.libCode == 'LIB002' }">대구광역시립 남부도서관</c:when>
																<c:otherwise>대구광역시립 수성도서관</c:otherwise>
															</c:choose></span></td>
													<td><c:choose>
															<c:when test="${list.bookState eq 2 }">
																<a
																	href="bookRental.bk?id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${list.bookNum}&libCode=${list.libCode}&state=${list.bookState}&renNum=${list.renIdvNum}">
																	예약중(대출가능)</a>
															</c:when>

															<c:when test="${list.bookState eq 7 || list.bookState eq 8 }">관외예약중<a
																	href="bookRevCancel.bk?id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${list.bookNum}&state=${list.bookState}&renNum=${list.renIdvNum}">(예약취소)</a>
															</c:when>
															<c:when test="${list.bookState eq 6 }">예약중<a
																	href="bookRevCancel.bk?id=${sessionScope.userID }&index=${sessionScope.userIndex}&bookNum=${list.bookNum}&state=${list.bookState}&renNum=${list.renIdvNum}">(예약취소)</a>
															</c:when>
															<c:when test="${list.bookState eq 5 }">관외예약중(대출가능)
															</c:when>
															<c:when test="${list.bookState eq 9 }">관외예약중(도서이동준비)</c:when>
														</c:choose></td>
												</tr>
											</c:if>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</c:when>
							<c:otherwise>
								<span class="empty"><h3>예약 내역이 없습니다.</h3></span>
							</c:otherwise>
						</c:choose>
						<!--  reservation List -->

					</div>
					<br> <br> <br>

					<div class="searchHeader">
						<h3 class="open">대출 히스토리</h3>
					</div>
					<div class="searchContents">

						<c:set var="isBrw" value="false" />
						<c:forEach items="${rentalList }" var="list" varStatus="status">
							<c:if
								test="${(list.renFlag eq 'brw' or list.renFlag eq 'outbrw') and list.renIdvDelFlag eq '1'}">
								<c:set var="isBrw" value="true" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${isBrw eq 'true'}">
								<c:set var="Num" value="0" />

								<div class="listTable">
									<table class="searchTable default">
										<thead>
											<tr>
												<th scope="row" data-class="expand"
													class="footable-first-column">No.</th>
												<th scope="row" data-hide="phone">자료정보</th>
												<th scope="row" data-hide="phone">대출일자</th>
												<th scope="row" data-hide="expand">반납일자</th>
												<th scope="row" data-hide="expand">도서관</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${rentalList }" var="list"
												varStatus="status">
												<c:if
													test="${(list.renFlag eq 'brw' or list.renFlag eq 'outbrw') and list.renIdvDelFlag eq '1'}">
													<c:set var="Num" value="${Num+1}" />
													<tr class="first">
														<td class="num expand footable-first-column">${Num }</td>
														<td class="type">${list.bookName }</td>
														<td class="renDate">${list.renBrwDate }</td>
														<td><span class="renBrwInvDAte">
																${list.renReturnDate } </span></td>
														<td><span class="libCode"> <c:choose>
																	<c:when test="${book.libCode == 'LIB001' }">대구광역시립 중앙도서관</c:when>
																	<c:when test="${book.libCode == 'LIB002' }">대구광역시립 남부도서관</c:when>
																	<c:otherwise>대구광역시립 수성도서관</c:otherwise>
																</c:choose></span></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:when>
							<c:otherwise>
								<span class="empty"><h3>지난 대출 내역이 없습니다.</h3></span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>

	</c:otherwise>
</c:choose>