
function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

function deleteCookie(name){
	document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function getCookie(name) {
	
	var nameOfCookie = name + "=";
	var x = 0;

	while ( x <= document.cookie.length )
	{
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie )
		{
			if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
				endOfCookie = document.cookie.length;
				return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;
		if ( x == 0 )
		break;
	}
	return ;
}

function notice_setCookie( name, value, expiredays ){
	
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; " +
			"path=/; expires=" + todayDate.toGMTString() + ";"
}
function individualNotice_setCookie( name, value, expiredays ){
	
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; " +
			"path=/; expires=" + todayDate.toGMTString() + ";"
}

function notice_closeWin(name){
	
	if ( document.forms[0].Notice.checked ) {
		notice_setCookie( name, "done" , 1); // 1=�Ϸ?�� ����â ���� ����
		window.close();
	}
}

function individualNotice_closeWin(name){
	if ( document.forms[0].individualNotice.checked ) {
		individualNotice_setCookie( name, "done" , 1); // 1=�Ϸ?�� ����â ���� ����
		window.close();
	}
}

function popContentView(cnt, totalSize){
	for(i=0; i<totalSize; i++) {
		if(cnt == i) {
			$("#title_"+i).addClass("on");
			$("#content_"+i).css("display", "");
		} else {
			$("#title_"+i).removeClass("on");
			$("#content_"+i).css("display", "none");
		}
	}
}


var tmp_noticeId = "";
var topNotice = "";

function viewNotice(noticeId){
	var tlist;
	var rwidth;
	//document.getElementById("title_"+noticeId).className="board_noticeOver";
	document.getElementById("a_"+noticeId).style.color="red";
	//document.getElementById("title_"+noticeId).onMouseOut="";
	if(tmp_noticeId != "" && noticeId != tmp_noticeId){
		overClose(tmp_noticeId);
	}
	tmp_noticeId = noticeId;
	document.getElementById("viewContent").innerHTML = document.getElementById(noticeId).innerHTML;
	tlist = document.getElementById(noticeId).getElementsByTagName("table");
	for (i=0;i<=tlist.length;i++){
	if(tlist[i] != null){
		if(tlist[i].name == "topTable"){
			rwidth = parseInt(tlist[i].width) + 80;
			window.resizeTo(rwidth, tlist[i].height);
			return;
		}
	}
	}
window.resizeTo(410,500);
}

function overClose(noticeId){
	document.getElementById("a_"+noticeId).style.color="#0C4071";
}

function onLoad(){
	
	//viewNotice(topNotice);
}

//초단위 쿠키 생성(초,분,시,일 단위별 쿠키 생성 가능)
function setCookiePerSec(name, value, sec) {
	var expire = new Date();
	expire.setTime(expire.getTime() + sec*1000);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expire.toUTCString() + ";";
}
