$(document).ready(function(){
	//사이즈 변수 선언(공통 사용)
	var winWidth = window.innerWidth || document.documentElement.clientWidth;//미디어쿼리 사이즈와 $(window).width()가 인식하는 px단위 사이즈가 달라서 선언한 변수 : clinentWidth 와 innerWidth의 사이즈는 동일하나 innerWidth는 익스플로러 9 미만 버전은 인식하지 못하므로 동시선언
	
	/* 모바일 사이즈 로고 변경 */
	var logoPath = $('.header .logo img').attr('src');
	if(logoPath != null)
	{
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
	}

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

	//placeholder
	$('.searchInput').placeholder({customClass:'placeholder'});//검색 placeholder 하위버전 지원 메소드($(선택자).placeholder({customClass:'클래스명'});)

	/* 탭메뉴 */
	if($('#divTabMenu').length > 0){
		if(winWidth < 1024){
			$("#divTabMenu").mThumbnailScroller({
				type:"click-50",
				theme:"buttons-out"
			});
			$("#divTabMenu").mThumbnailScroller("scrollTo", $('#divTabMenu .selected'));
		}else if(winWidth > 1023){
			$("#divTabMenu").mThumbnailScroller("destroy");
		}
		
		$(window).resize(function(){
			winWidth = window.innerWidth || document.documentElement.clientWidth;
			if(winWidth < 1024){
				$("#divTabMenu").mThumbnailScroller({
					type:"click-50",
					theme:"buttons-out"
				});
				$("#divTabMenu").mThumbnailScroller("scrollTo", $('#divTabMenu .selected'));
			}else if(winWidth > 1023){
				$("#divTabMenu").mThumbnailScroller("destroy");
			}
		});
	}

	/*퀵메뉴*/
	var $win = $(window);
	var top = $(window).scrollTop();
	var speed          = 1000;
	var easing         = 'swing';
	var $layer         = $('#divQuick');
	var layerTopOffset = 0;
	if (top > 0 )
		$win.scrollTop(layerTopOffset+top);
	else
		$win.scrollTop(0);
	$(window).scroll(function(){
		yPosition = $win.scrollTop()  - 130;
		if (yPosition < 194)
		{
			yPosition = 194;
		}
		$layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
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