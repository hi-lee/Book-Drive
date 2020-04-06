<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="bdstyle/style/ko/home/basket.css">

<style>
.empty {
	text-align: center;
}
</style>
<div id="divContentsW">
	<div id="divContents">

		<h2 id="divTitle">보관함</h2>
		<div id="divLocation">
			<ul>
				<li><a href="#" title="HOME"><img
						src="bdstyle/image/ko/local/home.png" alt="HOME"></a></li>
				<li>보관함</li>
			</ul>
		</div>
		<script type="text/javascript">
			var saveBtn = "저장";
			var mailBtn = "메일";
			var printBtn = "인쇄";
			var previewBtn = "미리보기";
			var endNoteBtn = "EndNote";
			var refWorksBtn = "RefWorks";
		</script>
		<div id="divContent">
			<c:choose>
				<c:when test="${empty cartList}">
					<span class="empty"><h3>보관함에 담긴 책이 없습니다.</h3></span>
				</c:when>
				<c:otherwise>
				
					<div class="basketHeader">
						<p>
							<input type="checkbox" id="allcheck" name="allcheck"
								onclick="checkedAll(this, 'basketFrm', 'data')"
								onkeypress="checkedAll(this, 'basketFrm', 'data')" title="바구니넣기">
							&nbsp;&nbsp;&nbsp;&nbsp; 서명 / 저자 / 출판사 / ISBN / 출판년 / 소장처
						</p>
						<div class="fileSend">&nbsp;</div>
					</div>

					<form name="basketFrm" id="basketFrm" method="post"
						action="bookCartDel.bk" onsubmit="return checked(this);">
						<c:forEach var="list" items="${cartList}" varStatus="status">
							<ul class="basketList">
								<li>
									<p>
										<input type="checkbox" name="data" value="${list.cartNum }"
											class="checkbox" title="바구니넣기"> <span class="num">${status.count }</span>
									</p>
									<dl>
										<dt>서명</dt>
										<dd class="title">
											<img alt="도서" title="도서"
												src="bdstyle/image/ko/solution/common/ico/type_m.png">
											<a href="bookInfo.bk?bookNum=${list.bookNum }">${list.bookName }</a>
										</dd>
										<input type="hidden" name="index" value="${param.index }">
										<dt>저자</dt>
										<dd>${list.bookWriter }글</dd>
										<dt>출판사</dt>
										<dd>${list.bookPub }</dd>
										<dt>ISBN</dt>
										<dd>${list.ISBN }</dd>
										<dt>출판년</dt>
										<dd>${list.bookPubDate }</dd>
										<dt>소장처</dt>
										<dd>
											<c:choose>
												<c:when test="${list.libCode == 'LIB001' }">대구광역시립 중앙도서관</c:when>
												<c:when test="${list.libCode == 'LIB002' }">대구광역시립 남부도서관</c:when>
												<c:otherwise>대구광역시립 수성도서관</c:otherwise>
											</c:choose>
										</dd>
										<dd></dd>
									</dl>
								</li>
							</ul>
						</c:forEach>
						<div class="buttons">
							<a
								href="bookSearchPro.bk?index=${sessionScope.userIndex}&search=${param.search}&value=${param.value}" title="검색결과 돌아가기" class="LinkButton">검색결과 돌아가기</a> 
								<input type="submit" value="삭제" title="삭제"> 
								<a href="#" onclick="document.getElementById('allcheck').checked=true;checkedAll(document.getElementById('allcheck'),'basketFrm','data');document.basketFrm.submit();"
								title="바구니비우기" class="LinkButton">바구니비우기</a>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		<script type="text/javascript" src="bdstyle/js/common/Checkbox.js"></script>
		<script type="text/javascript">
			function checked(form) {
				var ret = isEverChecked(form.data);
				var MSG_CHECK_FIELDS = "항목을 체크하세요.";
				if (!ret) {
					form.target = "_self";
					alert(MSG_CHECK_FIELDS);
				}
				return ret;
			}

			function actionChange(action) {

				var frm = document.basketFrm;

				//target 초기화.(mycolSave 에서 타겟이 변경되었을 경우를 대비)
				frm.target = "_self";

				if (action == "detail") {
					//체크박스가 선택되지 않았을 경우 첫번째 데이터를 action으로 설정.
					var firstData = frm.firstData.value;

					//체크박스의 제일 처음 선택된 값을 action 값으로 설정.
					var chkbox = frm.data;
					if (chkbox != null) {
						var chkboxLength = chkbox.length;
						for (var i = 0; i < chkboxLength; i++) {
							if (chkbox[i].checked) {
								firstData = chkbox[i].value;
								break;
							}
						}
					}

					var itemType = $("#itemType").val();
					if (jQuery.trim(itemType) == '') {
						frm.action = "/search/detail/" + firstData;
					} else if (jQuery.trim(itemType) == 'ers') {
						frm.action = "/search/ers/detail/"
								+ firstData.substring(3);
					} else {
						frm.action = "/eds/detail/" + firstData;
					}
				} else if (action == "mycolSave") {
					if (isEverChecked(frm.data)) {
						frm.action = "/mylist/pop/writeitem";
						var options = "width=600,height=550,resizable=no,top=100,left=200,scrollbars=yes";
						window.open('', "mycolSave", options);

						frm.target = "mycolSave";
					} else {
						return false;
					}
				} else if (action == "delete") {
					frm.action = "bookCartDel.bk";
				} else if (action == "digicolSave") {
					if (isEverChecked(frm.data)) {
						var hasEds = false;
						$(document.basketFrm.data)
								.each(
										function() {
											if (this.checked) {
												var item_type = $(this)
														.parent()
														.parent()
														.find(
																"input[name=itemType]")
														.val();
												if (item_type == 'eds'
														|| item_type == 'ers') {
													alert("eds데이터는 digicol에 담을 수 없습니다.");
													hasEds = true;
												}
											}
										});
						if (hasEds) {
							return false;
						} else {
							frm.action = "/digicol/pop/writeitem";
							var options = "width=600,height=550,resizable=no,top=100,left=200,scrollbars=yes";
							window.open('', "digicolSave", options);

							frm.target = "digicolSave";
						}
					} else {
						return false;
					}
					return true;
				} else {
					frm.action = "/search/basket/service";
				}
			}
		</script>
	</div>
</div>