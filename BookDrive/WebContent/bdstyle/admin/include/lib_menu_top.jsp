<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- menu_top START -->
<div id="divHeader">
<div id="divGlobalMenu">
	<div>
		<ul>
			<li class="login">
				<c:choose>
					<c:when test="${sessionScope.id eq '' or sessionScope.id eq null}">
						<a href="AdminloginForm.logL" title="로그인">로그인</a>
					</c:when>
					<c:otherwise>
						<a href="Adminlogout.logL" title="로그아웃">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</li>
			<li><a>
				${empty sessionScope.id ? '' : sessionScope.id.concat(' 님').concat('(').concat(sessionScope.code).concat(', ').concat(sessionScope.libname).concat(')') }
			</a></li>
		</ul>
	</div>
</div>
<!-- header -->
<div class="header">
	<h1 class="logo"><a href="Adminmain.logL" title="BookDrive"><img src="bdstyle/admin/image/ko/solution/common/logo_lib.png"></a></h1>
	<div id="divTopMenu">
		<h2 class="skip">주메뉴</h2>
		<ul>
			<li class="">
					<c:choose>
						<c:when test="${sessionScope.id eq '' or sessionScope.id eq null}">
							<a href="javascript:return false;" title="로그인">로그인</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:return false;" title="로그아웃">로그아웃</a>
						</c:otherwise>
					</c:choose>
					<div>
						<ul>
							<c:choose>
								<c:when test="${sessionScope.id eq '' or sessionScope.id eq null}">
									<li>
										<a href="AdminloginForm.logL" title="로그인">로그인</a>					
									</li>
								</c:when>
								<c:otherwise>
									<li class="last">
										<a href="Adminlogout.logL" title="로그아웃">로그아웃</a>					
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>
			<li class="">
					<a href="javascript:return false;" title="관리자정보">관리자정보</a>
					<div>
					<ul>
						<li class="last">
							<a href="LibAdminInfo.libL" title="도서관 관리자 정보">도서관 관리자 정보</a>					
						</li>
					</ul>
					</div>
				</li>
			<li class="">
					<a href="javascript:return false;" title="도서관정보">도서관정보</a>
					<div>
					<ul>
					<li class="last">
						<a href="LibraryInfo.libL?libcode=${sessionScope.code}" title="도서관정보">도서관정보</a>					
					</li>
					</ul>
					</div>
				</li>
			<li>
					<a href="javascript:return false;" title="도서관리">도서관리</a>
					<div>
						<ul>
							<li>
								<a href="BookList.bookL?libcode=${sessionScope.code}" title="도서검색">도서검색</a>					
							</li>
							<li>
								<a href="BookInsertForm.bookL" title="도서등록">도서등록</a>					
							</li>
							<li>
								<a href="BookRevList.bookL?libcode=${sessionScope.code}" title="도서예약내역">도서예약내역</a>
							</li>
							<li>
								<a href="BookBrwList.bookL?libcode=${sessionScope.code}" title="도서대출내역">도서대출내역</a>					
							</li>
							<li>
								<a href="BookOutRevList.bookL?libcode=${sessionScope.code}" title="도서관외예약내역">도서관외예약내역</a>					
							</li>
							<li class="last">
								<a href="BookOutBrwList.bookL?libcode=${sessionScope.code}" title="도서관외대출내역">도서관외대출내역</a>					
							</li>
						</ul>
					</div>
				</li>
			<li>
					<a href="javascript:return false;" title="게시판관리">게시판관리</a>
					<div>
						<ul>
							<li>
								<a href="BoardList1.boardL" title="공지사항보기">공지사항보기</a>					
							</li>
							<li>
								<a href="BoardList2.boardL?libcode=${sessionScope.code}" title="QnA보기">QnA보기</a>					
							</li>
							<li class="last">
								<a href="BoardList3.boardL?libcode=${sessionScope.code}" title="희망도서 요청 보기">희망도서 요청 보기</a>					
							</li>
						</ul>
					</div>
				</li>
			</ul>
	</div>
	<a href="javascript:void();" class="wholeMenuOpen" title="전체메뉴"><img src="bdstyle/admin/image/ko/local/wholeMenuOpen.png" alt="전체메뉴"></a>
	<!-- //Top Menu -->
	<!-- Whole Menu -->
	<div class="wholeMenu">
		<h2 class="skip">전체메뉴보기</h2>
		<ul>
			<li><h3><a href="javascript:return false;" title="관리자정보">관리자정보</a></h3>
						<ul>
						<li>
							<a href="LibAdminInfo.libL" title="도서관 관리자 정보">도서관 관리자 정보</a>				
							</li>
						</ul>
					</li>
					<li><h3><a href="javascript:return false;" title="도서관정보">도서관정보</a></h3>
						<ul>
						<li>
							<a href="LibraryInfo.libL?libcode=${sessionScope.code}" title="도서관정보">도서관정보</a>				
							</li>
						</ul>
					</li>
					<li><h3><a href="javascript:return false;" title="도서관리">도서관리</a></h3>
						<ul>
						<li>
								<a href="BookList.bookL?libcode=${sessionScope.code}" title="도서검색">도서검색</a>					
							</li>
							<li>
								<a href="BookInsertForm.bookL" title="도서등록">도서등록</a>					
							</li>
							<li>
								<a href="BookRevList.bookL?libcode=${sessionScope.code}" title="도서예약내역">도서예약내역</a>
							</li>
							<li>
								<a href="BookBrwList.bookL?libcode=${sessionScope.code}" title="도서대출내역">도서대출내역</a>					
							</li>
							<li>
								<a href="BookOutRevList.bookL?libcode=${sessionScope.code}" title="도서관외예약내역">도서관외예약내역</a>					
							</li>
							<li>
								<a href="BookOutBrwList.bookL?libcode=${sessionScope.code}" title="도서관외대출내역">도서관외대출내역</a>					
							</li>
						</ul>
					</li>
					<li class="last"><h3><a href="javascript:return false;" title="게시판관리">게시판관리</a></h3>
						<ul>
						<li>
								<a href="BoardList1.boardL" title="공지사항보기">공지사항보기</a>					
							</li>
							<li>
								<a href="BoardList2.boardL?libcode=${sessionScope.code}" title="QnA보기">QnA보기</a>					
							</li>
							<li class="last">
								<a href="BoardList3.boardL?libcode=${sessionScope.code}" title="희망도서 요청 보기">희망도서 요청 보기</a>					
							</li>
						</ul>
					</li>
					</ul>
		<a href="javascript:void();" class="wholeMenuClose" title="전체메뉴 닫기"><img src="bdstyle/admin/image/ko/local/wholeMenuClose.gif" alt="전체메뉴 닫기"></a>
	</div>
</div>
</div>
<!-- menu_top END -->