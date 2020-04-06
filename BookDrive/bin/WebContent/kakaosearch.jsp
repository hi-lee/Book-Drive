<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>API 연습</title>
</head>

<body>
	<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
    <h1>ISBN을 검색해주세요.</h1>
    <input id="isbn" type="text">
    <ul id="result">
    	
    </ul>
    <button id="search">검색</button>
    <p></p>

    <script>
        $(function () {

            $("#search").click(function () {
				alert($("#isbn").val());
                $.ajax({
                    method: "GET",
                    url: "https://dapi.kakao.com/v3/search/book?target=isbn", // 전송 주소
                    data: { query: $("#isbn").val() }, // 보낼 데이터
                    headers: { Authorization: "KakaoAK 7c28f9da096eaa302f600c9900820d6e" }
                })
                    .done(function (msg) { // 응답이 오면 처리를 하는 코드
                    	alert('성공');
                   		$("#result").append('<li>'+msg.documents[0].title+'</li>');
                   		$("#result").append('<li>'+msg.documents[0].contents+'</li>');
                   		
                        console.log(msg);
                    });
            })
        });

    </script>
</body>

</html>