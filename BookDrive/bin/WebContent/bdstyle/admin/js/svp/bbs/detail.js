window.onload = function(){
	/* 게시판 상세 이미지 */
	$(".boardContent img").each(function(){
		var imgs = $(this);
		var imgOuterWidth = imgs.outerWidth(true);
		var imgWidth =imgs.width();
		var imgHeight = imgs.height();
		var imgParentWidth = imgs.parent().width();
		var outWidth = imgOuterWidth - imgWidth;
		var responsiveImgWidth = imgParentWidth - outWidth;
		if(imgOuterWidth >= imgParentWidth){
			imgs.css({"width":responsiveImgWidth,"height":"auto"});
		}
		$(window).resize(function(){
			$(".boardContent img").each(function(){
				var eachImgs = $(this);
				var eachImgOuterWidth = eachImgs.outerWidth(true);
				var eachImgWidth = eachImgs.width();
				var eachImgHeight = eachImgs.height();
				var eachImgParentWidth = eachImgs.parent().width();
				var eachOutWidth = eachImgOuterWidth - eachImgWidth;
				var eachResponsiveImgWidth = eachImgParentWidth - eachOutWidth;
				if(eachImgOuterWidth >= eachImgParentWidth){
					eachImgs.css({"width":eachResponsiveImgWidth,"height":"auto"});
				}else if(eachImgParentWidth >= imgOuterWidth){
					imgs.css({"width":imgWidth,"height":imgHeight});
				}else if(eachImgParentWidth < imgOuterWidth){
					imgs.css({"width":eachResponsiveImgWidth,"height":"auto"});
				}
			});
		});
	});
		
	//게시판 상세 테이블
	$(".boardContent table").each(function(){
		var tables = $(this);
		var tableOuterWidth = tables.outerWidth(true);
		var tableWidth =tables.width();
		var tableHeight = tables.height();
		var tableParentWidth = tables.parent().width();
		var outWidth = tableOuterWidth - tableWidth;
		var responsiveTableWidth = tableParentWidth - outWidth;
		if(tableOuterWidth >= tableParentWidth){
			tables.css({"width":responsiveTableWidth,"height":"auto"});
		}
		$(window).resize(function(){
			$(".boardContent table").each(function(){
				var eachTables = $(this);
				var eachTableOuterWidth = eachTables.outerWidth(true);
				var eachTableWidth = eachTables.width();
				var eachTableHeight = eachTables.height();
				var eachTableParentWidth = eachTables.parent().width();
				var eachOutWidth = eachTableOuterWidth - eachTableWidth;
				var eachResponsiveTableWidth = eachTableParentWidth - eachOutWidth;
				if(eachTableOuterWidth >= eachTableParentWidth){
					eachTables.css({"width":eachResponsiveTableWidth,"height":"auto"});
				}else if(eachTableParentWidth >= tableOuterWidth){
					tables.css({"width":tableWidth,"height":tableHeight});
				}else if(eachTableParentWidth < tableOuterWidth){
					tables.css({"width":eachResponsiveTableWidth,"height":"auto"});
				}
			});
		});
	});
};

String.prototype.byteLength = function() {
    var l= 0;
     
    for(var idx=0; idx < this.length; idx++) {
        var c = escape(this.charAt(idx));
        if( c.length==1 ) l ++;
        else if( c.indexOf("%u")!=-1 ) l += 3;
        else if( c.indexOf("%")!=-1 ) l += c.length/3;
    }
     
    return l;
};

function deleteAble(){
	if(document.frm.deleteValue.value=="N"){
		alert(FORBID_DELETE_BOARD_MSG);
		return false;
	}
}

/*댓글 저장*/
function commentAdd(bbsId,contentId){
	
	var commentDetail = $("#comment_detail").val();
	var data = "bbs_id="+bbsId+"&content_id="+contentId+"&comment_detail="+encodeURIComponent(commentDetail);	
	$.ajax({
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		url: "/bbs/comment",
		type: "POST",
		data: data,
		dataType: "json",
		cache: false,
		success: function(msg) {
			if (msg.result== -1) {
				alert(MSG_FAILED_REPLY_SAVE);
			}else if(msg.result== -2){
				$("#writeSpace").css("display","");
			}else if(msg.result== -3){
				$("#overLength").css("display","");
			}else if(msg.result== -4){
				alert(MSG_LOGIN_FIRST);
			}else if(msg.result== -5){
				alert(MSG_REQUIRED_GRANT);
			}else{
				$("#comment_detail").val("");
				$("#overLength").css("display","none");
				$("#writeSpace").css("display","none");
				showAddedComment(msg);
			}
		},
		error: function() {
			alert(MSG_FAILED_REPLY_SAVE);
		}
	});
}

