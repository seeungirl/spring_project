function notiwrite_submit(){
	var ed = CKEDITOR.instances.editor.getData(); 

	if(noti_write_frm.n_subject.value == ""){
		alert("공지사항 제목을 입력하세요.")	
	}else if(ed==""){
		alert("내용을 입력하세요");
	}else{
		if(noti_write_frm.n_fixed.checked){
			noti_write_frm.n_fixed.value="Y";
		}else{
			noti_write_frm.n_fixed.value="N";
		}
		console.log(noti_write_frm.n_fixed.value)
		
		noti_write_frm.method="post";
		noti_write_frm.action="./notice_writeok.do";
		//noti_write_frm.submit();
		
	}
	
}