<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.getParameter("id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="경일대학교 도서관">
<meta name="keyword"
	content="경일대학교 전자도서관, 경일대학교, 전자도서관, 경일대학교 도서관, 도서검색">
<meta name="copyright" content="@copy; KYUNGIL UNIVERSITY LIBRARY.">
<meta
	content="initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no"
	name="viewport">
<meta name="format-detection" content="telephone=no">
<!--아이폰 전화번호 스타일 초기화-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BookDrive</title>
<!-- 홈페이지 이름 -->
<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/standard/common.css">
<link rel="stylesheet" href="bdstyle/style/ko/thema/pc/main.css">
<link rel="stylesheet" href="bdstyle/style/ko/thema/tablet/main.css"
	media="screen and (min-width:768px) and (max-width:1024px)">
<link rel="stylesheet" href="bdstyle/style/ko/thema/mobile/main.css"
	media="screen and (max-width:767px)">

<!-- SCRIPT -->
<script type="text/javascript">
	var imgPath = 'bdstyle/image/';
</script>
<script type="text/javascript" src="bdstyle/script/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="bdstyle/script/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="bdstyle/script/main.js"></script>
<script type="text/javascript" src="bdstyle/js/common/cookie.js"
	charset="utf-8"></script>
<style>
.search {
	background-image: url(bdstyle/image/search.jpg);
	max-width:100%;height:600px;
	background-size: cover;

}

.ikc-search-wrap {
	position: relative;
	max-width: 870px;
	padding: 25px;
	margin: 0 auto 0;
	background-color: rgba(255, 255, 255, .6);
	
	
	
}

.ikc-search-wrap .ikc-search form {
	position: relative;
	width: 100%;
	height: 66px;
	background-color: #fff;
	box-shadow: 0 3px 8px 0 rgba(0, 0, 0, .2)
}

.ikc-search-wrap .ikc-search form span {
	width: 100%;
	border: none
}

.ikc-search-wrap .ikc-search form input {
	height: 66px;
	padding: 0;
	text-indent: 25px;
	border: 3px solid #ddccac;
	border-radius: 0;
	box-sizing: border-box;
	color: #292a2c
}

.ikc-search-wrap .ikc-search form input::-webkit-input-placeholder {
	color: #86868a
}

.ikc-search-wrap .ikc-search form input:-ms-input-placeholder {
	color: #86868a
}

.ikc-search-wrap .ikc-search form input::-moz-placeholder {
	color: #86868a
}

.ikc-search-wrap .ikc-search form input:-moz-placeholder {
	color: #86868a
}

.ikc-search-wrap .ikc-search form input[type=text]::-ms-clear {
	display: none
}

.ikc-search-wrap .ikc-search form .searchBtn {
	position: absolute;
	top: 0;
	right: 0;
	width: 0;
	height: 66px;
	padding: 0;
	margin: 0;
	padding: 0 20px 0 45px;
	background: url(bdstyle/image/ko/solution/common/ico/searchBtnBg.png)
		no-repeat 20px 50% #ddccac;
	border: 1px solid #ddccac;
	color: #fff;
}
#divContents {
	width: 100%;
	padding: 0;
}
</style>


<!-- divHeader -->
<script type="text/javascript">
	/*
	 $(document).ready(function(){
	 callThumbnail('#loanBestList1','9788952932808','CAT','000000501414');
	 callThumbnail('#loanBestList2','9788991130197','CAT','000000225695');
	 callThumbnail('#loanBestList3','','CAT','000000176779');
	 callThumbnail('#loanBestList4','','CAT','000000190415');
	 callThumbnail('#loanBestList5','895288048X/89','CAT','000000190416');
	 callThumbnail('#newArrivalList1','9791196457051','CAT','000000507480');
	 callThumbnail('#newArrivalList2','9788962463958','CAT','000000507383');
	 callThumbnail('#newArrivalList3','9788935212859','CAT','000000506928');
	 callThumbnail('#newArrivalList4','9791162540886','CAT','000000507017');
	 callThumbnail('#newArrivalList5','9791187059509','CAT','000000507404');
	 });
	 */
</script>
</head>
<body>
	<div id="divAccessibility">
		<ul>
			<li><a href="https://cham.kiu.ac.kr/#divTopMenu">메뉴 바로가기</a></li>
			<li><a href="https://cham.kiu.ac.kr/#divContents">본문
					바로가기(skip to content)</a></li>
			<li><a href="https://cham.kiu.ac.kr/#divSearch">검색 바로가기</a></li>
			<li><a href="https://cham.kiu.ac.kr/#divFooter">도서관정보 바로가기</a></li>
		</ul>
	</div>
	<div id="divWrapper">
		<jsp:include page="/bdstyle/include/menu_top.jsp" />
		<hr>
		<div id="divContentsW">
			<div id="divContents">

				<div class="search">
					<br> <br> <br> <br> <br> <br>
					<div class="ikc-search-wrap">
						<div class="ikc-search">
							<form name="searchForm" ng-submit="search()"
								class="ng-pristine ng-valid" action="bookSearchPro.bk">
								<span tabindex="-1" role="presentation"
									class="k-widget k-autocomplete k-header ikc-search-keyword ng-pristine ng-untouched ng-valid k-state-default"
									style=""><input ik-auto-complete="" name="value"
									class="ikc-search-keyword ng-pristine ng-untouched ng-valid k-input ng-empty"
									placeholder="소장자료를 한번에 검색합니다." id="keyword" ng-model="keyword"
									data-role="autocomplete" type="text" autocomplete="off"
									role="textbox" aria-haspopup="true" aria-disabled="false"
									aria-readonly="false" aria-owns="keyword_listbox"
									aria-autocomplete="list" style="width: 100%;"><span
									class="k-icon k-loading" style="display: none"></span></span>
								<button type="submit" class="searchBtn"></button>
							</form>
						</div>
					</div>
				</div>



			</div>
		</div>

		<hr>
		<jsp:include page="/bdstyle/include/menu_bottom.jsp" />
	</div>
</body>
</html>