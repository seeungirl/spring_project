function go_noticewrite(){
	location.href = "./notice_write.do";
}

function go_noticelist(){
	location.href = "./notice_list.do";	
}

function go_noticemodify(val){
	location.href = "./notice_modify.do?no="+val;
}

function ckload(){
	window.onload = function(){ 
		var c = CKEDITOR.replace("editor",{
			width : 900,
			height : 400
		})
	}
}


function notiwrite_submit(val){
	var ed = CKEDITOR.instances.editor.getData();
	var actionurl = "";
	if(val=='write'){
		actionurl="./notice_writeok.do";
	}else{
		actionurl="./notice_modifyok.do";
	} 

	if(noti_write_frm.n_subject.value == ""){
		alert("공지사항 제목을 입력하세요.")	
	}else if(ed==""){
		alert("내용을 입력하세요");
	}else{		
		noti_write_frm.method="post";
		noti_write_frm.action=actionurl;
		noti_write_frm.submit();
	}
	
}


/* 첨부파일 추가 */
function addFile(obj){
	var max = 2;
	var maxSize = max * 1024 * 1024;
    var curFileCnt = obj.files[0].size; 
	
    if (curFileCnt > maxSize) {
        alert("첨부파일은 최대 " + max + "MB 까지 첨부 가능합니다.");
	    document.querySelector("input[name=nfile]").value = "";
    }
}

function notice_modi_filechange(){
	$(function(){
		$('#fake_file').click(function(){
		    $("#nfile").click();
		});
		$('#nfile').change(function() {
			var w=0;
			var f_nm = "";
			while(w<$('#nfile')[0].files.length){
				if(w==0){
					f_nm+=$('#nfile')[0].files[w].name;			
				}else{
					f_nm+=","+$('#nfile')[0].files[w].name;
				}
				
				w++;
			}
			
			$('#selected_filename').val(f_nm);
		});
	})
}

function notice_delete(val){
	
	if(val=="list"){
		if(sum == 0){
			alert('삭제할 공지사항 글을 선택해주세요.');
		}else{
			act_del();
		}
	}else if(val=="view"){
		act_del();
	}
}

function act_del(){
	if(confirm("공지사항 데이터 삭제시 복구 되지 않습니다.")){
		noti_delete_frm.action="./noti_deleteok.do";
		noti_delete_frm.method="POST";
		noti_delete_frm.submit();		
	}
}
