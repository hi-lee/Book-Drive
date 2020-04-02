<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관외 예약 내역</title>
<script>
function chkForm(f){
	document.rentalform.submit();
}
</script>
</head>
<body>
	<c:set var="isOutRev" value="false" />
	<c:forEach items="${rentalList }" var="list">
		<c:if test="${list.renFlag eq 'outrev' && list.renIdvDelFlag eq '0' && (list.bookState eq 5)}">
			<c:set var="isOutRev" value="true" />
		</c:if>
	</c:forEach>
<form name="rentalform" onsubmit="return chkForm(this)">
	<c:choose>
		<c:when test="${isOutRev eq 'true' }">
			<c:forEach items="${rentalList }" var="list">
				<c:if
					test="${list.renFlag eq 'outrev' && list.renIdvDelFlag eq '0' && (list.bookState eq 5)}">
					${list.bookName }<a
						href="bookRental.bk?usage=1&index=${sessionScope.memIndex}&bookNum=${list.bookNum}&libCode=${list.libCode}&state=${list.bookState}&renNum=${list.renIdvNum}">대출하기</a>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
				대출가능한 관외 예약 내역이 없습니다. 
			</c:otherwise>
	</c:choose>
	</form>

</body>
</html>