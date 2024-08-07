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
    <title>상품수정 페이지</title>
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
	<form id="prd_modify_frm" enctype="multipart/form-data">
		<input type="hidden" value="${adm_id}" name="adm_id">
		<input type="hidden" value="Y" name="pd_ck">
		<input type="hidden" value="${result.p_no}" name="p_no">
		<section>
		<p>상품 등록 페이지</p>
		<div class="product_insert">
		    <ul>
		        <li>대메뉴 카테고리</li>
		        <li>
		            <select class="product_input1" name="cate_name" id="search_select" select_data="${result.cate_name}">
		           		<option value="">대메뉴 카테고리</option>
		            	<cr:forEach var="arr" items="${catelist}" varStatus="no">
		                	<option value="${arr.cate_name}">${arr.cate_name}</option>
		                </cr:forEach>
		            </select><input onclick="go_catelist()"  type="button" value="카테고리 등록" title="카테고리 등록" class="product_btn"> <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>상품코드</li>
		        <li>
		            <input type="text" class="product_input1" name="p_code" value="${result.p_code}" readonly> 
		            <span class="help_text" id="db_ck_text">※ 상품코드는 수정이 불가능합니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>상품명</li>
		        <li>
		            <input type="text" class="product_input2" maxlength="100" name="p_name" value="${result.p_name}"> <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>상품 부가설명</li>
		        <li>
		            <input type="text" name="p_summary" class="product_input4" maxlength="200" value="${result.p_summary}"> <span class="help_text">※ 상품 부가설명은 최대 200자까지만 적용할 수 있습니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>판매가격</li>
		        <li>
		            <input type="text" name="p_price" class="product_input3" maxlength="7" value="${result.p_price}" onkeyup="priceck(prd_modify_frm.p_dc_percent.value,prd_modify_frm)"> <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
		        </li>
		    </ul>
		    <ul>
		        <li>할인율</li>
		        <li>
		            <input type="text" name="p_dc_percent" class="product_input3" maxlength="2" value="${result.p_dc_percent}" onkeyup="priceck(this.value,prd_modify_frm)">% <span class="help_text">※ 숫자만 입력하세요</span>
		        </li>
		    </ul>
		    <ul>
		        <li>할인가격</li>
		        <li>
		            <input type="text" name="p_dc_money" class="product_input3" maxlength="7" value="${result.p_dc_money}" readonly> <span class="help_text">※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>상품재고</li>
		        <li>
		            <input type="text" name="p_stock" class="product_input3" maxlength="4" value="${result.p_stock}">EA <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>판매 유/무</li>
		        <li>
		            <label class="product_label">
		            	<input type="radio" name="p_use" value="Y" style="vertical-align:-1px;" data-radio='${result.p_use}'> 판매시작
		            </label>
		            <label class="product_label">
		            	<input type="radio" name="p_use" value="N" style="vertical-align:-1px;" data-radio='${result.p_use}'> 판매종료
		            </label> <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
		        </li>
		    </ul>
		    <ul>
		        <li>조기품절</li>
		        <li>
		            <label class="product_label">
		                <input type="radio" name="p_soldout" value="Y" style="vertical-align:-1px;" data-radio='${result.p_soldout}'> 사용
		            </label>
		            <label class="product_label">
		                <input type="radio" name="p_soldout" value="N" style="vertical-align:-1px;" data-radio='${result.p_soldout}'> 미사용
		            </label>
		        </li>
		    </ul>
		    <ul style="height: 160px;">
		        <li>상품 대표이미지</li>
		        <li>
		        	<ol class="img_area" id="img_area">
		        		<cr:forEach var="img" items="${result.p_thumb_img}" varStatus="no">
		        			<cr:set var="imgtxtlen" value="${fn:length(result.p_ori_img.split(',')[no.index])}" />
			        		<div data-no="${no.index}">
			        			<span class="img_box"><img src="../prdimg_upload/${img}"></span>	
			        			<a href="../prdimg_upload/${img}">
				        			<cr:if test="${imgtxtlen>=10}">...${fn:substring(result.p_ori_img.split(',')[no.index],imgtxtlen-10,imgtxtlen)}</cr:if>
				        			<cr:if test="${imgtxtlen<10}">${result.p_ori_img.split(',')[no.index]}</cr:if>
			        			</a>
								<input type="button" value="삭제하기" onclick="imgdel('${no.index}')">
			        		</div>
		        		</cr:forEach>
		        	</ol>
		            <ol style="width:100%; height: auto;">
			            <li style="width:100%; height:45px;">
				            <input type="file" name="files" value="${result.p_ori_img.split(',')[0]}">
				            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
			            </li>
			            <li style="height:45px;">
				            <input type="file" name="files" value="${result.p_ori_img.split(',')[1]}">
				            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
			            </li>
			            <li style="height:45px;">
				            <input type="file" name="files" value="${result.p_ori_img.split(',')[2]}">
				            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
			            </li>
		            </ol>
		        </li>
		    </ul>
		    <ul style="height: 400px;">
		        <li>상품 상세설명</li>
		        <li>
		            <textarea class="product_text1" name="p_desc">${result.p_desc}</textarea>
		        </li>
		    </ul>
		</div>
		<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
		    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;" onclick="go_prdlist()">
		    <input type="button" value="상품 수정" title="상품 수정" class="p_button p_button_color2" onclick="prd_insert('modify')">
		    </span>
		</div>
		</section>
	</form>
</main>
<%@ include file="/admin/layout/footer.jsp" %>
</body>

<script>
	cateprd_sc_ck();
	radioset();
	product_modify_imgck();
</script>

</html>