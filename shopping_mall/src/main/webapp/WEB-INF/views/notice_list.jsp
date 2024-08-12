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
    <title>공지사항 리스트 페이지</title>
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
<cr:set var="arrlength" value="${fn:length(result)}" />
<form id="noti_delete_frm">
	<section>
	    <p>공지사항 관리페이지</p>
	    <div class="subpage_view">
		    <ul>
		        <li><input type="checkbox" id="allck"></li>
		        <li>NO</li>
		        <li>제목</li>
		        <li>글쓴이</li>
		        <li>날짜</li>
		        <li>조회</li>
		    </ul>
	    
		   	<cr:if test="${arrlength==0}">
		        <ol class="none_text">
		        	<li>등록된 공지 내용이 없습니다.</li>
		    	</ol>
		    </cr:if>
	    
		    <cr:set var="ino" value="${total-startpg}" />
		    <cr:forEach var="arr" items="${result}" varStatus="no">
		    <ol>
		        <li><input type="checkbox" name="oneck" value='${arr.n_no}'></li>
		        <cr:if test="${arr.n_fixed == 'Y'}">
		        	<li><b class="notice">공지</b></li>
		        </cr:if>
		         <cr:if test="${arr.n_fixed == 'N'}">
		        	<li>${ino-no.index}</li>
		        </cr:if>
		        <li><a href="./notice_view.do?no=${arr.n_no}">${arr.n_subject}</a></li>
		        <li>${arr.adm_id}</li>
		        <li>${arr.n_indate}</li>
		        <li>${arr.n_viewcount}</li>
		    </ol>
		    </cr:forEach>
	    </div>
	    
	    <div class="board_btn">
	        <button class="border_del" onclick="notice_delete('list')">공지삭제</button>
	        <input type="button" class="border_add" onclick="go_noticewrite()" value="공지등록">
	    </div>
	    <div class="border_page">
	        <ul class="pageing">
	            <li><img src="./ico/double_left.svg"></li>
	            <li><img src="./ico/left.svg"></li>
	            <cr:set var="pg" value="${total/10+(1-((total/10)%1))%1}" />
	            <cr:forEach var="no" begin="1" end="${pg}" step="1">
	            	<li><a style="color : #333" href="./notice_list.do?page=${no}">${no}</a></li>
	            </cr:forEach>
	            <li><img src="./ico/right.svg"></li>
	            <li><img src="./ico/double_right.svg"></li>
	        </ul>
	    </div>
	</section>
</form>
</main>
<%@ include file="/admin/layout/footer.jsp" %>​
</body>
<script>
del_ckbox_all();
</script>

</html>