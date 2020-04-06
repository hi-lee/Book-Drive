<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript" src="bdstyle/script/jquery.selectBox.js"></script>
<script type="text/javascript" src="bdstyle/js/search/main.js"></script>
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/advanced.css">
<style>
#divContents {
	position: relative;
	width: 1040px;
	min-height: 700px;
	margin: 0 auto;
	padding: 0 0 65px 0;
}
</style>

<div id="divContentsW">
	<div id="divContents">
		<h2 id="divTitle">소장자료검색</h2>

		<div id="divLocation">
			<ul>
				<li><a href="template_main.jsp" title="HOME"> <img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li><a href="#">소장자료검색</a></li>
				<li><a href="#">전체</a></li>
			</ul>
		</div>
		<!-- content start -->
		<div id="divContent">

			<!-- advancedSearch -->
			<div class="advancedSearch">
				<form id="search" action="bookSearchPro.bk" name="search"
					method="get">
					<input type="hidden" name="index" value="${sessionScope.userIndex}">
					<!-- 디지털컬렉션 파라미터  -->
					<fieldset>
						<legend>통합검색 </legend>
						<div class="searchKeyword">
							<select id="si0" name="search"
								class="searchOpt1 selectSearchItem selectBox"
								title="검색 항목을 선택하는곳" size="1" style="display: none;">
								<option value="TOTAL">전체</option>
								<option value="bookName" selected="selected">서명</option>
								<option value="bookWriter">저자</option>
								<option value="bookPub">출판사</option>
								<option value="ISBN">ISBN</option>
								<!--  	<option value="5">서명 or 저자</option>	-->
							</select> <input type="text" name="value" value=""
								onfocus="setTextObj(this)"
								class="inputTextType1 inputSearchKeyword" title="검색어를 입력하세요">
						</div>


					</fieldset>
					<div class="buttons">
						<input type="submit" value="검색" title="검색" class="searchBtn">
						<a href="SearchHistory.bk" title="검색 History">검색 History</a> <input
							id="resetAll" type="button" value="다시쓰기" title="다시쓰기">
					</div>
			</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#resetAll").click(function() {
				document.search.reset();
				$(".searchOpt1").selectBox('refresh').selectBox();
			});
		});
	</script>
</div>
</div>
<hr>
</div>


