<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=10">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
	<script src="./js/common.js"></script>
   	<script src="./js/notice.js"></script>
   	<script src="./ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="/admin/layout/admin_header.jsp" %>​
<main class="maincss">
<form id="noti_write_frm" enctype="multipart/form-data">
	<section>
	    <p>공지사항 등록페이지</p>
		<div class="write_view">
			<ul>
			    <li>공지사항 여부</li>
			    <li>
			        <label class="label_notice"><em class="cbox"><input type="checkbox" name="n_fixed" value="N"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
			    </li>
			</ul>
			<ul>
			    <li>공지사항 제목</li>
			    <li>
			        <input type="text" class="notice_input1" name="n_subject"> ※ 최대 150자까지 입력이 가능
			    </li>
			</ul>
			<ul>
			    <li>글쓴이</li>
			    <li>
			        <input type="text" class="notice_input2" value="${adm_id}" name="adm_id" readonly> ※ 관리자 이름이 노출 됩니다.       
			    </li>
			</ul>
			<ul>
			    <li>첨부파일</li>
			    <li>
			        <input type="file" name="nfile" multiple="multiple" onchange="addFile(this)"> ※ 첨부파일 최대 용량은 2MB 입니다.       
			    </li>
			</ul>
			<ul class="ul_height">
			    <li>공지내용</li>
			    <li>
			        <textarea class="notice_input3" id="editor" name="n_contents" placeholder="공지내용을 입력하세요!"></textarea>
			    </li>
			</ul>
		</div>
		<div class="board_btn">
		    <button class="border_del" onclick="go_noticelist()">공지목록</button>
		    <input type="button" value="공지등록" class="border_add" onclick="notiwrite_submit()">
		</div>
	</section>
</form>
</main>
<%@ include file="/admin/layout/footer.jsp" %>​
</body>

<script type="text/javascript">
	//window.onload : 해당 웹페이지 실행시 즉시 발동시키는 함수
	window.onload = function(){ 
		//API로드방법
		var c = CKEDITOR.replace("editor",{ //들어갈 textarea id 입력
			width : 900,
			height : 400
		})
	}
</script>

</html>