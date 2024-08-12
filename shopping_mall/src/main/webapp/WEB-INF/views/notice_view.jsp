<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
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
</head>
<body>
<%@ include file="/admin/layout/admin_header.jsp" %>​

<main class="maincss">
	<form id="noti_delete_frm">
		<input type="hidden" name="oneck" value="${result.n_no}">
		<input type="hidden" name="notick" value="${result.n_fixed}">
		<section>
		    <p>공지사항 확인 페이지</p>
			<div class="write_view">
				<ul>
				    <li>공지사항 제목</li>
    		        <cr:if test="${result.n_fixed == 'Y'}">
		        		<li><b class="notice">공지</b>${result.n_subject}</li>
		        	</cr:if>
  	    		    <cr:if test="${result.n_fixed == 'N'}">
		        		<li>${result.n_subject}</li>
		        	</cr:if>
				</ul>
				<ul>
				    <li>글쓴이</li>
				    <li>${result.adm_id}</li>
				</ul>
				<ul>
				    <li>첨부파일</li>
				    <li>
				       <cr:forEach var="file" items="${result.n_ori_img.split(',')}" varStatus="no">
				       		<span><a href="../noti_file_upload/${result.n_save_img.split(",")[no.index]}">${file}</a></span>
				       </cr:forEach>
				    </li>
				</ul>
				<ul class="ul_height">
				    <li>공지내용</li>
				    <li>
				        <div class="notice_input3" style="overflow-y: auto;">
				        ${result.n_contents}
				        </div>
				    </li>
				</ul>
			</div>
			<div class="board_btn">
			    <input type="button" class="border_del" onclick="go_noticelist()" value="공지목록">
			    <button class="border_add">공지수정</button>
			    <button class="border_modify" onclick="notice_delete('view')" style="margin-left: 8px;">공지삭제</button>
			</div>
		</section>
	</form>
</main>
<%@ include file="/admin/layout/footer.jsp" %>
</body>


</html>