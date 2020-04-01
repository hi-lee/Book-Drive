<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../bdstyle/admin/include/admin_loginCheck.jsp"/>
<script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
<script type="text/javascript" src="bdstyle/admin/script/main.js"></script>
<div id="divContentsW">
	<div id="divContents">
		<dl class="mainSubTitle">
			<dt><img src="bdstyle/admin/image/ko/local/main/arrowOff.png"></dt>
			<dd><span>금일 대여내역</span>
		</dl>
		<div class="contents1"> <!-- 금일 대여내역 -->
			<div class="newArrivalMod">
				<div>
					<ul>
						<li>
							<div class="left">금일 대출건수</div><br>
							<div class="right"><a href="BookBrwList.bookA"><h2 class="MainCountBold">${maincount.todayBrwCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">금일 예약건수</div><br>
							<div class="right"><a href="BookRevList.bookA"><h2 class="MainCountBold">${maincount.todayRevCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">금일 관외대출건수</div><br>
							<div class="right"><a href="BookOutBrwList.bookA"><h2 class="MainCountBold">${maincount.todayOutBrwCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">금일 관외예약건수</div><br>
							<div class="right"><a href="BookOutRevList.bookA"><h2 class="MainCountBold">${maincount.todayOutRevCount}</h2></a>건</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<dl class="mainSubTitle">
			<dt><img src="bdstyle/admin/image/ko/local/main/arrowOff.png"></dt>
			<dd><span>진행중인 대여내역</span>
		</dl>
		<div class="contents2"> <!-- 진행중 대여내역 -->
			<div class="newArrivalMod">
				<div>
					<ul>
						<li>
							<div class="left">진행중인 대출건수</div><br>
							<div class="right"><a href="BookBrwList.bookA"><h2 class="MainCountBold">${maincount.progressBrwCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">진행중인 예약건수</div><br>
							<div class="right"><a href="BookRevList.bookA"><h2 class="MainCountBold">${maincount.progressRevCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">진행중인 관외대출건수</div><br>
							<div class="right"><a href="BookOutBrwList.bookA"><h2 class="MainCountBold">${maincount.progressOutBrwCount}</h2></a>건</div>
						</li>
						
						<li>
							<div class="left">진행중인 관외예약건수</div><br>
							<div class="right"><a href="BookOutRevList.bookA"><h2 class="MainCountBold">${maincount.progressOutRevCount}</h2></a>건</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="contents3">
			<div class="notice">
				<div>
					<h2>공지사항<span style="font-size:10px;color:red;margin-left:15px;">최신글 내용입니다.</span></h2>
					<ul>
						<li class="on">
							<ul>
								<c:forEach var="boardlist" items="${boardlist1}" varStatus="status">
									<li>
										<a href="BoardModifyForm.boardA?boardnum=${boardlist.boardNum}&page=1">
											${boardlist.boardSubject}
										</a>
										<span class="date">${boardlist.boardDate}</span>
									</li>
								</c:forEach>
								<c:if test="${empty boardlist1}">
									<li>
										새 글이 없습니다.
									</li>
								</c:if>
							</ul>
							<p class="more"><a href="BoardList1.boardA" title="공지사항 더보기"><img src="bdstyle/admin/image/ko/local/main/more.gif" alt="공지사항 더보기"></a></p>
			            </li>
					</ul>
				</div>
			</div>
			<div class="notice">
				<div>
					<h2>QnA<span style="font-size:10px;color:red;margin-left:15px;">최신글 내용입니다.</span></h2>
					<ul>
						<li class="on">
							<ul>
								<c:forEach var="boardlist" items="${boardlist2}" varStatus="status">
									<li>
										<a href="BoardModifyForm.boardA?boardnum=${boardlist.boardNum}&page=1">
											${boardlist.boardSubject}
										</a>
										<span class="date">${boardlist.boardDate}</span>
									</li>
								</c:forEach>
								<c:if test="${empty boardlist2}">
									<li>
										새 글이 없습니다.
									</li>
								</c:if>
							</ul>
							<p class="more"><a href="BoardList2.boardA" title="QnA 더보기"><img src="bdstyle/admin/image/ko/local/main/more.gif" alt="공지사항 더보기"></a></p>
			            </li>
					</ul>
				</div>
			</div>
		</div>
		<div class="contents3">
			<div class="notice">
				<div>
					<h2>희망도서<span style="font-size:10px;color:red;margin-left:15px;">최신글 내용입니다.</span></h2>
					<ul>
						<li class="on">
							<ul>
								<c:forEach var="boardlist" items="${boardlist3}" varStatus="status">
									<li>
										<a href="BoardQnADetail.boardA?boardnum=${boardlist.boardNum}&page=1">
											${boardlist.boardSubject}
										</a>
										<span class="date">${boardlist.boardDate}</span>
									</li>
								</c:forEach>
								<c:if test="${empty boardlist3}">
									<li>
										새 글이 없습니다.
									</li>
								</c:if>
							</ul>
							<p class="more"><a href="BoardList3.boardA" title="희망도서 더보기"><img src="bdstyle/admin/image/ko/local/main/more.gif" alt="공지사항 더보기"></a></p>
			            </li>
					</ul>
				</div>
			</div>
			<div class="notice">
				<div>
					<h2>자유게시판<span style="font-size:10px;color:red;margin-left:15px;">최신글 내용입니다.</span></h2>
					<ul>
						<li class="on">
							<ul>
								<c:forEach var="boardlist" items="${boardlist4}" varStatus="status">
									<li>
										<a href="BoardModifyForm.boardA?boardnum=${boardlist.boardNum}&page=1">
											${boardlist.boardSubject}
										</a>
										<span class="date">${boardlist.boardDate}</span>
									</li>
								</c:forEach>
								<c:if test="${empty boardlist4}">
									<li>
										새 글이 없습니다.
									</li>
								</c:if>
							</ul>
							<p class="more"><a href="BoardList4.boardA" title="자유게시판 더보기"><img src="bdstyle/admin/image/ko/local/main/more.gif" alt="공지사항 더보기"></a></p>
			            </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>