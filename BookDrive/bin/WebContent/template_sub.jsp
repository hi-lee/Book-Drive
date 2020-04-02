<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="경일대학교 도서관">
	<meta name="keyword" content="경일대학교 전자도서관, 경일대학교, 전자도서관, 경일대학교 도서관, 도서검색">
	<meta name="copyright" content="@copy; KYUNGIL UNIVERSITY LIBRARY.">
	<meta content="initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no" name="viewport">
	<meta name="format-detection" content="telephone=no"><!--아이폰 전화번호 스타일 초기화-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>BookDrive</title> <!-- 홈페이지 이름 -->
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="bdstyle/style/ko/standard/common.css">
	<link rel="stylesheet" href="bdstyle/style/ko/thema/pc/sub.css">
	<link rel="stylesheet" href="bdstyle/style/ko/thema/tablet/sub.css" media="screen and (min-width:768px) and (max-width:1024px)">
	<link rel="stylesheet" href="bdstyle/style/ko/thema/mobile/sub.css" media="screen and (max-width:767px)">
	<link rel="stylesheet" href="bdstyle/style/ui/jquery.mThumbnailScroller.css">
	
	<!-- SCRIPT -->
	<script type="text/javascript">var imgPath = 'bdstyle/image/';</script>
	<script type="text/javascript" src="bdstyle/script/footable.js"></script>
	<script type="text/javascript" src="bdstyle/script/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="bdstyle/script/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="bdstyle/script/jquery.mThumbnailScroller.js"></script>
	<script type="text/javascript" src="bdstyle/script/sub.js"></script>
    <script type="text/javascript" src="bdstyle/js/common/cookie.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
	<script type="text/javascript" src="script/css3-mediaqueries.js"></script>
	<script type="text/javascript" src="/script/respond.src.js"></script>
	<![endif]-->
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
		<li><a href="https://cham.kiu.ac.kr/#divContents">본문 바로가기(skip to content)</a></li>
		<li><a href="https://cham.kiu.ac.kr/#divSearch">검색 바로가기</a></li>
		<li><a href="https://cham.kiu.ac.kr/#divFooter">도서관정보 바로가기</a></li>
	</ul>
</div>
<div id="divWrapper">
	<jsp:include page="/bdstyle/include/menu_top.jsp"/>
	<hr>
	<jsp:include page="${pageIn }"/>
	<hr>
	<jsp:include page="/bdstyle/include/menu_bottom.jsp"/>
</div>
</body>
</html>