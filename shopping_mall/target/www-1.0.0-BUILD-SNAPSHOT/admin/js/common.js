function admin_master(){
	location.href = "./admin_master.do";
}

function admin_main(){
	location.href = "./admin_main.do";	
}

function admin_logout(){
	location.href = "./admin_logout.do";
}

function cancel(){
	if(confirm("정말 취소하시겠습니까?")){
		location.reload(true);
	}
}

//사이트 정보 저장 페이지 라디오버튼 불러오기
function radioset(){
	var radio = document.querySelectorAll("input[type='radio']");
	var namelist = new Array();

	var f;
	for(f=0; f<radio.length; f++){
		namelist.push(radio[f].name);
	}
	var set = new Set(namelist);
	var rdnamelist = [...set];

	var w=0;
	while(w<rdnamelist.length){
		var tg = document.getElementsByName(rdnamelist[w]);
		var ww=0;
		while(ww<tg.length){
			if(tg[ww].attributes['data-radio'].nodeValue != ""){
				if(tg[ww].value == tg[ww].attributes['data-radio'].nodeValue){
					tg[ww].checked = true;
				}				
			}else{
				if(tg[ww].value == 'N'){
					tg[ww].checked = true;
				}
			}
			ww++;
		}
		w++;
	}
}

//사이트 정보 저장 페이지 체크박스 불러오기
function ckboxset(){
	var ckbox = document.querySelectorAll("input[type='checkbox']");
	
	var w=0;
	while(w<ckbox.length){
		if(ckbox[w].value == "N"){
			ckbox[w].checked=false;
		}else{
			ckbox[w].checked=true;
		}
		
		
		w++;
	}
}


var sum=0;
function del_ckbox_all(){
	var allck = document.getElementById("allck");
	var ck = document.getElementsByName("oneck");
	allck.addEventListener("click",function(){
		var cked = this.checked;
		var w=0;
		while(w<ck.length){
			ck[w].checked = cked;
			w++;
		}
	});

	var ww=0;
	while(ww<ck.length){
		ck[ww].addEventListener("click",function(){
			if(this.checked == true){
				sum++;
			}else{
				sum--;
			}
			
			if(sum == ck.length){
				allck.checked=true;
			}else{
				allck.checked=false;
			}
		})
		ww++;
	}
}

function pagingck(){
	const urlParams = new URL(location.href).searchParams;
	const page = urlParams.get('page');
	if(page != null){
		var paging_each = document.querySelectorAll(".pageing li:has(a)");
		var pagingli = document.querySelectorAll(".pageing li a");
		var w=0;
		while(w<paging_each.length){
			if(page == pagingli[w].innerText){
				paging_each[w].classList.add("selected");			
			}
			w++;
		}
		
	}
}