$(document).ready(function(){
	//선택조건으로 조회
	$('.limitSearchBtn a').click(function(){
		if($(this).parent().next().css('display') == 'none'){
			$(this).parent().next().show();
			$(this).parent().addClass('on');
		}else{
			$(this).parent().next().hide();
			$(this).parent().removeClass('on');
		}
		return false;
	});
	$(window).resize(function(){
		if($(window).width() > 767){
			$('.searchArea form').show();
		}
	});
	
	//보내기
	$('.fileSend > a').click(function(){
		$(this).next().toggle();
		return false;
	});
	
	//table
	if($(".mobileTable").length > 0){
		$('.mobileTable').footable({
			breakpoints :{
				phone:767,
				tablet:1024
			}
		});
	}
});