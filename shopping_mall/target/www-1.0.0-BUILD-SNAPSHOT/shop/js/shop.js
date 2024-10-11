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
}

function go_agree(){
	location.href = "./agree.do";
}

function join_ck_id(m_id,adm_id){
	if(m_id != ""){
			var ajax = new XMLHttpRequest();
			ajax.onreadystatechange = function(){
				if(ajax.readyState==4 && ajax.status==200){
					console.log(this.response); //data가 있으면 1 없으면 0
					var resultHtml = document.getElementById("shop_info_txt");
					
					if(this.response == 0){ //사용가능
						resultHtml.innerText="사용 가능한 아이디입니다.";
						resultHtml.style.display = "block";
						resultHtml.classList.remove('warn');
						shop_join_frm.db_ck.value = "Y";
					}else{ //사용가능
						if(this.response > 0){
							resultHtml.innerText="사용 불가능한 아이디입니다.";
							resultHtml.style.display = "block";
							resultHtml.classList.add('warn');
							shop_join_frm.db_ck.value = "N";							
						}else{
							alert("잘못된 접근입니다");
							location.href="./admin_master.do";
						}
					}
				}
			};
			ajax.open("GET","./shop_idck.do?m_id="+m_id+"&adm_id="+adm_id,true);
			ajax.send();
		}else{ 
			alert("아이디를 입력해주세요");
		}
}

//메일 인증
function shop_email(){
	
}

function join_submit(){
	if(shop_join_frm.m_id.value==""){
		alert("아이디를 입력해주세요");
	}else if(shop_join_frm.m_pass.value==""){
		alert("패스워드를 입력해주세요");
	}else if(shop_join_frm.m_pass_ck.value==""){
		alert("동일한 패스워드를 한번 더 입력해주세요")
	}else if(shop_join_frm.m_pass.value != shop_join_frm.m_pass_ck.value){
		alert("패스워드가 다른 값이 입력되었습니다.");
		shop_join_frm.m_pass_ck.value = "";
	}else if(shop_join_frm.m_name.value==""){
		alert("이름을 입력해주세요");
	}else if(shop_join_frm.m_email.value==""){
		alert("이메일을 입력해주세요");
	}else if(shop_join_frm.m_phone.value==""){
		alert("휴대폰 번호를 입력해주세요");
	}else if(shop_join_frm.db_ck.value == ""){
		alert("중복체크는 필수값입니다");
	}else{
		if(shop_join_frm.db_ck.value == "N"){
			alert("해당 아이디는 사용중입니다.")
		}else{
			shop_join_frm.method="post";
			shop_join_frm.action="./shopjoin_ok.do";
			shop_join_frm.submit();						
		}
	}
}