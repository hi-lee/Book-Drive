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
						<a href="AdminloginForm.logC" title="로그인">로그인</a>
					</c:when>
					<c:otherwise>
						<a href="Adminlogout.logC" title="로그아웃">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</li>
			<li><a>
				${empty sessionScope.id ? '' : sessionScope.id.concat(' 님') }
			</a></li>
		</ul>
	</div>
</div>
<!-- header -->
<div class="header">
	<h1 class="logo"><a href="Adminmain.logC" title="BookDrive"><img src="bdstyle/admin/image/ko/solution/common/logo_admin.png"></a></h1>
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
										<a href="AdminloginForm.logC" title="로그인">로그인</a>					
									</li>
								</c:when>
								<c:otherwise>
									<li class="last">
										<a href="Adminlogout.logC" title="로그아웃">로그아웃</a>					
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>
			<li class="">
					<a href="javascript:return false;" title="회원관리">회원관리</a>
					<div>
					<ul>
					<li>
						<a href="MemberList.memA" title="일반회원 목록">일반회원 목록</a>					
					</li>
					<li>
						<a href="LibAdminList.lib" title="도서관 관리자 정보">도서관 관리자 목록</a>					
					</li>
					<li class="last">
						<a href="AdminList.memA" title="통합 관리자 정보">통합 관리자 목록</a>					
					</li>
					</ul>
					</div>
				</li>
			<li class="">
					<a href="javascript:return false;" title="도서관정보">도서관정보</a>
					<div>
					<ul>
					<li>
						<a href="LibraryList.lib" title="도서관 목록">도서관 목록</a>					
					</li>
					<li class="last">
						<a href="LibraryApvList.lib" title="도서관 관리자 가입신청 확인">도서관 관리자 가입신청 확인</a>					
					</li>
					</ul>
					</div>
				</li>
			<li>
					<a href="javascript:return false;" title="도서관리">도서관리</a>
					<div>
						<ul>
							<li>
								<a href="BookList.bookA" title="도서 목록">도서 목록</a>					
							</li>
							<!--<li>
								<a href="BookImageInsert.bookA" title="도서이미지가져오기">도서이미지가져오기</a>					
							</li>-->
							<li>
								<a href="BookBrwList.bookA" title="도서대출내역">도서 대출내역</a>
							</li>
							<li>
								<a href="BookRevList.bookA" title="도서예약내역">도서 예약내역</a>
							</li>
							<li>
								<a href="BookOutBrwList.bookA" title="도서 관외대출내역">도서 관외대출내역</a>					
							</li>
							<li class="last">
								<a href="BookOutRevList.bookA" title="도서관외예약내역">도서 관외예약내역</a>					
							</li>
						</ul>
					</div>
				</li>
			<li>
					<a href="javascript:return false;" title="게시판관리">게시판관리</a>
					<div>
						<ul>
							<li>
								<a href="BoardList1.boardA" title="공지사항관리">공지사항관리</a>					
							</li>
							<li>
								<a href="BoardList2.boardA" title="QnA관리">QnA관리</a>					
							</li>
							<li>
								<a href="BoardList3.boardA" title="희망도서관리">희망도서관리</a>					
							</li>
							<li class="last">
								<a href="BoardList4.boardA" title="자유게시판관리">자유게시판관리</a>					
							</li>
						</ul>
					</div>
				</li>
			</ul>
	</div>
	<a href="https://cham.kiu.ac.kr/#" class="wholeMenuOpen" title="전체메뉴"><img src="bdstyle/admin/image/ko/local/wholeMenuOpen.png" alt="전체메뉴"></a>
	<!-- //Top Menu -->
	<!-- Whole Menu -->
	<div class="wholeMenu">
		<h2 class="skip">전체메뉴보기</h2>
		<ul>
			<li><h3><a href="javascript:return false;" title="회원관리">회원관리</a></h3>
						<ul>
						<li>
							<a href="MemberList.memA" title="일반회원목록">일반회원 목록</a>				
							</li>
						<li>
							<a href="LibAdminList.lib" title="도서관 관리자 목록">도서관 관리자 목록</a>				
							</li>
						<li>
							<a href="AdminList.memA" title="통합 관리자 목록">통합 관리자 목록</a>				
							</li>
						</ul>
					</li>
					<li><h3><a href="javascript:return false;" title="도서관정보">도서관정보</a></h3>
						<ul>
						<li>
							<a href="LibraryList.lib" title="도서관 목록">도서관 목록</a>				
							</li>
						<li>
							<a href="LibraryApvList.lib" title="도서관 관리자 가입신청 확인">도서관 관리자 가입신청 확인</a>				
							</li>
						</ul>
					</li>
					<li><h3><a href="javascript:return false;" title="도서관리">도서관리</a></h3>
						<ul>
						<li>
							<a href="BookList.bookA" title="도서목록">도서목록</a>				
							</li>
						<li>
							<a href="BookBrwList.bookA" title="도서대출내역">도서 대출내역</a>				
							</li>
						<li>
							<a href="BookRevList.bookA" title="도서예약내역">도서 예약내역</a>				
							</li>
						<li>
							<a href="BookOutBrwList.bookA" title="도서관외대출내역">도서 관외대출내역</a>				
							</li>
						<li>
							<a href="BookOutRevList.bookA" title="도서관외예약내역">도서 관외예약내역</a>				
							</li>
						</ul>
					</li>
					<li class="last"><h3><a href="javascript:return false;" title="게시판관리">게시판관리</a></h3>
						<ul>
						<li>
							<a href="BoardList1.boardA" title="공지사항관리">공지사항관리</a>				
							</li>
						<li>
							<a href="BoardList2.boardA" title="QnA관리">QnA관리</a>				
							</li>
						<li>
							<a href="BoardList3.boardA" title="희망도서관리">희망도서관리</a>				
							</li>
						<li>
							<a href="BoardList4.boardA" title="자유게시판관리">자유게시판관리</a>				
							</li>
						</ul>
					</li>
					</ul>
		<a href="https://cham.kiu.ac.kr/#" class="wholeMenuClose" title="전체메뉴 닫기"><img src="bdstyle/admin/image/ko/local/wholeMenuClose.gif" alt="전체메뉴 닫기"></a>
	</div>
</div>
</div>
<!-- menu_top END -->