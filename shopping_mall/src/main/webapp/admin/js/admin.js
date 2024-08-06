/*-- admin --*/

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
					var resultHtml = document.getElementById("adm_info_txt");
					
					if(this.response == 0){ //사용가능
						resultHtml.innerText="사용 가능한 아이디입니다.";
						resultHtml.style.display = "block";
						resultHtml.classList.remove('warn');
						join_frm.db_ck.value = "Y";
					}else{ //사용가능
						if(this.response > 0){
							resultHtml.innerText="사용 불가능한 아이디입니다.";
							resultHtml.style.display = "block";
							resultHtml.classList.add('warn');
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
	var msg = "";
	if (apv =="Y"){
		msg = "관리자 승인을 진행하시겠습니까?";
	}else{
		msg = "관리자 승인을 해제하시겠습니까?";
	}
	if(confirm(msg)){
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function(){
			if(ajax.readyState==4 && ajax.status==200){
				if(this.response > 0){
					alert('승인설정이 완료되었습니다.');
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

//사이트 정보 저장 버튼
function siteinfo_submit(e){
	var required = $("#siteinfo").find("input[type='text']");
	var length=0;
	var count=0;
	
	$.each(required,function(idx){
		if($(this).attr('data-rq')=="true"){
			length++;
		}
	});
	
	var isValid = true;
	var checkInputValue = $.each(required,function(idx){
		if($(this).attr('data-rq')=="true"){
			var title = $(this).parent("li").prev().text();
			if($(this).val() == ""){
				alert(title+" 값은 필수 값입니다.");
				$(this).focus();
				isValid = false;
				
				return false;
			}else{
				if($(this).attr('data-isnan')=="true"){
					if(isNaN($(this).val())==true){
						alert(title+" 값은 숫자로만 입력이 가능합니다.");
						$(this).focus();
						isValid = false;
						
						return false;						
					}
				}
				count++;
			}
		}
	});

	if(count == length){
		if( parseInt(ms_frm.ms_pay_maxpoint.value) < parseInt(ms_frm.ms_pay_minpoint.value) ){
			alert("결제 최대 포인트는 최소 포인트보다 커야합니다.");
		}else if(ms_frm.ms_pay_bank.value !="" && ms_frm.ms_pay_banknum.value==""){
			alert("무통장 은행 선택시 은행계좌번호는 필수값입니다.")
		}else if(isNaN(ms_frm.ms_pay_banknum.value)==true){
			alert("은행계좌번호는 숫자로만 입력해주세요");
		}else{
			ms_frm.method="post";
			ms_frm.action="./adm_siteinfo_ok.do";
			ms_frm.submit();			
		}
	}else{
		
	}

	
	return isValid;
}




