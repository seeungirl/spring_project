<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="/shop/css/index.css" rel="stylesheet" />
    <link href="/shop/css/subpage.css" rel="stylesheet" />
    <link href="/shop/css/agree.css?v=1" rel="stylesheet" />
    
    <script src="/admin/js/common.js"></script>
    <script src="/shop/js/shop.js"></script>
  </head>

  <body>
<%@ include file="/shop/layout/shop_header.jsp" %>
 <main>
    <div class="products">
      <h3>MEMBER_JOIN</h3>
      <div class="sub_view">
  
    	<div class="joinview">     
		    <form id="agree_frm">
		    	<input type="hidden" name="shopname" value="${shopname}">
		    	<input type="hidden" name="adm_id" value="${adm_id}">
		    	<h3>약관동의</h3>
				<div class="join_agreement_cont">
					<div class="join_agreement_box">
						<div class="form_element">
							<input type="checkbox" id="allck">
							<label class="check" for="allAgree"> <em>ShopBag의 모든 약관을 확인하고 전체 동의합니다.</em></label>
							<span>(전체동의, 선택항목도 포함됩니다.)</span>
						</div>
					</div>
					
					<div class="join_agreement_box js_terms_view">
						<div class="form_element">
							<input type="checkbox" id="termsAgree1" class="oneck require" value="N" name="m_agree_mail">
							<label class="check_s" for="termsAgree1"><strong>(필수)</strong> 이용약관</label>
							<span><a href="#" target="_blank">전체보기</a></span>
						</div>
						<div class="agreement_box" style="white-space:pre;">${term.term_use}</div>						
					</div>
					
					<div class="join_agreement_box js_terms_view">
						<div class="form_element">
							<input type="checkbox" id="termsAgree2" class="oneck require" value="N" name="m_agree_sms">
							<label class="check_s" for="termsAgree2"><strong>(필수)</strong> 개인정보 수집 및 이용 </label>
							<span><a href="#" target="_blank">전체보기</a></span>
						</div>
						<div class="agreement_box" style="white-space:pre;">${term.term_pinfo}</div>
					</div>
					
					<div class="important_check_box">
						<strong class="important_check dn">이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.</strong>
					</div>
					
				</div>
						
				<div class="btn_center_box">
					<input type="button" id="btnNextStep" class="btn_join" onclick="go_join()" value="다음단계">
	        	</div>
			</form>
        </div>
        
      </div>
    </div>
</main>
<%@ include file="/shop/layout/shop_footer.jsp" %>
  </body>
  
<script>
shop_del_ckbox_all();
</script>
  
</html>