<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String pageIn = request.getAttribute("pagefile") != null ? (String) request.getAttribute("pagefile") : "/member/lib_loginForm.jsp";
	System.out.println("pageIn : " + pageIn);
%>

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
	<link rel="stylesheet" type="text/css" href="bdstyle/admin/style/ko/standard/admin_common.css">
	<c:choose>
		<c:when test="${pagefile eq '/member/lib_main.jsp'}">
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/pc/admin_main.css">
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/tablet/admin_main.css" media="screen and (min-width:768px) and (max-width:1024px)">
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_main.css" media="screen and (max-width:767px)">
		</c:when>
		<c:otherwise>
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/pc/admin_sub.css">
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/tablet/admin_sub.css" media="screen and (min-width:768px) and (max-width:1024px)">
			<link rel="stylesheet" href="bdstyle/admin/style/ko/thema/mobile/admin_sub.css" media="screen and (max-width:767px)">
		</c:otherwise>
	</c:choose>
	<link rel="stylesheet" href="bdstyle/admin/style/ui/jquery.mThumbnailScroller.css">
	
	<!-- SCRIPT -->
	<script type="text/javascript">var imgPath = 'bdstyle/admin/image/';</script>
	<script type="text/javascript" src="bdstyle/admin/script/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="bdstyle/admin/script/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="bdstyle/admin/script/jquery.mThumbnailScroller.js"></script>
	<script type="text/javascript" src="bdstyle/admin/script/sub.js"></script>
    <script type="text/javascript" src="bdstyle/admin/js/common/cookie.js" charset="utf-8"></script>
    <script type="text/javascript" src="bdstyle/admin/script/footable.js"></script>
    
    <!--[if lt IE 9]>
	<script type="text/javascript" src="script/css3-mediaqueries.js"></script>
	<script type="text/javascript" src="/script/respond.src.js"></script>
	<![endif]-->
	<!-- divHeader -->
<script type="text/javascript">
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
	<jsp:include page="/bdstyle/admin/include/lib_menu_top.jsp"/>
	<hr>
	<jsp:include page="<%=pageIn %>"/> <!-- 테스트용으로 사용한다 -->
	<hr>
	<jsp:include page="/bdstyle/admin/include/admin_menu_bottom.jsp"/>
</div>
</body>
</html>