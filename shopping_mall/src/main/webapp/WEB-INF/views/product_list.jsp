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
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
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
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 ${arrlength} 건</span>
    <span>
        <form id="prdlist_search_frm">
        <select class="p_select1"  id="search_select" name="search_select">
            <option value="1">상품명</option>
            <option value="2">상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="search_word">
        <input type="submit" value="검색" title="상품검색" class="p_submit" onclick="prdlist_search()">
        </form>
    </span>
</div>
<form id="prd_delete_frm">
	<div class="subpage_view2">
	    <ul>
	        <li><input type="checkbox"></li>
	        <li>코드</li>
	        <li>이미지</li>
	        <li>상품명</li>
	        <li>카테고리 분류</li>
	        <li>판매가격</li>
	        <li>할인가격</li>
	        <li>할인율</li>
	        <li>재고현황</li>
	        <li>판매유/무</li>
	        <li>품절</li>
	        <li>관리</li>
	    </ul>
	    <cr:if test="${arrlength==0}">
		    <ul>
		        <li style="width: 100%;">등록된 상품이 없습니다.</li>
		    </ul>
	    </cr:if>
	    <cr:forEach var="arr" items="${result}" varStatus="no">
	    <ul>
	        <li><input type="checkbox" name="prdck" value='${arr.p_no}'></li>
	        <li>${arr.p_code}</li>
	        <li class=thumbmail><img src="../prdimg_upload/${arr.p_thumb_img.split(",")[0]}"></li>
	        <li>${arr.p_name}</li>
	        <li>${arr.cate_name}</li>
	        <li>${arr.p_price}</li>
	        <li>${arr.p_dc_money}</li>
	        <li>${arr.p_dc_percent}%</li>
	        <li>${arr.p_stock}</li>
	        <li>${arr.p_use}</li>
	        <li>${arr.p_soldout}</li>
	        <li>관리</li>
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
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" onclick="go_prdwrite()">
    <input type="button" value="카테고리 리스트" title="카테고리 리스트" class="p_button p_button_color2" onclick="go_catelist()">
    </span>
</div>
</section>
</main>
<%@ include file="/admin/layout/footer.jsp" %>
</body>
</html>