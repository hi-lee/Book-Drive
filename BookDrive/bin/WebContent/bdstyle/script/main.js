$(document).ready(function(){
	//사이즈 변수 선언(공통 사용)
	var winWidth = window.innerWidth || document.documentElement.clientWidth;//미디어쿼리 사이즈와 $(window).width()가 인식하는 px단위 사이즈가 달라서 선언한 변수 : clinentWidth 와 innerWidth의 사이즈는 동일하나 innerWidth는 익스플로러 9 미만 버전은 인식하지 못하므로 동시선언
	
	/* 상단 공지 */
	if($('.information2 ul li').length == 2){//공지사항 2개인 경우
		$('.information2 ul li').css('width','48%');//공지사항 li width값 48%
	}else if($('.information2 ul li').length == 1){//공지사항 1개인 경우
		$('.information2 ul li').css({'width':'100%'});//공지사항 li width값 100%
	}else if($('.information2 ul li').length == 0){//공지사항 없을 경우
		$('.information2').remove();//information2 div 삭제
	}
	if($(window).width() < 1025){
		if($('.information2').length == 0){//태블릿, 모바일에서(width 1024px 이하) 문서 로드 되었을 시 공지사항이 없으면 
			$('.todayClose').hide();//닫기 버튼 숨김
		}
	}
	$(window).resize(function(){
		if($(window).width() < 1025){
			if($('.information2').length == 0){//태블릿 모바일 사이즈로 리사이징 되었을 시 information2 div 없으면 
				$('.todayClose').hide();//닫기 버튼 숨김
			}
		}
		if($(window).width() > 1024){
			if($('.information1').length > 0){//PC 사이즈로 리사이징 되었을 시 information2 div 존재하면
				$('.todayClose').show();//닫기 버튼 보여짐
			}
		}
	});
	
	$('.todayClose .close a').click(function(){//닫기 버튼 클릭
		$('.information').hide();
		return false;
	});

	/* 모바일 사이즈 로고 변경 */
	var logoPath = $('.header .logo img').attr('src');
	if(winWidth < 768){
		$('.header .logo img').attr('src',logoPath.replace('logo','logoM'));
	}
	$(window).resize(function(){
		winWidth = window.innerWidth || document.documentElement.clientWidth;
		if(winWidth < 768){
			$('.header .logo img').attr('src',logoPath.replace('logo','logoM'));
		}else{
			$('.header .logo img').attr('src',logoPath.replace('logoM','logo'));
		}
	});
	
	/* last 클래스 */
	$('#divTopMenu > ul > li div ul').each(function(i,element){
		$(this).children().last().addClass('last');
	});	
	$('.wholeMenu > ul').each(function(i,element){
		$(this).children().last().addClass('last');
	});	

	/* 내메뉴 */
	$('.myMenuBtn').click(function(){
		if($('#divGlobalMenu .myMenu').css('display') == 'block'){
			$('#divGlobalMenu .myMenu').hide();
		}else{
			myPageSummary();
			$('#divGlobalMenu .myMenu').show();
		}		
		return false;
	});
	
	$('.myMenu .close').bind('click', function(){
		$('#divGlobalMenu .myMenu').hide();
		return false;
	});


	/* 주메뉴 */
	var menuA = $('#divTopMenu > ul > li > a');
	var menuLi = $('#divTopMenu > ul > li');
	menuA.bind('mouseover focus',function(){
		menuLi.removeClass('on');
		$(this).parent().addClass('on');
		menuLi.last().children('div').children().children('.last').children().focusout(function(){
			menuLi.removeClass('on')
		});
		menuLi.mouseleave(function(){
			$(this).removeClass('on')
		});
	});

	/* 전체메뉴*/
	$('.wholeMenuOpen').bind('click', function(){
		if($('.wholeMenu').css('display') == 'block'){
			$('.wholeMenu').removeClass('on');
			$('.wholeMenuOpen').removeClass('on');
		}else{
			$('.wholeMenu').addClass('on');
			$('.wholeMenuOpen').addClass('on');
		}
		$('.wholeMenuClose').bind('click', function(){
			$('.wholeMenu').removeClass('on');
			return false;
		});
		return false;
	});
	//tablet mobile
	$('.wholeMenu > ul > li h3').click(function(){
		winWidth = window.innerWidth || document.documentElement.clientWidth;
		if(winWidth < 1025){
			if($(this).next().css('display') == 'none'){
				$('.wholeMenu > ul > li').removeClass('on');
				$(this).parent().addClass('on');
			}else{
				$(this).parent().removeClass('on');
			}
		}
	});

	/* 검색탭 */
	$('#divSearch > ul li h2 a').bind('click focus', function(){
		$('#divSearch > ul li').removeClass('on');
		$(this).parent().parent().addClass('on');
		return false;
	});
	//태블릿, PC
	$(window).resize(function(){
		winWidth = window.innerWidth || document.documentElement.clientWidth;
		if(winWidth < 1025){
			$('#divSearch > ul li').removeClass('on');
			$('#divSearch > ul li').first().addClass('on');
			return false;
		}
	});
	//placeholder
	$('.searchInput').placeholder({customClass:'placeholder'});//검색 placeholder 하위버전 지원 메소드($(선택자).placeholder({customClass:'클래스명'});)

	/* 팝업존 */
	var naviImg = $('.popup ul li a.navi img').attr('src');
	$('.popup ul li a.navi').bind('mouseover focus', function(){
		$('.popup ul li a.navi img').attr('src',naviImg.replace('On','Off'));
		$(this).children().attr('src',naviImg.replace('Off','On'));
		$('.popup ul li').removeClass('on');
		$(this).parent().addClass('on');
	});
	//자동 롤링
	popup();
	function popup(){
		var popupCnt = $('.popup ul li').length;
		var i = $('.popup ul li.on').index();
		var autoTime = 3000;
		var auto = setInterval(function(){
			if(popupCnt > 1){
				i++;
				if(i == popupCnt){
					i=0;
				}
				$('.popup ul li').removeClass('on');
				$('.popup ul li').eq(i).addClass('on');
				$('.popup ul li a.navi img').attr('src',naviImg.replace('On','Off'));
				$('.popup ul li.on a.navi img').attr('src',naviImg.replace('Off','On'));
			}
		},autoTime);
		$('.popup').mouseover(function(){
			clearInterval(auto);
		});
		$('.popup a').focus(function(){
			clearInterval(auto);
		});
	}
	$('.popup').mouseleave(function(){
		popup();
	});
	$('.popup a').focusout(function(){
		popup();
	});
	// navi 버튼 위치
	$(".popup ul li").each(function(i,element){
		var listCnt = $('.popup ul li').length;
		var liIndex = $(this).index();
		$(this).children().first().css("right", ((listCnt - (liIndex)) * 18));
	});		

	/* 이용시간 롤링 */
	var hourHeight = $('.openHour').height();
	$('.rolling ul li').css('height', hourHeight);
	openHour();
	function openHour(){
		var rollingTime = 4000;
		var aniTime = 1000;
		var rollingUl = $('.rolling ul');
		var rolling = setInterval(function(){
			if(rollingUl.children().length > 1){
				$(rollingUl).animate({top:-hourHeight},aniTime,function(){
					rollingUl.children().first().appendTo(rollingUl);
					rollingUl.css('top','0');
				});				
			}
		},rollingTime);
		$('.openHour').mouseover(function(){
			clearInterval(rolling);
		});
	}
	$('.openHour').mouseleave(function(){
		openHour();
	});

	/* 도서 탭 */
	$('.newArrival > ul > li .title').bind('click focus',function(){
		$('.newArrival > ul > li').removeClass('on');
		$(this).parent().addClass('on');
		return false;
	});

	/* 게시판 탭 */
	$('.notice div > ul > li > a').bind('click focus',function(){
		$('.notice div > ul > li').removeClass('on');
		$(this).parent().addClass('on');
		return false;
	});

	/* 관련사이트 */
	$('.relatedSite > a').click(function(){
		if($('.relatedSite ul').css('display') == 'none'){
			$(this).next().show();
		}else{
			$(this).next().hide();
		}
		return false;
	});

	$('.todayClose .close a').click(function(){//닫기 버튼 클릭
		if($("#todayClose").attr("checked") == 'checked')
		{
			setCookie('TODAY_POPUP','NONE',1);
		}
		$('.information').hide();
		return false;
	});
	
	$('#todayClose').click(function(){//닫기 버튼 클릭
		if($("#todayClose").attr("checked") == 'checked')
		{
			setCookie('TODAY_POPUP','NONE',1);
		}
		$('.information').hide();
		return false;
	});
	
	if(getCookie("TODAY_POPUP") != null) {
		$('.information').hide();
	}
});

