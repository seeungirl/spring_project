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
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/category.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
    <script src="./js/common.js"></script>
    <script src="./js/admin.js"></script>
</head>
<body>

<%@ include file="/admin/layout/admin_header.jsp" %>​

<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
   
   <cr:set var="arrlength" value="${fn:length(result)}" />
   <cr:forEach var="arr" items="${result}" varStatus="no"> 
		<cr:if test="${arrlength-1==0}">
			<ol class="new_admin_none">
			     <li>신규 등록된 관리자가 없습니다.</li>
			</ol>
		</cr:if>
		<cr:if test="${arrlength-1>0 && arr.adm_id!='master'}">
		    <ol class="new_admin_lists2">
		        <li>${arrlength-1-no.index}</li>
		        <li>${arr.adm_name}</li>
		        <li>${arr.adm_id}</li>
		        <li>${arr.adm_phone}</li>
		        <li>${arr.adm_email}</li>
		        <li>${arr.adm_position1}</li>
		        <li>${arr.adm_position2}</li>
		        <li>${fn:substring(arr.adm_date,0,10)}</li>
		        <li>
		        	<cr:if test="${arr.adm_aprove=='Y'}">
		            	<input type="button" value="승인" class="new_addbtn1" title="승인" onclick="change_aprove('${arr.adm_no}','N')">
		            </cr:if>
        		    <cr:if test="${arr.adm_aprove=='N'}">
		            	<input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="change_aprove('${arr.adm_no}','Y')">
		            </cr:if>
		        </li>
    		</ol>
		</cr:if>
   </cr:forEach>
   <div class="subpage_view4">
	    <span style="float: right;">
	    	<input type="button" value="신규 관리자 등록" title="신규 관리자 등록" class="p_button p_button_color2" onclick="admin_master()">
	    </span>
	</div>
</section>
</main>
<%@ include file="/admin/layout/footer.jsp" %>
</body>
</html>