function go_prdlist(){
	location.href = "./product_list.do";
}

function go_prdwrite(){
	location.href = "./product_write.do";
}

function go_catelist(){
	location.href = "./cate_list.do";
}

function go_catewrite(){
	location.href = "./cate_write.do";
}

function make_cate(val){
	var catecode = cate_frm.cate_code.value;
	if(cate_frm.group_code.value == ""){
		alert("분류코드를 입력하세요.");
	}else if(catecode == ""){
		alert("대메뉴 코드를 입력하세요.");
	}else if(cate_frm.cate_name.value == ""){
		alert("대메뉴명을 입력하세요.");
	}else{
		if(isNaN(cate_frm.group_code.value) == true){
			alert("분류코드는 숫자로만 입력해주세요");
		}else if(isNaN(catecode) == true){
			alert("대메뉴명은 숫자로만 입력해주세요");
		}else{
			if(val=='write'){
				cate_frm.action="./catewrite_ok.do";							
			}else{
				cate_frm.action="./catemodify_ok.do";	
			}
			cate_frm.method="post";
			cate_frm.submit();
		}
	}
}

	var sum=0;
	function category_del_ck(){
		var allck = document.getElementById("allck");
		var ck = document.getElementsByName("cateck");
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


	function category_delete(){
		if(sum == 0){
			alert('삭제할 카테고리를 선택해주세요.');
		}else{
			if(confirm("정말 삭제하시겠습니까?")){
				cate_delete_frm.method = 'POST';
				cate_delete_frm.action = './cate_deleteok.do';
				cate_delete_frm.submit();			
			}
		}
	}
	
	function cate_gomodify(val){
		location.href = "./cate_modify.do?c_no="+val;
	}
	
	function modify_cate(){
		
	}