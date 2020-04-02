// checkbox : 함수와 연동할 체크 박스 객체
// lngCheckAlert를 재정의해야 한다.
var flag = true;

// checkbox를 모두 선택 또는 모두 선택해제 
function checkAllOrNone(checkbox)
{
    // 의미 있는 체크 박스일때, 
    if (checkbox) {
        // 여러개일 때
        if (checkbox.length) {
         	for (i = 0; i < checkbox.length; i++)
        		checkbox[i].checked = flag;
        }
        else {
            checkbox.checked = flag;
        }
    	flag = !flag;	
    }
}

// checkBox의 개수를 리턴
function countNo(checkbox)
{
    if (checkbox)
        return ((checkbox.length) ? checkbox.length: 1);
    else
        return 0;    
}
// checkbox 중에서 선택된 것이 하나라도 있으면, true리턴, 아무것도 선택되어 있지 않으면 false리턴
function isEverChecked(checkbox)
{
    // 의미 있는 체크 박스일때, 
    if (checkbox) {
        // 여러개일 때
        if (checkbox.length) {
        	for (i = 0; i < checkbox.length; i++)
        	    if (checkbox[i].checked == true)
        	        return true;
        }
        else {
            if (checkbox.checked == true)
                return true;
        }
    }
    return false;
}

// 체크 박스를 체크하였는지 확인한다.
function isValidCheckbox(checkbox) {
    if ((count = countNo(checkbox)) == 0) {
        return false;
    }
    else if (!isEverChecked(checkbox)) {
        alert(lngCheckAlert);
        return false;
    }
    return true;
}    
//자기자신, 폼이름, 체크될 체크박스이름
function checkedAll(obj, form, name) {
	var f=eval("document."+form);
	if(obj.checked){
		for(var j=0; j<f.elements.length; j++){
			if(f.elements[j].type=="checkbox"){
				if(f.elements[j].name == name){
					f.elements[j].checked = true;
				}
			}
		}
	}else{
		for(var j=0; j<f.elements.length; j++){
			if(f.elements[j].type=="checkbox"){
				if(f.elements[j].name == name){
					f.elements[j].checked = false;
				}
			}
		}
	}	
}
