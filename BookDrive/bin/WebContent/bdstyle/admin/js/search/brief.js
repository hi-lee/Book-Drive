var DEF_ORDER_ITEM_CHECKIN = "PUBLISHER_DATE";
var DEF_ORDER_ITEM_BINDING = "ACCESSION_NO";
var DEF_ORDER_SORT = "DESC";
//권호정보 설정 항목(테이블 헤더, td width, sort_item
var TH_ITEMS_C = new Array(numberMsg,checkinMsg,pubdateMsg,indateMsg,bookstateMsg ,articleMsg,appendixMsg);
var TH_RESPONSIVE_C = new Array(
		""
		,""
		,"data-hide=\"phone\""
		,"data-hide=\"phone\""
		,"data-class=\"expand\""
		,"data-hide=\"phone\""
		,"data-hide=\"phone\""
);
var TH_SORT_ITEMS_C = new Array("","VOLUME_NAME_DISP","PUBLISHER_DATE","IN_DATE","CHECKIN_STATE","","");
//제본정보 설정 항목(테이블 헤더, td width, sort_item	
var TH_ITEMS_B = new Array(numberMsg,accessionnoMsg,bookShelfMsg,callNoMsg,checkinnameMsg,bookstateMsg,serviceMsg,remarkMsg);
var TH_RESPONSIVE_B = new Array(
		""
		,"data-hide=\"phone\""
		,"data-hide=\"phone\""
		,"data-hide=\"phone\""
		,"data-hide=\"phone\""
		,""
		,"data-class=\"expand\""
		,"data-hide=\"phone\""
);
var TH_SORT_ITEMS_B = new Array("","ACCESSION_NO", "BOOKSHELF", "PLACE_NO||CALL_NO","VOLUME_NO","MATERIAL_STATUS||LOAN_FLAG||CATALOG_FLAG","");

var IS_SER_RUNNING = false;

function previewDetail(id) {
	var obj = document.getElementById("prevDetail_"+id);
	var sysdCtrl = id.substring(0,3)+id.substring(6);
	var ctrl = id.substring(6);
	
	if(obj.style.display=="block") {
		obj.style.display="none";
		$("#item_"+id + " .info").css("display","block");
		return;
	}
	else {
		if ($("#prevDetail_"+id + " ul").length > 0) {
			$("#prevDetail_"+id).css("display","block");
			$("#item_"+id + " .info").css("display","none");
		}
		else {
			
			if(id.substring(0,3) == 'ERS')
			{
				$.ajax({
					url : "/search/ers/ersResource",
					data: "subsPackId="+ ctrl,
					dataType: 'json',
					success: function(data) {

						if(data != null){
							viewPreviewDetailErs(data, id);
						}
					},
					error : function(result) {}
				});
			}
			else
			{
				$.ajax({
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					type: "POST",
					url: "/search/prevDetail/"+id,
					dataType: "xml",
					complete: function(xhr, statusText){
						if(statusText=="success") {
							viewPreviewDetail(xhr, id);
						}
					}
				});
			}
		}
	}
}

function previewDetailGallery(id) {
	var obj = document.getElementById("prevDetail_"+id);
	var sysdCtrl = id.substring(0,3)+id.substring(6);
	var ctrl = id.substring(6);
	
	if ($("#prevDetail_"+id + " ul").length > 0) {
		$("#prevDetail_"+id).css("display","block");
	}
	else {
		if(id.substring(0,3) == 'ERS')
		{
			$.ajax({
				url : "/search/ers/ersResource",
				data: "subsPackId="+ ctrl,
				dataType: 'json',
				success: function(data) {

					if(data != null){
						viewPreviewDetailErs(data, id);
					}
				},
				error : function(result) {}
			});
		}
		else
		{

			$.ajax({
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				type: "POST",
				url: "/search/prevDetail/"+id,
				dataType: "xml",
				complete: function(xhr, statusText){
					if(statusText=="success") {
						viewPreviewDetail(xhr, id);
					}
				}
			});
		}
	}
}

function viewPreviewDetail(xhr, id) {
	var xmlObj = xhr.responseXML;
	var profiles = $("profile", xmlObj);
	
	if(profiles != null) {
		var profile;
		var p_size = profiles.length;
		var strProfile = "";
		var sysdiv = id.substring(0,3);
		var ctrl = id.substring(6);
		var name = "";
		var value = "";

		var thumnailStr = "";
		
		//목록 미리보기.
		if(sysdiv=="CAT" || sysdiv=="ACQ" || sysdiv=="ART" || sysdiv=="ERM" || sysdiv=="COL" || sysdiv == "BRV"){
			for(var i=0; i<p_size; i++){
				profile = profiles[i];
				
				name="";
				value="";

				if($("name", profile)[0] != undefined){
					name = $("name", profile)[0].firstChild.nodeValue;
				}
				if($("value", profile)[0] != undefined){
					value = $("value", profile)[0].firstChild.nodeValue;
				}
				
				strProfile += "<li><dl><dt>"+name+"</dt><dd>"+value+"</dl></li>";
			}
		}else{//DL 미리보기.
			var level;
			var space;
			
			for(var i=0; i<p_size; i++){
				profile = profiles[i];
				
				level = $("level", profile)[0].firstChild.nodeValue;
				space = "";
				name="";
				value="";

				if($("name", profile)[0] != undefined){
					name = $("name", profile)[0].firstChild.nodeValue;
				}
				if($("value", profile)[0] != undefined){
					value = $("value", profile)[0].firstChild.nodeValue;
				}
				
				if (level == "0") {
					strProfile += "<li><dl><dt>"+name+"</dt></dl></li>";
				}
				else {
					strProfile += "<li><dl><dt>"+name+"</dt><dd>"+value+"</dl></li>";
				}
			}
		}
		
		$("<ul>"+strProfile+"</ul>").appendTo("#prevDetail_"+id);
		$("#prevDetail_"+id).css("display","block");
		$("#item_"+id + " .info").css("display","none");

		/*
		if (sysdiv == "ERM") {
			viewResource(xhr, id);
		}
		*/
	}
}

function viewPreviewDetailErs(data, id) {
	
	$("#prevDetail_"+id).empty();
	var resources = data.resources;
	if(resources != ""){
		
		strProfile = "<div class=\"listTable\"><table class=\"mobileTable\" summary=\"\" border=\"1\" cellspacing=\"0\">";
		strProfile += "<thead>";
		strProfile += "<tr>";
		strProfile += "<th scope=\"row\" class=\"num\">No. </th>";
		strProfile += "<th scope=\"row\" data-class=\"expand\">"+dbNameMsg+"</th>";
		strProfile += "<th scope=\"row\" data-hide=\"phone\">"+offerPeriodMsg+"</th>";
		strProfile += "<th scope=\"row\" data-hide=\"phone\">"+delayPeriodMsg+"</th>";
		strProfile += "<th scope=\"row\" data-hide=\"phone\">"+mediaMsg+"</th>";
		strProfile += "</tr>";
		strProfile += "</thead>";
		strProfile += "<tbody>";
		
		for(var i =0 ; i<resources.length; i++){
			var cb = "";
			var ce = "";
			var em = "";
			var url = "";
			if(resources[i].coverageBegin != null) cb = resources[i].coverageBegin;
			if(resources[i].coverageEnd != null) ce = resources[i].coverageEnd;
			if(resources[i].embago != null) em = resources[i].embago;
			
			var urlList = resources[i].urlList;
			
			if(urlList != null){
				if(urlList.length >= 1){
					url = urlList[0];
				}
			}
			
			strProfile += "<tr>";
			strProfile += "<td class=\"num\">"+ (i+1) +"</td>";
			strProfile += "<td class=\"location\">"+resources[i].packageName+"</td>";
			strProfile += "<td class=\"location\">"+cb+" ~ "+ce+"</td>";
			strProfile += "<td class=\"callNum\">"+em+"</td>";
			if(url != null)
			{
				strProfile += "<td class=\"accessionNo\"><a href=\"/outlink?moduleId=search&targetUrl="+url+"\" target=\"_blank\" class=\"btnType2\">URL</a></td>";
			}
			else
			{
				strProfile += "<td class=\"accessionNo\"></td>";
			}
			strProfile += "</tr>";
			
		}
		
		strProfile += "</tbody>";
		strProfile += "</table>";
		strProfile += "</div>";
	}
	else 
	{
		
	}
	
	$(strProfile).appendTo("#prevDetail_"+id);
	$("#prevDetail_"+id).css("display","block");
	$("#item_"+id + " .info").css("display","none");
}


