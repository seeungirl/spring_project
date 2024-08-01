<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="./css/subpage.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    
	<script src="./js/jquery.js"></script>
	<script src="./js/common.js"></script>
	<script src="./js/admin.js"></script>
</head>
<body id="siteinfo">

<%@ include file="/admin/layout/admin_header.jsp" %>​

<main class="maincss">

<form id="ms_frm">
	<input type="hidden" value="${adm_id}" name="adm_id">
	<section>
	    <p>홈페이지 가입환경 설정</p>
		<div class="subpage_view">
			<ul class="info_form">
			    <li>홈페이지 제목</li>
			    <li>
			        <input type="text" value="${mj.ms_mallname}" class="in_form1" id="" name="ms_mallname" data-rq="true"> 
			    </li>
			</ul>    
			<ul class="info_form">
			    <li>관리자 메일 주소</li>
			    <li>
			        <input type="text" value="${mj.ms_admemail}" class="in_form2" name="ms_admemail" data-rq="true"> 
			        ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
			    </li>
			</ul> 
			<ul class="info_form">
			    <li>포인트 사용 유/무</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pointuse" value="Y" data-radio='${mj.ms_pointuse}'>포인트 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pointuse" value="N" data-radio='${mj.ms_pointuse}'>포인트 미사용</label></em>
			    </li>
			</ul>
			<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
			    <li>회원가입시 적립금</li>
			    <li>
			        <input type="text" class="in_form3" maxlength="5" name="ms_joinpoint" value="${mj.ms_joinpoint}" data-rq="true" data-isnan="true"> 점
			    </li>
			    <li>회원가입시 권한레벨</li>
			    <li>
			        <input type="text" class="in_form3" maxlength="1" name="ms_joinlevel" value="${mj.ms_joinlevel}" data-rq="true"> 레벨
			    </li>
			</ul> 
		</div>
		
		<p>홈페이지 기본환경 설정</p>
		<div class="subpage_view">
			<ul class="info_form2">
			    <li>회사명</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_cpname" data-rq="true" value="${mb.ms_cpname}"> 
			    </li>
			    <li>사업자등록번호</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_cpnum" data-rq="true" value="${mb.ms_cpnum}"> 
			    </li>
			</ul> 
			<ul class="info_form2">
			    <li>대표자명</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_cpceoname" data-rq="true" value="${mb.ms_cpceoname}"> 
			    </li>
			    <li>대표전화번호</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_cpceophone" data-rq="true" value="${mb.ms_cpceophone}"> 
			    </li>
			</ul>
			<ul class="info_form2">
			    <li>통신판매업 신고번호</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_reportnum" value="${mb.ms_reportnum}"> 
			    </li>
			    <li>부가통신 사업자번호</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_addcpnum" value="${mb.ms_addcpnum}"> 
			    </li>
			</ul>
			<ul class="info_form2">
			    <li>사업장 우편번호</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_cppost" data-rq="true" maxlength="5" value="${mb.ms_cppost}"> 
			    </li>
			    <li>사업장 주소</li>
			    <li>
			        <input type="text" class="in_form2" name="ms_cpaddress" data-rq="true" value="${mb.ms_cpaddress}">
			    </li>
			</ul>
			<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
			    <li>정보관리책임자명</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_ifofficername" data-rq="true" value="${mb.ms_ifofficername}"> 
			    </li>
			    <li>정보책임자 E-mail</li>
			    <li>
			        <input type="text" class="in_form1" name="ms_ifofficeremail" data-rq="true" value="${mb.ms_ifofficeremail}"> 
			    </li>
			</ul>
		</div>
		
		
		<p>결제 기본환경 설정</p>
		<div class="subpage_view">  
			<ul class="info_form2">
			    <li>무통장 은행</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_pay_bank" value="${mp.ms_pay_bank}"> 
			    </li>
			    <li>은행계좌번호</li>
			    <li>
			        <input type="text" class="in_form1" name="ms_pay_banknum" data-isnan="true" value="${mp.ms_pay_banknum}"> 
			    </li>
			</ul>
			<ul class="info_form">
			    <li>신용카드 결제 사용</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pay_credituse" value="Y" data-radio='${mp.ms_pay_credituse}'> 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pay_credituse" value="N" data-radio='${mp.ms_pay_credituse}'> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
			    </li>
			</ul>
			<ul class="info_form">
			    <li>휴대폰 결제 사용</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pay_phoneuse" value="Y" data-radio='${mp.ms_pay_phoneuse}'> 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pay_phoneuse" value="N" data-radio='${mp.ms_pay_phoneuse}'> 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
			    </li>
			</ul>
			<ul class="info_form">
			    <li>도서상품권 결제사용</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pay_bookgiftcarduse" value="Y" data-radio='${mp.ms_pay_bookgiftcarduse}'> 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pay_bookgiftcarduse" value="N" data-radio='${mp.ms_pay_bookgiftcarduse}'> 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
			    </li>
			</ul>
			<ul class="info_form2">
			    <li>결제 최소 포인트</li>
			    <li>
			        <input type="text" class="in_form0" maxlength="5" name="ms_pay_minpoint" data-rq="true" data-isnan="true" value="${mp.ms_pay_minpoint}"> 점
			    </li>
			    <li>결제 최대 포인트</li>
			    <li>
			        <input type="text" class="in_form0" maxlength="5" name="ms_pay_maxpoint" data-rq="true" data-isnan="true" value="${mp.ms_pay_maxpoint}"> 점
			    </li>
			</ul>
			<ul class="info_form">
			    <li>현금 영수증 발급사용</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pay_cashreceipt" value="Y" data-radio='${mp.ms_pay_cashreceipt}'> 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pay_cashreceipt" value="N" data-radio='${mp.ms_pay_cashreceipt}'> 미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
			    </li>
			</ul>
			<ul class="info_form2">
			    <li>배송업체명</li>
			    <li>
			        <input type="text" class="in_form0" name="ms_pay_deliveryname" data-rq="true" value="${mp.ms_pay_deliveryname}"> 
			    </li>
			    <li>배송비 가격</li>
			    <li>
			        <input type="text" class="in_form0" maxlength="7" name="ms_pay_deliveryprice" data-rq="true" data-isnan="true" value="${mp.ms_pay_deliveryprice}"> 원
			    </li>
			</ul>
			<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
			    <li>희망배송일</li>
			    <li class="checkcss">
			        <em><label><input type="radio" class="ckclass" name="ms_pay_deliverydate" value="Y" data-radio='${mp.ms_pay_deliverydate}'> 사용</label></em> 
			        <em><label><input type="radio" class="ckclass" name="ms_pay_deliverydate" value="N" data-radio='${mp.ms_pay_deliverydate}'> 미사용</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
			    </li>
			</ul>
		</div>
		<div class="btn_div">
		    <button type="button" class="sub_btn1" title="설정 저장" onclick="javascript:void siteinfo_submit(event)">설정 저장</button>
		    <button type="button" class="sub_btn2" title="저장 취소" onclick="javascript:void cancel()">저장 취소</button>
		</div>
	</section>

</form>

</main>
<%@ include file="/admin/layout/footer.jsp" %>

</body>

<script>
	radioset();
</script>

</html>