<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pay-고객정보 입력 및 결제금액 정보 세팅</title>
</head>
<body>
<form id="frm" method="post" action="./pay3.do" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="goodcode" value="${stringList[0]}">
	<input type="hidden" name="goodname" value="${stringList[1]}">
	<input type="hidden" name="goodea" value="${stringList[3]}">
	<input type="hidden" name="goodprice" value="${stringList[2]}">
	<input type="hidden" name="price" value="${stringList[4]}">

	상품 코드 : ${stringList[0]}<br>
	상품명 : ${stringList[1]}<br>
	상품가격 : ${stringList[2]}원<br>
	상품갯수 : ${stringList[3]}EA<br>
	총결제금액 : <fn:formatNumber value="${stringList[4]}" pattern="#,###" />원<br>

	<p>[ 고객정보 입력 ]<p>
	고객명 : <input type="text" name="buyername"><br>
	연락처 : <input type="text" name="buyertel" maxlength="11"> * "-",숫자만 입력<br>
	이메일 : <input type="text" name="buyeremail"> * 실제 이메일 주소<br>
	주소 : <input type="text" name="rec_post" maxlengt="5" value="11703"><br>
	<input type="text" name="rec_way" placeholder="도로명주소" value="경기도 의정부시 회룡로 149번길"><br>
	<input type="text" name="rec_addr" placeholder="상세주소" value="3004호"><br>
	<br>
	
	<p>[ 결제방식 ]</p>
	<input type="radio" name="gopaymethod" value="Card" checked>신용카드
	<input type="radio" name="gopaymethod" value="Directbank">계좌이체
	<input type="radio" name="gopaymethod" value="VBank">가상계좌 
	
	<br><br>
	<input type="button" value="결제선택완료" onclick="gopay()">
</form>
</body>

<script type="text/javascript">
	function gopay(){
		frm.submit();
	}
</script>

</html>