/**
 * 썸네일 호출
 * @param id
 * @param isbn
 * @return
 */
function callThumbnail(id, isbn, sysdiv, ctrl) {
	if(isbn == null || isbn == ''){ return; }
	//ajaxLoader("#"+id, true, "small");
	$.ajax({
		type: "POST",
		url: "/openapi/thumbnail",
		dataType: "json",
		data: "isbn="+isbn+"&sysdiv="+sysdiv+"&ctrl="+ctrl,
		success: function(msg) {
			//썸네일 생성.
			//ajaxLoader("#"+id, false);
			viewThumnail(msg, id);
		},
		error: function() {
			//ajaxLoader("#"+id, false);
			//결과를 찾지 못해도 error로 떨어짐.
			//viewThumnailNoImage(id);
		}
	});
}

function viewThumnail(json, id) {
	if(json !== undefined && json != null && json != "") {
		var images = json.smallUrl;
		$("#"+id).attr("src",images);
	} else {
		//viewThumnailNoImage(id);
	}
}

function previewHolding(locOrder, locCnt, sysdCtrl, location) {
	var id = sysdCtrl + "_" + locOrder;
	
	if($("#holdingW_" + id).css("display") == "block") {
		$("#holdingW_" + id).hide();
		$("#availableButton_"+id).removeClass("on");
	}
	else {
		if ($("#holdingW_"+id + " div").length > 0) {
			$("#holdingW_" + id).show();
			$("#availableButton_"+id).addClass("on");
		}
		else {
			$.ajax({
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				type: "POST",
				url: "/search/prevLoc/"+sysdCtrl,
				dataType: "xml",
				data: "loc="+location,
				complete: function(xhr, statusText){
					if(statusText=="success") {
						viewPreviewHolding(xhr, sysdCtrl,locOrder);
					}
				}
			});
		}
	}
}

