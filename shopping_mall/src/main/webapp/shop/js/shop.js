var sum=0;
function shop_del_ckbox_all(){
	var allck = document.getElementById("allck");
	var ck = document.querySelectorAll(".oneck");
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

function go_join(){
	agree_frm.action="./join.do";
	agree_frm.method="post";
		
	if(agree_frm.m_agree_mail.checked==false || agree_frm.m_agree_sms.checked==false){
		alert("이용약관 및 개인정보 이용 및 수집 정보는 필수 항목입니다");
	}else{
		agree_frm.m_agree_mail.value="Y";
		agree_frm.m_agree_sms.value="Y";
		agree_frm.submit();
	}
//	location.href = "./join.do";
}

function go_agree(){
//	agree_frm.action="./agreeok.do";
//	agree_frm.methoe="post";
//	agree_frm.submit();
	location.href = "./agree.do";
}