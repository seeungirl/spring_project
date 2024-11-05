<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=20">
    <link rel="stylesheet" type="text/css" href="./css/mainlogin.css?v=30">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
    <script src="./js/common.js"></script>
    <script src="./js/admin.js"></script>
</head>
<body class="bodycss">
    <header class="admin_title">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
    </header>
    <form id="login_frm" onsubmit="return adminlogin_submit()">
    	<section class="admin_bgcolor">
	        <div class="admin_login">
	            <span>
	                <div class="left_div">
	                <ul>
	                	<li><input type="text" class="input_text1" name="adm_id" placeholder="관리자 아이디를 입력하세요"></li>
	                	<li><input type="password" class="input_text1" name="adm_pass" placeholder="관리자 패스워드를 입력하세요"></li>
	                </ul>
	                </div>
	                <div class="right_div">
	                    <button type="submit" class="btn_submit" title="MASTER LOGIN">MASTER LOGIN</button>
	                </div>
	                <em class="alert_msg">※ 본 사이트는 관리자 외에는 접근을 금합니다. 페이지 접근에 대한 접속 정보는 모두 기록 됩니다.</em>
	            </span>
	            <span>
	                <ol class="admin_info">
	                    <li title="신규 관리자 등록요청" onclick="admin_master()">신규 관리자 등록요청</li>
	                    <li title="아이디/패스워드 찾기" onclick="find_idpw()">아이디/패스워드 찾기</li>
	                </ol>                
	            </span>
	        </div>
	    </section>
    </form>
<%@ include file="/admin/layout/footer.jsp" %>
</body>
</html>