function myPageSummary(){
	$('.myMenuArea .myMenu').show('slide', function(){
		$.ajax({
            type : 'GET',
            url : '/mypage/ajax/summary',
            dataType : 'json',
            success:function(result){
            	
            	$('#overdueCount').html(result.overdueCnt);
            	$('#returnCharge').html(result.returnCharge);
            	
            	if(!jQuery.isEmptyObject(result.notReserveDate) && !jQuery.isEmptyObject(result.notReserveDate.not_reserve_sdate) && !jQuery.isEmptyObject(result.notReserveDate.not_reserve_ldate)) {
            		$('#reserveDate').html("예약제재기간 : " + dateFormat(result.notReserveDate.not_reserve_sdate, '-') + "~" + dateFormat(result.notReserveDate.not_reserve_ldate, '-'));
            	}
            	
            	$('#loanCount').html(result.loanCount);
            	$('#reserveCount').html(result.reserveCount);
            	$('#purchaseProcessCount').html(result.purchaseRequestStat.stat1);
            	$('#purchaseArrangeCount').html(result.purchaseRequestStat.stat2);
            	$('#studyroomCount').html(result.studyRoomCount);
            },
            error:function(result){}
       });
	});
}

function callThumbnail(target, isbn, sysdiv, ctrl) {
	$.ajax({
		type: "POST",
		url: "/openapi/thumbnail",
		dataType: "json",
		data: "isbn="+isbn+"&sysdiv="+sysdiv+"&ctrl="+ctrl,
		success: function(msg) {
			if (msg != null) {
				var images = msg.smallUrl;			
				if(images != undefined && images != '' && images != null) {				
					$(target).attr({"src":images, alt:"책표지"});
				} else {
					$(target).attr({"src":imgPath+"local/main/noImg.jpg", alt:"책표지"});	
				}
			}
			else {
				$(target).attr({"src":imgPath+"local/main/noImg.jpg", alt:"책표지"});	
			}
		},
		error: function() {
			$(target).attr({"src":imgPath+"local/main/noImg.jpg", alt:"책표지"});			
		}
	});
}