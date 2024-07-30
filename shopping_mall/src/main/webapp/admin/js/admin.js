function admin_master(){
	location.href = "./admin_master.do";
}

function admin_main(){
	location.href = "./admin_main.do";	
}

function adminjoin_submit(){
	if(join_frm.adm_id.value==""){
		alert("아이디를 입력해주세요");
	}else if(join_frm.adm_pass.value==""){
		alert("패스워드를 입력해주세요");
	}else if(join_frm.adm_pass_ck.value==""){
		alert("동일한 패스워드를 한번 더 입력해주세요")
	}else if(join_frm.adm_pass.value != join_frm.adm_pass_ck.value){
		alert("패스워드가 다른 값이 입력되었습니다.");
		join_frm.adm_pass_ck.value = "";
	}else if(join_frm.adm_email.value==""){
		alert("이메일을 입력해주세요");
	}else if(join_frm.adm_phone[1].value=="" || join_frm.adm_phone[2].value==""){
		alert("휴대폰 번호를 모두 입력해주세요");
	}else if(join_frm.adm_position1.value=="" || join_frm.adm_position2.value==""){
		alert("담당 부서를 모두 선택해주세요.")
	}else if(join_frm.db_ck.value == ""){
		alert("중복체크는 필수값입니다");
	}else{
		if(join_frm.db_ck.value == "N"){
			alert("해당 아이디는 사용중입니다.")
		}else{
			join_frm.method="post";
			join_frm.action="./adminjoin_ok.do";
			join_frm.submit();						
		}
	}
}

//아이디 중복체크
function duplication_id(adm_id){
		if(adm_id != ""){
			var ajax = new XMLHttpRequest();
			ajax.onreadystatechange = function(){
				if(ajax.readyState==4 && ajax.status==200){
					console.log(this.response); //data가 있으면 1 없으면 0
					
					if(this.response == 0){ //사용가능
						document.getElementById("adm_info_txt").innerText="사용 가능한 아이디입니다.";
						document.getElementById("adm_info_txt").style.display = "block";
						document.getElementById("adm_info_txt").classList.remove('warn');
						join_frm.db_ck.value = "Y";
					}else{ //사용가능
						if(this.response > 0){
							document.getElementById("adm_info_txt").innerText="사용 불가능한 아이디입니다.";
							document.getElementById("adm_info_txt").style.display = "block";
							document.getElementById("adm_info_txt").classList.add('warn');
							join_frm.db_ck.value = "N";							
						}else{
							alert("잘못된 접근입니다");
							location.href="./admin_master.do";
						}
					}
				}
			};
			ajax.open("GET","./duplication_idck.do?adm_id="+adm_id,true);
			ajax.send();
		}else{ 
			alert("아이디를 입력해주세요");
		}
}

function adminlogin_submit(){
	if(login_frm.adm_id.value ==""){
		alert("아이디를 입력해주세요");
		return false;
	}else if(login_frm.adm_pass.value ==""){
		alert("패스워드를 입력해주세요");
		return false;
	}else{
		login_frm.method="post";
		login_frm.action = "./adminlogin_ok.do"
		login_frm.submit();
	}
}

//승인 미승인 변경
function change_aprove(no,apv){
	if(confirm("정말 변경하시겠습니까?")){
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function(){
			if(ajax.readyState==4 && ajax.status==200){
				if(this.response > 0){
					location.reload(true);
				}else{
					alert('잘못된 접근입니다. 다시 시도해주세요.');
					location.href='./admin_main.do';
				}
			}
		};
		ajax.open("POST","./change_aprove.do",true);
		ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
		ajax.send("adm_aprove="+apv+"&adm_no="+no);
	}
}

function admin_logout(){
	location.href = "./admin_logout.do";
}