function viewPreviewHolding(xhr, sysdCtrl, locOrder) {
	
	var id = sysdCtrl+"_"+locOrder;
	var sysdiv = sysdCtrl.substring(0,3);
	var xmlObj = xhr.responseXML;
	
	//2011.06.10 박미영 추가. 소장처지도 사용여부 옵션 처리(소장처 링크 클릭하면 소장처 이미지 팝업)
	var isUseLocationMap = $("isUseLocationMap", xmlObj)[0].firstChild.nodeValue;
	var isUseLocationState = $("isUseLocationState", xmlObj)[0].firstChild.nodeValue;
	var isUseMypreserve = $("isUseMypreserve", xmlObj)[0].firstChild.nodeValue;
	var holding = $("holding", xmlObj);
	var noholding = $("noholding", xmlObj);
	var serholding = $("serholding", xmlObj);
	var orgMarcType = $("orgMarcType", xmlObj)[0].firstChild.nodeValue;
	
	var strLoc = "";

	var checkinViewType = "radio";
	if (typeof (CONST_CHECKINVIEW_TYPE) != 'undefined') {
		checkinViewType = CONST_CHECKINVIEW_TYPE;
	}
	
	if(holding[0] != undefined) {
		var items = $("item", holding);
		var item;
		var size = items.length;
		strLoc = "<div class=\"listTable\"><table class=\"mobileTable\" summary=\"\" border=\"1\" cellspacing=\"0\">";
		strLoc += "<thead>";
		strLoc += "<th scope=\"row\" class=\"num\">No. </th>";
		strLoc += "<th scope=\"row\" data-class=\"expand\">"+shelfMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+callNoMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+shelfinfoMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+loaninfoMsg+"</th>";
		strLoc += "</thead>";
		strLoc += "<tbody>";
		for(var i=0; i<size; i++) {
			item = items[i];
			strLoc += "<tr>";
			strLoc += "<td class=\"num\">"+(i+1)+"</td>";
			strLoc += "<td class=\"place\">";
			//2011.06.10 박미영 추가. 소장처지도 사용여부 옵션 처리(소장처 링크 클릭하면 소장처 이미지 팝업)
			if(isUseLocationMap == "true") {
				strLoc += "<a href=\"/search/service/locationMap?loc="+$("location", item)[0].firstChild.nodeValue+"&amp;sub_loc="+$("sub_location", item)[0].firstChild.nodeValue+"&amp;bookshelf="+$("bookshelf", item)[0].firstChild.nodeValue+"\" target=\"locationMap\"" +
				" onclick=\"return locationMap(this)\" onkeypress=\"return locationMap(this)\">";
				strLoc += $("shelf", item)[0].firstChild.nodeValue;
				strLoc += "</a>";
			} else if(isUseLocationState =='true') {
				if($("book_state", item)[0].firstChild.nodeValue=='정리중'){
					//공백
				}else{
					strLoc += $("shelf", item)[0].firstChild.nodeValue;
				}
			} else{
				strLoc += $("shelf", item)[0].firstChild.nodeValue;
			}
			strLoc += "</td>";
			strLoc += "<td class=\"mark\">"+$("callno", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td class=\"record\">"+$("shelfinfo", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td class=\"expect\">"+$("loaninfo", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "</tr>";
		}
		strLoc += "</tbody>";
	}
	else if(noholding[0] != undefined) {
		var items = $("item", noholding);
		var item;
		var size = items.length;
		var isUseCampusLoan = $("isUseCampusLoan", xmlObj)[0].firstChild.nodeValue;
		var isUsereserve = $("isUsereserve", xmlObj)[0].firstChild.nodeValue;
		var isReserveLimtItem0002 = $("isReserveLimtItem0002", xmlObj)[0].firstChild.nodeValue;
		var campusLocation = "";
		var siteLocation = "";
		var isUseMissrepo = $("isUseMissrepo", xmlObj)[0].firstChild.nodeValue;
		var UseAppNonbook = $("UseAppNonbook", xmlObj)[0].firstChild.nodeValue;
		var isUseLoanreq = $("isUseLoanreq", xmlObj)[0].firstChild.nodeValue;
		var isUseDelivery = $("isUseDelivery", xmlObj)[0].firstChild.nodeValue;
		var isUsePosPrint = $("isUsePosPrint", xmlObj)[0].firstChild.nodeValue;
		var isSendSms = $("isSendSms", xmlObj)[0].firstChild.nodeValue;
		var location = $("location", xmlObj)[0].firstChild.nodeValue;

		if (USE_SERVICE) {
			if(IS_USE_OPAC_SEARCH == "false" || sysdiv != "COL") {
				strLoc += "<div class=\"service\">";
				strLoc +="<dl>";
				strLoc +="<dt onclick=\"javascript:toggleServiceHelp('serviceHelp_"+id+"');\">"+infoServiceMsg+"</dt>";
				strLoc +="<dd id=\"serviceHelp_"+id+"\" style=\"display:none;\">";
				strLoc +="<ul>";
				if(isUseMypreserve == "true") {
					strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/mypreserve.gif\" alt=\"" + preserveMsg + "\" title=\"" + preserveMsg + "\"></span>"+preserveMsg+"</li>";
				}
				if(isUseCampusLoan == "true") {
					campusLocation = $("campusLocation", xmlObj)[0].firstChild.nodeValue;
					siteLocation = $("siteLocation", xmlObj)[0].firstChild.nodeValue;
					strLoc += "<li><span class=\"serviceIcon\"><img src=\""+imagePath+"solution/common/ico/branchLoan.gif\" width=\"16\" height=\"16\" alt=\"" + branchLoanMsg + "\" title=\"" + branchLoanMsg + "\"></span>" + branchLoanMsg + "</li>";
				}
				if(isUseMissrepo == "true") {
					strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/missrepo.gif\" alt=\"" + missBookMsg + "\" title=\"" + missBookMsg + "\"></span>"+missBookMsg+"</li>";
				}
				if(isUseLoanreq == "true") {
					strLoc += "<li><span class=\"serviceIcon\"><img src=\""+imagePath+"solution/common/ico/loanreq.gif\" width=\"16\" height=\"16\" alt=\"" + loanreqMsg + "\" title=\"" + loanreqMsg + "\"></span>" + loanreqMsg + "</span>";
				}
				if(isUseDelivery == "true" && orgMarcType == "m") {
					strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/delivery.gif\" alt=\"" + deliveryMsg + "\" title=\"" + deliveryMsg + "\"></span>"+deliveryMsg+"</li>";
				}
				//strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/serviceInfo04.png\" alt=\"장애인지원신청\"></span>장애인지원신청</li>";
				if(isUsePosPrint == "true") {
					strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/print.gif\" alt=\"" + printMsg + "\" title=\"" + printMsg + "\"></span>"+printMsg+"</li>";
				}
				if(isSendSms == "true") {
					strLoc +="<li><span class=\"serviceIco\"><img src=\""+imagePath+"solution/common/ico/mobile.gif\" alt=\"" + smsMsg + "\" title=\"" + smsMsg + "\"></span>"+smsMsg+"</li>";
				}
				strLoc +="</ul>";
				strLoc +="</dd>";
				strLoc +="</dl>";
				strLoc +="</div>";
			} 
			if(IS_USE_OPAC_SEARCH == "true" && sysdiv == "COL") {
				//strLoc += "<span class=\"serviceIcon\"><img src=\""+imagePath+"solution/common/ico/branchLoan.gif\" width=\"16\" height=\"16\" alt=\"" + branchLoanExt + "\" title=\"" + branchLoanExt + "\">&nbsp;" + branchLoanExt + "</span>";
			}
		}
		
		strLoc += "<div class=\"listTable\"><table class=\"mobileTable\" summary=\"\" border=\"1\" cellspacing=\"0\"><thead>";
		strLoc += "<th scope=\"row\" class=\"num\">No. </th>";
		strLoc += "<th scope=\"row\">"+shelfMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+callNoMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+accessionnoMsg+"</th>";
		if(isDenseNoDisplay == "true") {
			strLoc += "<th scope=\"row\" data-hide=\"phone\">"+densenoMsg+"</th>";
		}
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+bookstateMsg+"</th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+returndateMsg+" </th>";
		
		// 서비스 사용
		if (USE_SERVICE) {
			if(isUsereserve == "Y"){
				strLoc += "<th scope=\"row\">"+reservationMsg+" </th>";
			}
			if(isUseMypreserve == "true" || isUseCampusLoan == "true" || isUseMissrepo == "true" || isUseLoanreq == "true" || isUseDelivery == "true" || isUsePosPrint == "true" || isSendSms == "true" || UseAppNonbook == "true") {
				strLoc += "<th scope=\"row\" data-hide=\"phone\">"+ serviceMsg +"</th>";
			}
		}
		// holding 선택
		if (typeof SELECT_HOLDING_MODULE != undefined && SELECT_HOLDING_MODULE != '') { // 소장자료 선택인 경우 사용하지 않음
			strLoc += "<th scope=\"row\" data-hide=\"phone\">"+ selectMsg + "</th>";
		}

		//strLoc += "<th scope=\"row\">"+metsMsg+"</th>";
		strLoc += "</thead>";
		strLoc += "<tbody>";
		
		var beforeMainNo = "";
		var isEqualBeforeMainNo = false;
		
		for(var i=0; i<size; i++) {
			item = items[i];
			
			/* main no 별 출력 */
			var mainNo = $("main_no", item)[0].firstChild.nodeValue;
		
			if(mainNo != beforeMainNo) {
				isEqualBeforeMainNo=false;
			}
			else isEqualBeforeMainNo=true;
			beforeMainNo = mainNo;

			var book_state_code = $("book_state_code", item)[0].firstChild.nodeValue;
			var closedaccess_check = $("closedaccess_check", item)[0].firstChild.nodeValue;
			var loan_flag = $("loan_flag", item)[0].firstChild.nodeValue;
			var catalog_flag = $("catalog_flag", item)[0].firstChild.nodeValue;
			var material_status = $("material_status", item)[0].firstChild.nodeValue;
			var book_state_css = "disabled";
			if (book_state_code == '0001') {
				book_state_css = "available";
			}
			if (book_state_code == '0002') {
				book_state_css = "ing";
			}

			strLoc += "<tr"+(isEqualBeforeMainNo?"":" class=\"first\"")+">";
			strLoc += "<td class=\"num\">"+(i+1)+"</td>";
			strLoc += "<td class=\"location\">";
			if(isUseLocationMap == "true") {
				strLoc += "<a href=\"/search/service/locationMap?loc="+$("location", item)[0].firstChild.nodeValue+"&amp;sub_loc="+$("sub_location", item)[0].firstChild.nodeValue+"&amp;bookshelf="+$("bookshelf", item)[0].firstChild.nodeValue+"\" target=\"locationMap\"" +
						" onclick=\"return locationMap(this)\" onkeypress=\"return locationMap(this)\">";
				strLoc += $("place_name", item)[0].firstChild.nodeValue;
				strLoc += "</a>";
			} else if(isUseLocationState =='true') {
				if($("book_state", item)[0].firstChild.nodeValue=='정리중'){
				}else{
					strLoc += $("place_name", item)[0].firstChild.nodeValue;
				}
			} else{
				strLoc += $("place_name", item)[0].firstChild.nodeValue;
			}
			strLoc += "</td>";
			strLoc += "<td class=\"callNum\">"+$("call_no", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td class=\"accessionNo\">"+$("print_accessionno", item)[0].firstChild.nodeValue+"</td>";
			if(isDenseNoDisplay == "true") {
				strLoc += "<td>"+$("dense_no", item)[0].firstChild.nodeValue+"</td>";
			}
			strLoc += "<td class=\"bookStatus\"><span class=\"status " + book_state_css + "\">"+$("book_state", item)[0].firstChild.nodeValue+"</span></td>";
			strLoc += "<td class=\"returnDate\">"+$("return_plan_date", item)[0].firstChild.nodeValue+"</td>";

			if (USE_SERVICE) {
				if(isUsereserve == "Y"){
					strLoc += "<td class=\"reservation\">";
					var reservation = $("reservation", item)[0].firstChild.nodeValue;
					// 정리중 예약을 희망도서 신청 대출예약 기능으로만 사용하고자 하는 경우 추가 (상세만 적용되어 있어서 추가 처리, 2016.05.25)
					if((reservation == '0002' && isReserveLimtItem0002 == 'false') || reservation == '0003') {
						if(!isEqualBeforeMainNo) {
							var canReserveCount = Number($("can_reserve_count", item)[0].firstChild.nodeValue);
							var reservationCount = Number($("reservation_count", item)[0].firstChild.nodeValue);
							if($("book_state", item)[0].firstChild.nodeValue !='대출불가(자료실)'){
								if(canReserveCount>reservationCount) {
									var location = $("location", item)[0].firstChild.nodeValue;
									strLoc += "<a href=\"/search/reserve/form?mainno="+mainNo+"&amp;location="+location+"\" target=\"reserve\" onclick=\"return doReserve(this)\" onkeypress=\"return doReserve(this)\" class=\"\">";
									if($("catalog_flag", item)[0].firstChild.nodeValue == '0002') {
										strLoc += "<span class=\"reservation available\">"+$("msg_priority_cleanUp", item)[0].firstChild.nodeValue;
									}else {
										strLoc += "<span class=\"reservation available\">"+$("msg_possible_reserve", item)[0].firstChild.nodeValue;
									}
									if(reservationCount != 0) {
										strLoc += "<br/>("+reservationCount+$("msg_people_reserved", item)[0].firstChild.nodeValue+")";
									}
									strLoc += "</span></a>";
								}
								else {
									strLoc += "<span class=\"reservation disabled\">" + $("msg_reserve_limit_exceeded", item)[0].firstChild.nodeValue + "</span>";
								}
							}
							isEqualBeforeMainNo = true;
						}
					}
					strLoc += "</td>";
				}
			
				strLoc += "<td><span class=\"service\">";
			
				if(IS_USE_OPAC_SEARCH == "false" || sysdiv != "COL") {
					if(isUseMypreserve == "true" && book_state_code == "0001" && closedaccess_check == "0001" && loan_flag == "0001" && catalog_flag == "0003" && material_status == "0003") {
						strLoc += "<a href=\"/mypreserve/pop/write?controlno="+$("controlno", item)[0].firstChild.nodeValue+"&amp;main_no="+$("main_no", item)[0].firstChild.nodeValue+"&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\" target=\"preserve\" onclick=\"return doPreserve(this)\"><img src=\""+imagePath+"solution/common/ico/mypreserve.gif\" alt=\""+preserveMsg+"\"  title=\""+preserveMsg+"\"></a>";
					}
					if(isUseCampusLoan=="true") {
						if($("branch_loan_check", item)[0].firstChild.nodeValue == "0001" && book_state_code == "0001") {
							if(campusLocation != "N") {
								if(campusLocation.indexOf($("location", item)[0].firstChild.nodeValue)>-1) {
									strLoc += "<a href=\"/search/branch/form?ctrl="+$("controlno", item)[0].firstChild.nodeValue+"&amp;accno="+$("accession_no", item)[0].firstChild.nodeValue+"&amp;location="+$("location", item)[0].firstChild.nodeValue+"&amp;site_location="+siteLocation+"\" target=\"branch\" onclick=\"return doBranch(this)\">";
									strLoc += "<img src=\""+imagePath+"solution/common/ico/branchLoan.gif\" width=\"16\" height=\"16\" alt=\""+branchLoanMsg+"\"  title=\""+branchLoanMsg+"\">";
									strLoc += "</a>";
								}
							}
							else {
								strLoc += "<a href=\"/search/branch/form?ctrl="+$("controlno", item)[0].firstChild.nodeValue+"&amp;accno="+$("accession_no", item)[0].firstChild.nodeValue+"&amp;location="+$("location", item)[0].firstChild.nodeValue+"&amp;site_location="+siteLocation+"\" target=\"branch\" onclick=\"return doBranch(this)\">";
								strLoc += "<img src=\""+imagePath+"solution/common/ico/branchLoan.gif\" width=\"16\" height=\"16\" alt=\""+branchLoanMsg+"\"  title=\""+branchLoanMsg+"\">";
								strLoc += "</a>";
							}
						}
					}
					if((isUseMissrepo == "true" && book_state_code == "0001" && !(closedaccess_check == "0001" && loan_flag == "0001" && catalog_flag == "0003" && material_status == "0003"))) {
						strLoc += "<a href=\"/missrepo/write?accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\"><img src=\""+imagePath+"solution/common/ico/missrepo.gif\" alt=\""+missBookMsg+"\"  title=\""+missBookMsg+"\"></a>";
					}
					if(isUseLoanreq == "true" && book_state_code == "0001") {
						strLoc += "<a href=\"/loanreq/reqform?controlno="+$("controlno", item)[0].firstChild.nodeValue+"&amp;main_no="+$("main_no", item)[0].firstChild.nodeValue+"&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\" onclick=\"return doLoanreq(this)\"><img src=\""+imagePath+"solution/common/ico/loanreq.gif\" width=\"16\" height=\"16\" alt=\""+loanreqMsg+"\"  title=\""+loanreqMsg+"\"></a>";
					}
					if(isUseDelivery  == "true" && book_state_code == "0001" && orgMarcType == "m") {
						if(isUseDelivery == "true" && orgMarcType == "m") {
							if(isUseLocCheck == 'Y'){
								if($("location", item)[0].firstChild.nodeValue == SITE_CODE){
									strLoc += "<a href=\"/delivery/write?accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\"><img src=\""+imagePath+"solution/common/ico/delivery.gif\" alt=\""+deliveryMsg+"\"  title=\""+deliveryMsg+"\"></a>";
								}
							}else{
								strLoc += "<a href=\"/delivery/write?accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\"><img src=\""+imagePath+"solution/common/ico/delivery.gif\" alt=\""+deliveryMsg+"\"  title=\""+deliveryMsg+"\"></a>";
							}
						}
						
					}
					if(isUsePosPrint == "true") {
						strLoc += "<a href=\"/search/handler/output?submit=PRINT&amp;brief=Y&amp;holding=Y&amp;posPrint=Y&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"&amp;data="+sysdiv+$("controlno", item)[0].firstChild.nodeValue+ "\" onclick=\"return doPosprint(this);\"><img src=\""+imagePath+"solution/common/ico/print.gif\" width=\"16\" height=\"16\" alt=\""+printMsg+"\"  title=\""+printMsg+"\"></a>";
					}
					if(isSendSms == "true") {
						strLoc += "<a href=\"#none\" target=\"mobile\" onclick=\"return sendsms('"+$("accession_no", item)[0].firstChild.nodeValue+"')\"><img src=\""+imagePath+"solution/common/ico/mobile.gif\" width=\"16\" height=\"16\" alt=\""+smsMsg+"\"  title=\""+smsMsg+"\"></a>";
					}
					if(UseAppNonbook == "true") {
						//if($("book_state_code", item)[0].firstChild.nodeValue == "0001") 
							strLoc += "<a href=\"/nonbook/write?controlno="+$("controlno", item)[0].firstChild.nodeValue+"&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\" target=\"nonbook\" ><img src=\""+imagePath+"solution/common/ico/nonbook.gif\" width=\"16\" height=\"16\" alt=\""+nonBookMsg+"\"  title=\""+nonBookMsg+"\"></a>";
					}
				}
				if(IS_USE_OPAC_SEARCH == "true" && sysdiv == "COL" && book_state_code == "0001") {
					strLoc += "<a href=\"/will/insertColLoan?orgsysdiv=CAT&amp;controlno="+$("controlno", item)[0].firstChild.nodeValue+"&amp;location="+$("location", item)[0].firstChild.nodeValue+"&amp;site_location="+$("siteLocation", xmlObj)[0].firstChild.nodeValue+"&amp;call_no="+encodeURIComponent($("call_no", item)[0].firstChild.nodeValue)+"\" ><img src=\""+imagePath+"solution/common/ico/branchLoan.gif\" width=\"16\" height=\"16\" alt=\""+branchLoanExt+"\"  title=\""+branchLoanExt+"\"></a>";
				}
				strLoc += "</span></td>";
			}
	
			// 현재는 서고부재도서만 적용됨 필요시 module별 기능 추가해야함
			if (SELECT_HOLDING_MODULE != undefined && SELECT_HOLDING_MODULE != '') { // 소장자료 선택
				if ('missrepo' == SELECT_HOLDING_MODULE) {
					if (book_state_code == '0001') {
						strLoc += "<td class=\"select_holding\"><a href=\"javascript:selectHolding('"+$("accession_no", item)[0].firstChild.nodeValue+"');\" class=\"btnType9\">"+selectBtn+"</a></td>";
					}
				}
				else {
					strLoc += "<td></td>";
				}
			}
			
			/*
			strLoc += "<td>";
			if($("img_yn", item)[0].firstChild.nodeValue == 'Y') {				
				strLoc += "<a href=\"/search/media/imglist/"+sysdCtrl+"?meta_type=CA&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\" target=\"IMG\" onclick=\"return openMediaList(this,'IMG');\"><img src=\""+imagePath+"solution/common/ico/type_a.gif\" width=\"16\" height=\"16\" alt=\""+originalMsg+"\"  title=\""+originalMsg+"\"></a>";
			}
			strLoc += "&nbsp;";
			if($("vod_yn", item)[0].firstChild.nodeValue == 'Y') {
				strLoc += "<a href=\"/search/media/vodlist/"+sysdCtrl+"?meta_type=CA&amp;accession_no="+$("accession_no", item)[0].firstChild.nodeValue+"\" target=\"VOD\" onclick=\"return openMediaList(this,'VOD');\"><img src=\""+imagePath+"solution/common/ico/type_a.gif\" width=\"16\" height=\"16\" alt=\""+vodMsg+"\"  title=\""+vodMsg+"\"></a>";				
			}
			strLoc += "</td>";
			*/
			strLoc += "</tr>";
		}
		strLoc += "</tbody>";
	}
	else if(serholding[0] != undefined) {
		var items = $("item", serholding);
		var item;
		var size = items.length;
		strLoc += "<div class=\"listTable\"><table class=\"mobileTable mobileTableDL\" summary=\"\" border=\"1\" cellspacing=\"0\">";
		strLoc += "<thead>";
		strLoc += "<th scope=\"row\" >No. </th>";
		strLoc += "<th scope=\"row\" class=\"minWidth55\" data-class=\"expand\">"+shelfMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+shelfinfoMsg+" </th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+callNoMsg+"</th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+subscriptionMsg+"</th>";
		strLoc += "<th scope=\"row\" data-hide=\"phone\">"+latestdateMsg+"</th>";
		strLoc += "<th scope=\"row\" class=\"minWidth90\" data-hide=\"phone\">"+checkBindingMsg+"</th>";
		strLoc += "</thead>";
		strLoc += "<tbody>";

		for(var i=0; i<size; i++) {
			item = items[i];
			
			var location = $("location", item)[0].firstChild.nodeValue;
			var sub_location = $("sub_location", item)[0].firstChild.nodeValue;
			var bookshelf = $("bookshelf", item)[0].firstChild.nodeValue;
			var holdno = $("holdno", item)[0].firstChild.nodeValue;
			
			strLoc += "<tr>";
			strLoc += "<td class=\"num\">"+(i+1)+"</td>";
			strLoc += "<td>";
			
			var locVal = $("location", item)[0].firstChild.nodeValue;
			if(isUseLocationMap == "true") {
				strLoc += "<a href=\"/search/service/locationMap?loc="+locVal+"&amp;sub_loc="+$("sub_location", item)[0].firstChild.nodeValue+"&amp;bookshelf="+$("bookshelf", item)[0].firstChild.nodeValue+"\" target=\"locationMap\"" +
						" onclick=\"return locationMap(this)\" onkeypress=\"return locationMap(this)\">";
				strLoc += $("shelf", item)[0].firstChild.nodeValue;
				strLoc += "</a>";
			} else {
				strLoc += $("shelf", item)[0].firstChild.nodeValue;
			}
			strLoc += "</td>";
			
			strLoc += "<td>"+$("shelfinfo", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td>"+$("callno", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td>"+$("subscription", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td>"+$("latestdate", item)[0].firstChild.nodeValue+"</td>";
			strLoc += "<td><ul class=\"infoBtn\">";
			if ($("detaillocation", item)[0].firstChild.nodeValue == 'detailView') {
				strLoc += "<li><a href=\"#divSerTab\" onclick=\"prevSerDetail('Checkin', '"+id+"','" + location + "','" + sub_location + "','" + bookshelf + "','','" + holdno + "'); return false;\" class=\"checkinBtn\">"+msgCheckinButton+"</a></li>";
			}
			if ($("bookbindig", item)[0].firstChild.nodeValue == 'bindView') {
				strLoc += "<li><a href=\"#divSerTab\" onclick=\"prevSerDetail('Binding', '"+id+"','" + location + "','" + sub_location + "','" + bookshelf + "','','" + holdno + "'); return false;\" class=\"bindingBtn\">"+msgBindingButton+"</a></li>";
			}
			strLoc += "</ul></td>";
			strLoc += "</tr>";
		}
		strLoc += "</tbody>";
	}
	strLoc += "</table></div>";
	
	if(serholding[0] != undefined) {
		strLoc += "<div id=\"checkin_"+id+"\" class=\"infoView\" style=\"display:none;\"></div>";
		strLoc += "<div id=\"binding_"+id+"\" class=\"infoView\" style=\"display:none;\"></div>";
	}
	
	$(strLoc).appendTo("#holdingW_"+id);
	
	$("#holdingW_"+id).show();
	$("#availableButton_"+id).addClass("on");
	
	$('.mobileTable').footable({
		breakpoints :{
			phone:1024,
			tablet:1024
		}
	});
	
	$('.checkinBtn').click(function(){
		$(this).parents('tbody').find('a').removeClass('bindingBtnOn');
		$(this).parents('tbody').find('a').removeClass('checkinBtnOn');
        $(this).addClass('checkinBtnOn');
	});
	$('.bindingBtn').click(function(){
    	$(this).parents('tbody').find('a').removeClass('bindingBtnOn');
		$(this).parents('tbody').find('a').removeClass('checkinBtnOn');
        $(this).addClass('bindingBtnOn');
	});
}

function toggleServiceHelp(id) {
	if($("#" + id).css("display") == "block") {
		$("#" + id).hide();
	}
	else {
		$("#" + id).show();
	}
}

function callHoldInfo(locOrder, locCnt, sysdCtrl, hold_no){
	
	var obj = document.getElementById("prevLoc_"+sysdCtrl+locOrder);
	if(obj) {
		if(obj.style.display=="") {
			//테이블형 보기일 시 div가 td안에 정의되어 있다. td의 display를 변경 한다.
			obj.style.display="none";
			document.getElementById("locImg_"+hold_no).src=locOpenImg;
			prevTdDisplay('hidden', sysdCtrl);
			return;
		}
		else{
			for(var i=0; i<locCnt; i++) {
				document.getElementById("prevLoc_"+sysdCtrl+i).style.display="none";
				document.getElementById("locImg_"+hold_no).src=locOpenImg;
			}
			document.getElementById("locImg_"+hold_no).src=locCloseImg;
			
			if(obj.innerHTML !="") {
				prevTdDisplay('show', sysdCtrl);
				obj.style.display="";
			}
			else {
				prevTdDisplay('show', sysdCtrl);
				ajaxLoader("#prevLoc_"+sysdCtrl+locOrder, true, "middle");
				$.ajax({
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					type: "POST",
					url: "/search/prevLoc/"+sysdCtrl,
					
					dataType: "xml",
					data: "hold_no="+hold_no,
					complete: function(xhr, statusText){
					
						if(statusText=="success") {
							prevTdDisplay('hidden', sysdCtrl);
							ajaxLoader("#prevLoc_"+sysdCtrl+locOrder, false);
							viewLocation(xhr, sysdCtrl, locOrder);
						}
						else {
							alert("preview statusText===>"+statusText);
						}
						
					}
				});
			}
		}
	}
	
}

function callLoanCheck(id, sysdiv, ctrl) {
	$.ajax({
		type: "get",
		url: "/openapi/loancheck?sysdiv="+sysdiv+"&ctrl="+ctrl,
		dataType: "string",
		success: function(msg) {
			$("<span>"+msg+"</span>").appendTo("#"+id);
		},
		error: function() {
		}
	});
}

function sendsms(accession_no) {
	if(confirm(MSG_VALID_SEND_SMS)) {
		$.ajax({
			url: "/search/sendsms/"+accession_no,	
			type: "GET",
			data: "accession_no="+accession_no,		
			dataType: "json",
			cache: false,
			success: function (msg) {
				if(msg.result==0) {
					alert(MSG_VALID_COMPLETE_SMS);
				} else if(msg.result==-1) {					
					alert(MSG_VALID_LOGIN);
				} else if(msg.result==-2) {
					alert(MSG_ERROR_NOT_FIND);
				} else {
					alert(MSG_ERROR);
				}
			}
		});
	}
	return false;
}

function prevSerDetail(pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo) {
	if (IS_SER_RUNNING == true) return;
	callSerDetail(pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, '', '', '', '');
}

function callSerDetail(pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	IS_SER_RUNNING = true;

	var ser = pId.split("_");
	var dataId = ser[0];

	$.ajax({
		type: "GET",
		url: "/search/prev"+pMethod+"/"+dataId,
		data: "location="+pLocation+
		  "&sub_location="+pSubLocation+
		  "&bookshelf="+pBookShelf+
		  "&syear="+pYear+
		  "&hold_no="+pHoldNo+
		  "&oi="+pOi+
		  "&os="+pOs,
		dataType: "json",
		success: function(msg) {
			if (pMethod=="Checkin") {
				viewCheckin(msg, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
			}
			else {
				viewBinding(msg, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
			}
			IS_SER_RUNNING = false;
		},
		error: function() {
			IS_SER_RUNNING = false;
			alert("parse error!");
		}
	});	
}

function viewCheckin(json, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	var years = json.year;
	var headerHtml = "";
	var yearHtml = "";
	var listHtml = "";

	$("#checkin_" + pId + " > div").remove();
	$("#binding_" + pId + " > div").remove();
	$("#checkin_" + pId).show();
	$("#binding_" + pId).hide();

	if (pOi == '') {
		pOi = json.oi;
		pOs = json.os;
	}
	
	if(years != undefined && years.length>0) {
		var selectTag = "";
		var selectedCheck = "";
		
		if(json.syear != undefined && json.syear == 'total') {
			selectTag += "<option value=\"total\" selected=\"selected\">전체</option>";
		}
		else {
			selectTag += "<option value=\"total\">전체</option>";
		}
		
		for(var i=0; i<years.length; i++) {
			selectedCheck = "";
			if (i==0 && json.syear == undefined) {
				selectedCheck = "selected=\"selected\"";
				pYear = years[i].year;
			}
			if(json.syear != undefined && json.syear == years[i].year) {
				selectedCheck = "selected=\"selected\"";
				pYear = years[i].year;
			}
			selectTag += "<option value=\""+ years[i].year+ "\" "+selectedCheck+">"+years[i].year+"</option>";
		}
		yearHtml = "<div class=\"infoViewSelect\">";
		yearHtml += "<p>"+yearhelpMsg+"</p>";
		yearHtml += "<select name=\"selectBox\" class=\"searchOpt1 infoViewYear\" onchange=\"viewCheckinByYear('"+pId+"','"+pLocation+"','"+pSubLocation+"','"+pBookShelf+"',this[this.selectedIndex].value,'"+pHoldNo+"','"+preOi+"','"+preOs+"','"+pOi+"','"+pOs+"');\" title=\"연도선택\">"+selectTag+"</select></div>";
	}	
	
	headerHtml = "<div class=\"infoViewHeader\"><h4>"+detailholdinfoMsg+"</h4>"+yearHtml+"</div>";
	listHtml = "<div class=\"listTable\"><table class=\"searchTable searchTableDL mobileTable\"><caption>"+detailholdinfoMsg+"</caption>";

	if(json.data.length == 0) {
		listHtml += "<tr><td>"+detailholdinfoMsg+" "+noItemsMsg+"</td></tr></table>";
		$(headerHtml+listHtml).appendTo("#checkin_"+pId);
		$("#checkin_"+pId).show();
		return;
	}

	listHtml += makeThTag(TH_ITEMS_C, TH_SORT_ITEMS_C, TH_RESPONSIVE_C, 'Checkin', pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
	
	var items = json.data;
	var itemsSize = items.length;
	listHtml += "<tbody>";
	
	for(var i=0; i<itemsSize; i++) {
		listHtml += "<tr><td class=\"num\">"+(i+1)+"</td>";
		listHtml += "<td>"+items[i].volumnname+"</td>";
		listHtml += "<td>"+items[i].pubdate+"</td>";
		listHtml += "<td>"+items[i].indate+"</td>";
		listHtml += "<td><span class=\"status status01\">"+items[i].checkinstatename+"</span></td>";
		listHtml += "<td>";
		if(items[i].article != null && items[i].article!="") {
			listHtml += "<a href=\"/search/art/list?ser_controlno="+items[i].serControlno+"&checkin_no="+items[i].checkinno+"\">"+
			"<img src=\""+imgPath+"solution/common/ico/type_zart.png\" alt=\""+items[i].article+"\"></a>";
		} else {
			listHtml += "&nbsp;";
		}
		listHtml += "</td><td>";
		if(items[i].appendixname != null && items[i].appendixname!="") {
			listHtml += "<img src=\""+imgPath+"solution/common/ico/type_x.png\" alt=\""+items[i].appendixname+"\" onmouseover=\"$('#appendixInfo"+i+"').show();\" onmouseout=\"$('#appendixInfo"+i+"').hide();\" >"+
			"<p id='appendixInfo"+i+"' class='appendixInfo' style='display:none'>"+items[i].appendixname.replace(/\r/g,'</br>')+"</p>";			
		}
		listHtml += "</td></tr>";
	}
	listHtml += "</tbody></table></div>";
	
	$(headerHtml + listHtml).appendTo("#checkin_"+pId);
	$("#checkin_"+pId).show();

	$('.mobileTable').footable({
		breakpoints :{
			phone:1024,
			tablet:1024
		}
	});
}

function viewCheckinByYear(pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	if (IS_SER_RUNNING == true) return;
	callSerDetail("Checkin", pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
}

function viewBinding(json, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	var years = json.year;
	var headerHtml = "";
	var yearHtml = "";
	var listHtml = "";

	$("#checkin_" + pId + " > div").remove();
	$("#binding_" + pId + " > div").remove();
	$("#checkin_" + pId).hide();
	$("#binding_" + pId).show();

	if (pOi == '') {
		pOi = json.oi;
		pOs = json.os;
	}
	
	headerHtml = "<div class=\"infoViewHeader\"><h4>"+bookbindMsg+"</h4>";
	if (USE_SERVICE) {
		// 서비스 아이콘
		headerHtml += "<div class=\"service\" ><dl>" +
					  "<dt onclick=\"toggleServiceHelp('serviceHelp_"+pId+"');\">"+infoServiceMsg+"</dt>" +
					  "<dd id=\"serviceHelp_"+pId+"\"><ul>";
		if(isUseMypreserveInfo  == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/mypreserve.gif\" alt=\""+preserveMsg+"\"></span>"+preserveMsg+"</li>";
		}
		if(isUseCampusLoanInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/branchLoan.gif\" alt=\""+branchLoanMsg+"\"></span>"+branchLoanMsg+"</li>";
		}
		if(isUseMissrepoInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/missrepo.gif\" alt=\""+missBookMsg+"\"></span>"+missBookMsg+"</li>";
		}
		if(isUseLoanreqInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/loanreq.gif\" alt=\""+loanreqMsg+"\"></span>"+loanreqMsg+"</li>";
		}
		if(isUseDeliveryInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/delivery.gif\" alt=\""+deliveryMsg+"\"></span>"+deliveryMsg+"</li>";
		}
		if(isUsePosPrintInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/print.gif\" alt=\""+printMsg+"\"></span>"+printMsg+"</li>";
		}
		if(isSendSmsInfo == "true") {
			headerHtml += "<li><span class=\"serviceIco\"><img src=\""+imgPath+"solution/common/ico/mobile.gif\" alt=\""+smsMsg+"\"></span>"+smsMsg+"</li>";
		}
		headerHtml += "</ul></dd></dl></div>";
	}
	headerHtml += "</div>";
	
	listHtml = "<div class=\"listTable\"><table class=\"searchTable searchTableDL mobileTable\"><caption>"+bookbindMsg+"</caption>";

	if(json.data.length == 0) {
		listHtml += "<tr><td>"+bookbindMsg+" "+noItemsMsg+"</td></tr></table>";
		$(headerHtml+listHtml).appendTo("#binding_"+pId);
		$("#binding_"+pId).show();
		return;
	}

	listHtml += makeThTag(TH_ITEMS_B, TH_SORT_ITEMS_B, TH_RESPONSIVE_B, 'Binding', pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
	
	var items = json.data;
	var itemsSize = items.length;
	listHtml += "<tbody>";
	for(var i=0; i<itemsSize; i++) {
		
		listHtml += "<tr><td class=\"num\">"+(i+1)+"</td>";
		listHtml += "<td>"+items[i].printAccNo+"</td>";
		if(isUseBookShelf){
			listHtml += "<td>"+items[i].bookshelf_name+"</td>";
		}
		listHtml += "<td>"+items[i].callno+"</td>";
		listHtml += "<td>"+items[i].volumeno+"</td>";
		listHtml += "<td><span class=\"status\">"+items[i].bookstatelabel+"</span></td>";
		var dataTag = "";
		if(isUseOpacSearch == 'false'){
			if(isSeriesReserve == 'true')
			{
				if( items[i].loan_flag != '0001' && items[i].loan_flag != '0006'){
					dataTag = dataTag + "<a class='service' href='/search/reserve/form?mainno="+items[i].main_no+"&amp;location=J0000001' target='preserve' target='reserve' onclick='return doReserve(this)' onkeypress='return doReserve(this)'>"+
					"<img src='/image/ko/solution/common/ico/reserve.gif' width='16' height='16' alt='' title=''/>"+
					"</a>";
				}
				if(isUseMypreserve && items[i].closedaccess_check == '0001' && items[i].loan_flag == '0001' && items[i].catalog_flag == '0003' && items[i].material_status == '0003'){
					dataTag = dataTag + "<a class='service' href='/mypreserve/pop/write?controlno="+items[i].controlno+"&amp;main_no="+items[i].main_no+"&amp;accession_no="+items[i].accessionno+"&amp;volumeno="+items[i].volumeno+"' target='preserve' onclick='return doPreserve(this)'>"+
					"<img src='/image/ko/solution/common/ico/mypreserve.gif' width='16' height='16' alt='' title=''/>"+
					"</a>";
				}
				if(isUseSeriesDelivery != 'false' && items[i].loan_flag == '0001'){
					dataTag = dataTag + "<a class='service' href='/delivery/write?accession_no="+items[i].accessionno+"'>"+
					"<img src='/image/ko/solution/common/ico/delivery.gif' width='16' height='16' alt='' title=''/>"+
					"</a>";
				}
			}
		}
		listHtml += "<td>" + dataTag + "</td>";
		listHtml += "<td>"+items[i].remark+"</td>";
		listHtml += "</tr>";
	}
	listHtml += "</tbody></table></div>";
	
	$(headerHtml + listHtml).appendTo("#binding_"+pId);
	$("#binding_"+pId).show();
	
	$('.mobileTable').footable({
		breakpoints :{
			phone:1024,
			tablet:1024
		}
	});
}

function makeThTag(thItems, sortItems, thResponsive, pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	var nowSortSymbol = "▲";
	var thTag = "<thead><tr>";
	var thItemsSize = thItems.length;
	
	
	for(var i=0; i<thItemsSize; i++) {
		// 사용하지 않을 컬럼들은 구분하여 빈값 세팅할 수있도록 한다.
		var isRemoveItems = false;
		if(isUseBookShelf != 'true' && thResponsive[i] == bookShelfMsg){
			isRemoveItems = true;
		}
		
		if(!isRemoveItems){
			
			thTag += "<th scope=\"row\" "+thResponsive[i]+">";
			
			//1 소팅 대상인지 체크. 빈 값이 들어가 있으면 소팅 대상이 아니다.
			if(sortItems[i] !="") {
				//2 현재 소팅 아이템인지 체크. 현재 소팅 정보를 표시해줘야 한다.
				var anc = "<a href=\"javascript:serSorting('"+pMethod+"','"+pId+"','"+pLocation+"','"+pSubLocation+"','"+pBookShelf+"','"+pYear+"','"+pHoldNo+"','"+pOi+"','"+pOs+"','"+sortItems[i]+"','"+pOs+"')\" class=\"sort\">";
				if(pOi == sortItems[i]) {
					if(pOs !="ASC") {
						nowSortSymbol = "▼";
					}
					thTag += anc + thItems[i]+nowSortSymbol+"</a>";
				}
				else {
					thTag += anc + thItems[i]+"</a>";
				}
			}
			else {
				thTag += thItems[i];
			}
			
			thTag += "</th>";
		}
	}
	
	thTag += "</tr>";
	thTag += "</thead>";
	
	return thTag;
}

function serSorting(pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs) {
	if(pOi == '') {
		if(pMethod=="Checkin") {
			pOi = DEF_ORDER_ITEM_CHECKIN;
			pOs = "DESC";
		}
		else {
			pOi = DEF_ORDER_ITEM_BINDING;
			pOs = "DESC";
		}
	}
	
	if(preOi == pOi) {
		if(preOs == "ASC") {
			pOs = "DESC";
		}
		else {
			pOs = "ASC";
		}
	}
	else {
		pOs = "DESC";
	}
	
	callSerDetail(pMethod, pId, pLocation, pSubLocation, pBookShelf, pYear, pHoldNo, preOi, preOs, pOi, pOs);
}





function removeBasket(pDataId) {
	$.ajax({
		type: "POST",
		url: "/search/ajax/deleteBasket",
		data: "data="+pDataId,
		dataType: "json",
		success: function(msg) {
			if (msg.result == '0') {
				//alert("Item " + pDataId + " delete from basket!!!");
				viewBasketCount();
			}
			else {
				alert("basket delete fail!");
			}
		},
		error: function() {
			alert("basket delete fail!");
		}
	});	
}

function viewBasketCount() {
	$.ajax({
		type: "GET",
		url: "/search/ajax/countBasket",
		dataType: "json",
		success: function(msg) {
			$("#basket_count").text(msg.count);
		},
		error: function() {
			//alert("basket count fail!");
		}
	});	
}
function togglePreview(pDataId) {
	//var obj = document.getElementById("prevDetail_"+pDataId);
	//var sysdCtrl = pDataId.substring(0,3)+pDataId.substring(6);
	$("#previewBtn_"+pDataId).toggleClass("selected");
	if($("#preview_" + pDataId).css("display") == "block") {
		$("#preview_" + pDataId).hide();
	}
	else {
		$("#preview_" + pDataId).show();
	}
	previewDetail(pDataId);
}

//그래프 그리기
function graph(_data, _graphDiv, _color) {
	var _width = $(".facetList > ul").width() - 38,
		_height = $(".graph").height();
	$("#graphOuter").width(_width);

	var options = {
		'legend':{
			names: _data.name
				},
		'dataset':{
			title:'발행년도', 
			values: _data.value,
			colorset: [_color],
			fields:['발행년도']
		},
		'chartDiv' : _graphDiv,
		'chartType' : 'area',
		'leftOffsetValue': 0,
		'bottomOffsetValue': 0,
		'chartSize' : {width:_width, height:_height},
		'increment' : 1
	};
	Nwagon.chart(options);
}

// 슬라이더 셋팅
function setSlider(_min, _max) {
	$("#slider").slider({
		range: true,
		min: _min,
		max: _max,
		values: [_min, _max],
		slide: function(event, ui) {
			var start = ui.values[0],
				end = ui.values[1];
			graphMove(start, end);

			$("body").unbind("mouseup");
			$("body").mouseup(function() {
				graphReload(ui);
				$("body").unbind("mouseup");
			});
		}
	});

	function graphMove(start, end) {
		var _width = $(".facetList > ul").width() - 38;
		var step = 100 / (_max - _min),
			changeWidth = (end - start) * step,
			left = (start - _min) * step;

		$("#graph").width(changeWidth + "%");

		left = _width * (left / 100);
		$("#graph").css({"left" : left + "px"});
		$("#graphOuter").css({"left" : -left + "px"});

		$("#startDate").val(start);
		$("#endDate").val(end);

		$(window).resize(function() {
			clearTimeout($.global.resizeMove);
			$.global.resizeMove = setTimeout(function () {graphMove(start, end);}, 100);
		});
	}
}

// 슬라이더 변경 후 마우스업이 될 경우
function graphReload(ui) {
	//alert("선택 : " + ui.values[0] +", "+ ui.values[1]);

	/*$("#graphInner, #graphOuter").empty();
	$(".graph").css({
		"width" : "100%", "left" : 0
	});
	setGraph(ui.values[0], ui.values[1]);*/
}

function setGraph(_data, _first) {
	$("#graphInner, #graphOuter").empty();
	var _display = $(".pubYear dd").css("display");

	$(".pubYear dd").show(); // 그래프 영역에 값을 가져오기 위해서 show

	graph(_data, "graphInner", "#f3cb9d");
	graph(_data, "graphOuter", "#f29c39");

	var _minYear = _data.name[0],
		_maxYear = _data.name[_data.name.length - 1];

	if (_first) {
		setSlider(parseInt(_minYear), parseInt(_maxYear));
		$("#startDate").val(_minYear);
		$("#endDate").val(_maxYear);
	}

	if (_display == "none") {
		$(".pubYear dd").hide();
		var winWidth = window.innerWidth || document.documentElement.clientWidth;
		if(winWidth < 768) $(".facetList > ul").hide();
		else $(".facetList > ul").show();
	}

	$(window).resize(function() {
		clearTimeout($.global.resizeDraw);
		$.global.resizeDraw = setTimeout(function () {setGraph(_data);}, 100);
	});
}

// 그래프 데이타
function getGraphData() {
	var sorted = facetYearList.sort();
	var min = sorted[0];
	var max = sorted[sorted.length-1];
	
	var _data = {
			value : [],
			name : []
	};
	
	for (var i = 0, len = sorted.length; i < len; ++i) {
	    _data.name.push(sorted[i]);
	    _data.value.push([facetYearCount[sorted[i]]]);
	}	
	
	return _data;
}


if($('.advanceBtn').length == 0){
    if($(window).width() < 768){
              $('.dataSearch .searchW').css('padding-right','78px');
              $('.dataSearch .searchW .searchBtn').css('right','0');
    }
    $(window).resize(function(){
              if($(window).width() < 768){
                         $('.dataSearch .searchW').css('padding-right','78px');
                         $('.dataSearch .searchW .searchBtn').css('right','0');
              }else{
                         $('.dataSearch .searchW').css('padding-right','0');
              }
    });
}




// 선택 바구니담기
function addBasketSelect() {
	var checked_data = $('form[name=briefFrm] input:checkbox[name=data]:checked');
	if(checked_data.length == 0){
		alert(WARN_CHECK_FIELDS);
		return;
	}
	try{
		checked_data.each(function(){
			addBasket($(this).val());
			$('#basket_'+$(this).val()).addClass('selected');
		});
		toastr["success"](INFO_BASKET_INPUT, TITLE_BASKET);
	}catch(e){}
}

//개별 바구니 담기
function toggleBasket(pDataId) {
	var basket = "#basket_" + pDataId;
	
	if ($(basket).hasClass("selected") == true) {
		$(basket).removeClass("selected");
		removeBasket(pDataId);
		toastr["info"](INFO_BASKET_DEL, TITLE_BASKET);
	}
	else {
		$(basket).addClass("selected");
		addBasket(pDataId);
		toastr["success"](INFO_BASKET_INPUT, TITLE_BASKET);
	}
}

function addBasket(pDataId) {
	var itemType = "";
	if(pDataId.indexOf("ERS") >= 0)
	{
		itemType = "ers";
	}
	$.ajax({
		type: "POST",
		url: "/search/ajax/inputBasket",
		data: "data=" + pDataId + "&itemType=" + itemType,
		dataType: "json",
		success: function(msg) {
			if (msg.result == '0') {
				//alert("Item " + pDataId + " added to basket!!!");
				viewBasketCount();
			}
			else {
				alert("basket input fail!");
			}
		},
		error: function() {
			alert("basket input fail!");
		}
	});	
}

//선택하여 내서재 담기
function insertMylist() {
	var checked_data = $('form[name=briefFrm] input:checkbox[name=data]:checked');
	if(checked_data.length == 0){
		alert(WARN_CHECK_FIELDS);
		return;
	}
	var frm = document.briefFrm;
	var action = frm.action;
	frm.action = '/mylist/pop/writeitem';
	var options = "width=600,height=550,resizable=no,top=100,left=200,scrollbars=yes";
	window.open('',"mycolSave",options);
	frm.target="mycolSave";
	frm.submit();
	frm.action = action;
}


function insertMyLib(pDataId) {
	var options = "width=600,height=550,resizable=no,top=100,left=200,scrollbars=yes";
	window.open('/mylist/pop/writeitem?data='+pDataId,"mycolSave",options);
}

function openECIP(obj) {
	var option = "toolbar=no,scrollbars=yes,status=no,resizable=yes,width=800, height=600";
	window.open(obj.href,"ecip",option);
	return false;
}