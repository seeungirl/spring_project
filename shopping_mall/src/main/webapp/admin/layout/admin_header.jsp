<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession hs = request.getSession();
String adm_id = (String)hs.getAttribute("adm_id");
%>
    
<header class="headercss">
    <div class="header_div">
        <p onclick="admin_main()"><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p>
        	<% if(adm_id!=null){ %>
        	${adm_id} <a href="#">[개인정보 수정]</a> <a href="javascript:void admin_logout()">[로그아웃]</a>
        	<% } %>
        </p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
        	<%
        	if(adm_id!=null){
        		if(adm_id.equals("master")){ 
        	%>
            	<li title="쇼핑몰 관리자 리스트"><a href='./admin_list.do'>쇼핑몰 관리자 리스트</a></li>
            <% 
            	} 
            %>
            	<li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
				<li title="쇼핑몰 상품관리"><a href='./product_list.do'>쇼핑몰 상품관리</a></li>
            	<li title="쇼핑몰 기본설정"><a href='./admin_siteinfo.do'>쇼핑몰 기본설정</a></li>
        	<%
            } 
            %>
        </ol>
    </div>
</nav>