//소장처 변경 시 자료실 셀렉트박스 동적으로 변경.
function onChangeHolding(obj, id) {
	var index = obj.selectedIndex;
	var sub_locationList = document.getElementById("lmt"+(id+1));
	var size = sub_locationList.length;
	
	for(var i=0;i<size; i++)
	{
		sub_locationList.options[0] = null;
	}
	$(arr_holdingInfo[index]).appendTo("#"+sub_locationList.id);
	
}


//checkbox에서 전체와 그외것 선택시의 반전관계  
//frm: 검색 form 객체
function toggleCheckbox(checkedBox, frm) {
	var checkboxes = eval("document." + frm.name + "." + checkedBox.name);
	// 체크박스가 하나밖에 없을 때
	if (!checkboxes.length) return;

	if (checkedBox.value == ALL_SELECTED) {//전체 선택
		for (var i = 1; i < checkboxes.length ; i++) {
			if (checkboxes[0].checked == true)	//전체가 true
				checkboxes[i].checked = false;
		}
	}
	else { 
		var checkedNo = 0;
		var checkboxesNo = checkboxes.length;
		for (var i=1; i < checkboxesNo; i++) {
			if (checkboxes[i].checked == true)
				checkedNo++;
		}
		// 아무것도 선택하지 않으면 전체 선택
		if (checkedNo == 0) {
			checkboxes[0].checked = true;
		}
		// 모두 선택하면 선택 해제하고 전체 선택	
		else if (checkedNo == checkboxesNo-1 && checkboxes[0].checked == false) {
			for (i=1; i < checkboxesNo; i++) {
				checkboxes[i].checked = false;
			}
			checkboxes[0].checked = true;
		}
		//전체 이외에 하나라도 선택되면 전체를 해제
		else {
			checkboxes[0].checked = false;
		}
	}
}

$(document).ready(function() {
    $('input, textarea').placeholder({customClass:'placeholder'});
	
	if ($(window).width() < 768) $(".searchLimitSelect dd").hide();
	
	$(".searchLimitSelect dt").bind("click", function() {
		if ($(window).width() > 766) return; 
		$(this).toggleClass("on");
		$(this).next().slideToggle();
	});
	
	$(".advancedSearch .searchKeyword").eq(0).find(".inputSearchKeyword").focus();

});

$(window).resize(function() {
	if ($(window).width() < 768) {
		$(".searchLimitSelect dd").hide();
		$(".searchLimitSelect dt.on").next().show();

		$("#etcCharacterLayer").hide();
		$(".advancedSearch .btnLanguage").removeClass("selected");
	} else {
		$(".searchLimitSelect dd").show();
	}
	$(".searchOpt1").selectBox('destroy').selectBox();
});


