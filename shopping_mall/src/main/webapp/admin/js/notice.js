function go_noticewrite(){
	location.href = "./notice_write.do";
}

function go_noticelist(){
	location.href = "./notice_list.do";	
}


function notiwrite_submit(){
	var ed = CKEDITOR.instances.editor.getData(); 

	if(noti_write_frm.n_subject.value == ""){
		alert("공지사항 제목을 입력하세요.")	
	}else if(ed==""){
		alert("내용을 입력하세요");
	}else{		
		noti_write_frm.method="post";
		noti_write_frm.action="./notice_writeok.do";
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
	if(confirm("정말 삭제하시겠습니까?")){
		noti_delete_frm.action="./noti_deleteok.do";
		noti_delete_frm.method="POST";
		noti_delete_frm.submit();		
	}
}
