function doReserve(obj) {
	var options = "width=760,height=520,resizable=no,top=100,left=200,scrollbars=yes";
	window.open(obj.href,"reserve",options);
	return false;
}

function doBranch(obj) {
	var options = "width=760,height=520,resizable=no,top=100,left=200,scrollbars=yes";
	window.open(obj.href,"branch",options);
	return false;
}

function doPreserve(obj) {
	var options = "width=760,height=520,resizable=no,top=100,left=200,scrollbars=yes";
	window.open(obj.href,"preserve",options);
	return false;
}

function doLoanreq(obj) {
	var options = "width=760,height=420,resizable=no,top=200,left=300,scrollbars=yes";
	window.open(obj.href,"loanreq",options);
	return false;
}

function doPosprint(obj){
	var options = "width=400,height=300,resizable=no,top=200,left=300,scrollbars=yes";
	window.open(obj.href,"miniprint",options);
	return false;
}

//안산1대  CDNET전용
function cdNetPopup(url){
	var options = "width=360,height=250,resizable=no,top=200,left=300,scrollbars=yes";
	window.open(url,"",options);
}