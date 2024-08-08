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
	function cateprd_del_ck(){
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


	function cateprd_delete(part){
		var action = "";
		var frm;
		if(part=="cate"){
			action = "./cate_deleteok.do";
			frm = cate_delete_frm;
		}else if(part=="prd"){
			action = "./prd_deleteok.do";
			frm = prd_delete_frm;
		}
		
		if(sum == 0){
			alert('삭제할 카테고리를 선택해주세요.');
		}else{
			if(confirm("정말 삭제하시겠습니까?")){
				frm.method = 'POST';
				frm.action = action;
				frm.submit();			
			}
		}
	}
	
	function cate_gomodify(val){
		location.href = "./cate_modify.do?c_no="+val;
	}
	
	function catelist_search(){
		catelist_search_frm.method="get";
		catelist_search_frm.action="./cate_list.do";
		catelist_search_frm.submit();
	}
	
	function cateprd_sc_ck(){
		var ea = document.getElementById("search_select").children.length;
		var tg = document.querySelectorAll("option")
		var w=0;
		while(w<ea){
			if(tg[w].value == document.getElementById("search_select").getAttribute("select_data")){
				tg[w].setAttribute("selected","selected");
			}
			w++;
		}
	}
	
	function priceck(val,target){
		var price = target.p_price.value;
		if(price==""){
			alert("상품 가격을 입력해주세요.");
			target.p_price.focus();
			target.p_dc_percent.value="";
		}else{
			if(isNaN(val)==true){
				alert("숫자로만 입력해주세요.")
				target.p_dc_percent.value="";
			}else{
				var result = Math.floor(price/100*val);
				
				if(val == "0"){
					target.p_dc_money.value = 0;	
				}else{
					target.p_dc_money.value = result;
					
				}		
			}	
		}	
	}
	
	function product_code_ck(){
		var prdcode = ""; 

		var f;
		for(f=0; f < 7; f++){
		    var num = Math.floor(Math.random()*10);
		    prdcode = prdcode + num;
		}
		
		prd_write_frm.p_code.value = prdcode;

	}
	
	//상품코드 중복체크
	function duplication_prdcode(prdcode){
			if(prdcode != ""){
				var ajax = new XMLHttpRequest();
				ajax.onreadystatechange = function(){
					if(ajax.readyState==4 && ajax.status==200){
						if(isNaN(prdcode)==true){
							alert("상품 코드는 숫자로만 입력 가능합니다.");
							prd_write_frm.p_code.value="";
						}else if(prdcode.length != 7){
							alert("상품 코드는 7자리로 입력해주세요");
							product_code_ck();
						}else{
							dp_prdcode_html(this.response); //data가 있으면 1 없으면 0							
						}
					}
				};
				ajax.open("GET","./duplication_prdcodeck.do?p_code="+prdcode+"&adm_id="+prd_write_frm.adm_id.value ,true);
				ajax.send();
			}else{ 
				alert("아이디를 입력해주세요");
			}
	}
	
	function dp_prdcode_html(callback){
		var resulthtml = document.getElementById("db_ck_text");
		if(callback == 0){ //사용 가능
			resulthtml.innerText = "사용 가능한 상품코드입니다."
			resulthtml.classList.remove('warn');
			resulthtml.classList.add('pass'); 
			prd_write_frm.pd_ck.value="Y";
		}else{
			if(callback > 0){
				resulthtml.innerText = "이미 존재하는 상품코드입니다."
				resulthtml.classList.remove('pass');
				resulthtml.classList.add('warn');
				prd_write_frm.pd_ck.value="N";
			}else{
				alert("잘못된 접근입니다");
				location.href="./product_write.do.do";
			}
		}
	}
	
	function prd_insert(val){
		var frm;
		var action = "";
		if(val=='write'){
			frm = prd_write_frm;
			action="./prdinsert_ok.do";							
		}else{
			frm = prd_modify_frm;
			action="./prdmodify_ok.do";	
		}
		
		if(frm.cate_name.value == ""){
			alert("대메뉴 카테고리를 선택해주세요.");
		}else if(frm.p_code.value == ""){
			alert("상품 코드를 입력해주세요.");
		}else if(frm.p_name.value == ""){
			alert("상품명을 입력해주세요.");
		}else if(frm.p_price.value == ""){
			alert("상품 가격을 입력해주세요.");
		}else if(isNaN(frm.p_price.value)==true){
			alert("상품 가격은 숫자로만 입력 가능합니다.");
		}else if(isNaN(frm.p_stock.value)==true){
			alert("상품 재고는 숫자로만 입력 가능합니다.");
		}else if(frm.files[0].value == "" && val == 'write'){
			alert("썸네일 이미지를 등록해주세요.");
		}else if(frm.pd_ck.value == ""){
			alert("상품 코드 중복체크를 해주세요");
		}else{
			if(val=='modify'){
				var orithumb = document.querySelectorAll("#img_area > div");

				var i=0;
				var data = "";
				while(i<3){
					if($("#img_area>div[data-no='"+ i +"']").length){
						data = $("#img_area>div[data-no='"+ i +"'] img").attr("src").split("prdimg_upload/")[1];
					}else{
						data="";
					}
					$("input[name='old_p_thumb_img'][data-no='"+ i +"']").val(data);
					
					i++;
				}
			}
			frm.method="post";
			frm.action=action;
			frm.submit();
		}
	}
	
	function prdlist_search(){
		catelist_search_frm.method="get";
		catelist_search_frm.action="./product_list.do";
		catelist_search_frm.submit();
	}
	
	function product_modify_imgck(){
		var imgbox = document.querySelectorAll("#img_area >div");
		var files = document.getElementsByName("files");
		if(imgbox.length > 0){
			var w=0;
			while(w<imgbox.length){
				files[w].parentNode.style.display = "none";
				w++;
			}
		}
	}
	
	function imgdel(val){
		$("#file_area > li[data-no='"+ val +"']").css("display","block");
		
		var img = $("#img_area > div[data-no='"+ val +"'] >a").attr("href").split("prdimg_upload/")[1];
		$("input[name='del_p_thumb_img'][data-no='"+ val +"']").val(img);
		$("input[name='old_p_ori_img'][data-no='"+ val +"']").val("");
		$("#img_area>div[data-no='"+ val +"']").remove();
	}
	
	function prd_gomodify(val){
		location.href = "./product_modify.do?p_no="+val;
	}
	
	function prd_modify(){
		console.log(prd_modify_frm.files[0].value);
	}
	