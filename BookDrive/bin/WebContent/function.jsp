<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
toastr.options = {
	"closeButton" : false,
	"debug" : false,
	"newestOnTop" : false,
	"progressBar" : true,
	"positionClass" : "toast-bottom-center",
	"preventDuplicates" : false,
	"onclick" : null,
	"showDuration" : "300",
	"hideDuration" : "1000",
	"timeOut" : "3000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut"
}
toastr.success("승인완료", "승인이 완료되었습니다");
</script>
</html>