function showAddedComment(json) {
	
	$("<div id=\"divInline"+json.commentId+"\" class=\"divInline\">"+
			"<img src=\""+json.userIcon+"\" title=\""+MSG_USER_IMAGE+"\" width=\"25\" height=\"24\"  alt=\""+MSG_USER_IMAGE+"\" class=\"inlineUserimg\">"+
			"<div class=\"inlineTitleW\">"+
			"<div class=\"inlineTitle\">"+json.detail+"</div>"+
			"<dl class=\"inlineData\">"+
				"<dt class=\"inlineWriter\">"+MSG_WRITER+"</dt>"+
				"<dd class=\"inlinePresent\">"+json.writerName+
					"&nbsp;<img src=\""+JS_IMGPATH + "common/ico/mail.gif\" title=\""+MSG_MAIL+"\" width=\"16\" height=\"11\" alt=\""+MSG_MAIL+"\">"+
				"</dd>"+
				"<dt class=\"inlineDateTitle\">"+MSG_DATE+"</dt>"+
				"<dd class=\"inlineDate\">&nbsp;|&nbsp;"+json.insertDate+"</dd>"+
			"</dl>"+
			"<div class=\"inlineBtn\">"+
				"<span class=\"BtnArrow\"><a href=\"javascript:commentDel('"+json.commentId+"','"+json.contentId+"'); \">&nbsp;&nbsp;"+MSG_DEL+"</a></span>"+
			"</div>"+
		"</div></div>").appendTo("#divCommentList");
		
		$("#commentNumber").text("["+json.commentCnt+"]");
		
}

function commentDel(commentId,contentId,count){
	
	var data = "comment_id="+commentId+"&content_id="+contentId;
	
	$.ajax({
		url: "/bbs/commentDel",
		type: "POST",
		data: data,
		dataType: "json",
		cache: false,
		success: function (msg) {
			if (msg.result==1) {
				$("#divInline"+commentId+"").remove();
				var cnt = $("#commentNumber").text();
				var cnt2 = cnt.replace("[","");
				cnt2 = cnt2.replace("]","");
				var cnt3 = cnt2-1;
				$("#commentNumber").text("["+cnt3+"]");
			}else {
				alert(MSG_FAILED_REPLY_DELETE);
			}
		},
		error: function(msg) {
			alert(MSG_FAILED_REPLY_DELETE);
		}
	});
	
}

function insertGrade(writer_ip,bbs_id, content_id){
	
	var gradeVal = $('input[name=grade]:radio:checked').val();
	var commentVal = $('input[name=content]:text').val();
	
	var chk = document.getElementsByName("grade");
	var chk_leng = chk.length;
	var cnt_checkRadio = 0;
	for (var i = 0; i < chk_leng; i++){
		if (chk[i].checked == true){
			cnt_checkRadio++;
		}
	}
		
	if(cnt_checkRadio == 0){
		alert('만족도를 체크해주세요');
		return false;
	}
	var commentVal = $('input[name=content]:text').val();

	if(gradeVal == 1 || gradeVal == 2){
		if(commentVal == ''){
			alert("의견을 입력해주세요!");
			return false;
		}
	}

	var data = "grade=" + gradeVal + "&content=" + commentVal + "&bbs_id=" + bbs_id + "&content_id=" + content_id + "&writer_ip=" + writer_ip; 
	
	$.ajax({
		url: "/grade/insert",
		type: "POST",
		data: data,
		dataType: "json",
		cache: false,
		success: function (msg) {
			if (msg.result == 1) {
				if(msg.resultModule == 'succ'){
					alert(MSG_SAVE);
				}else{
					alert('이미 설문조사에 참여하셨습니다.\n 설문조사는 해당 건별로 한번만 가능합니다');
				}
				location.reload();
			}else {
				alert('Error');
			}
		},
		error: function(msg) {
			alert('Error');
		}
	});
	
}
function deleteMsg(){
	var commentVal = $('input[name=content]:text').val();
	var msg = MSG_GUIDE;
	
	if(commentVal == msg){
		$('input[name=content]:text').attr("value", "");
	}
}