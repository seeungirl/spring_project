<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 수정 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=10">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
    <script src="./js/jquery.js"></script>
	<script src="./js/common.js"></script>
   	<script src="./js/notice.js"></script>
   	<script src="./ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="/admin/layout/admin_header.jsp" %>​
<main class="maincss">
<form id="noti_write_frm" enctype="multipart/form-data">
	<input type="hidden" name="n_old_ori" value="${result.n_ori_img}">
	<input type="hidden" name="n_save_img" value="${result.n_save_img}">
	<input type="hidden" name="n_no" value="${result.n_no}">
	<section>
	    <p>공지사항 수정페이지</p>
		<div class="write_view">
			<ul>
			    <li>공지사항 여부</li>
			    <li>
			        <label class="label_notice"><em class="cbox"><input type="checkbox" name="n_fixed" value="${result.n_fixed}"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
			    </li>
			</ul>
			<ul>
			    <li>공지사항 제목</li>
			    <li>
			        <input type="text" class="notice_input1" name="n_subject" value="${result.n_subject}"> ※ 최대 150자까지 입력이 가능
			    </li>
			</ul>
			<ul>
			    <li>글쓴이</li>
			    <li>
			        <input type="text" class="notice_input2" value="${result.adm_id}" name="adm_id" readonly> ※ 관리자 이름이 노출 됩니다.       
			    </li>
			</ul>
			<ul>
			    <li>첨부파일</li>
			    <li>
			        <input type="file" id="nfile" name="nfile" value="${result.n_ori_img}" multiple="multiple" onchange="addFile(this)" style="display:none;">
			        <input type="button" id="fake_file" value="파일 선택"> 
			        <input type="text" name="selected_filename" id="selected_filename" value="${result.n_ori_img}">       
			        ※ 첨부파일 최대 용량은 2MB 입니다.
			    </li>
			</ul>
			<ul class="ul_height">
			    <li>공지내용</li>
			    <li>
			        <textarea class="notice_input3" id="editor" name="n_contents" placeholder="공지내용을 입력하세요!">${result.n_contents}</textarea>
			    </li>
			</ul>
		</div>
		<div class="board_btn">
		    <input type="button" value="공지목록" class="border_del" onclick="go_noticelist()">
		    <input type="button" value="공지수정" class="border_add" onclick="notiwrite_submit('modify')">
		</div>
	</section>
</form>
</main>
<%@ include file="/admin/layout/footer.jsp" %>​
</body>

<script type="text/javascript">
ckboxset();
ckload();
notice_modi_filechange();
</script>

</html>