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
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=6">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
    <script src="./js/common.js"></script>
    <script src="./js/product.js"></script>
</head>
<body>
<%@ include file="/admin/layout/admin_header.jsp" %>​

<main class="maincss">
	<cr:set var="arrlength" value="${fn:length(result)}" />
	<section>    
	<p>카테고리관리 페이지</p>
	<div class="subpage_view">
	    <span>등록된 카테고리 ${arrlength}건</span>
	    <span>
	        <form>
		        <select class="p_select1">
		            <option>카테고리명</option>
		            <option>카테고리코드</option>
		        </select>
		        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
		        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
	        </form>
	    </span>
	</div>
	<form id="cate_delete_frm">
		<div class="subpage_view2">
		    <ul>
		        <li><input type="checkbox" id="allck"></li>
		        <li>분류코드</li>
		        <li>대메뉴 코드</li>
		        <li>대메뉴명</li>
		        <li>소메뉴 코드(사용안함)</li>
		        <li>소메뉴명(사용안함)</li>
		        <li>사용 유/무</li>
		        <li>관리</li>
		    </ul>
		    <cr:if test="${arrlength==0}">
			    <ul>
			        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
			    </ul>
		    </cr:if>
		    <cr:forEach var="arr" items="${result}" varStatus="no">
			    <ul>
			        <li><input type="checkbox" name="cateck" value='${arr.c_no}'></li>
			        <li style="text-align: left; text-indent: 5px;">${arr.group_code}</li>
			        <li>${arr.cate_code}</li>
			        <li style="text-align: left; text-indent: 5px;">${arr.cate_name}</li>
			        <li>-</li>
			        <li style="text-align: left; text-indent: 5px;">-</li>
			        <li>${arr.cate_use}</li>
			        <li><a href="javascript:void cate_gomodify('${arr.c_no}')" style="color : #333">[수정]</a></li>
			    </ul>
		    </cr:forEach>
		</div>
	</form>
	
	<div class="subpage_view3">
	    <ul class="pageing">
	        <li><img src="./ico/double_left.svg"></li>
	        <li><img src="./ico/left.svg"></li>
	        <li>1</li>
	        <li><img src="./ico/right.svg"></li>
	        <li><img src="./ico/double_right.svg"></li>
	    </ul>
	</div>
	<div class="subpage_view4">
	    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" onclick="category_delete()">
	    <span style="float: right;">
	    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" onclick="go_prdlist()">
	    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="go_catewrite()">
	    </span>
	</div>
	</section>
</main>
<%@ include file="/admin/layout/footer.jsp" %>
</body>

<script>
category_del_ck();
</script>

</html>