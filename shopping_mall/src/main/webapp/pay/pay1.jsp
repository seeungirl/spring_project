<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Random rd = new Random(); //Random class를 이용
	String pgcode = "";
	int a=0;
	while(a<6){
		pgcode += rd.nextInt(9);
		
		a++;
	}
	request.setAttribute("pgcode",pgcode);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pay - 상품 정보 및 갯수에 맞는 금액 설정</title>
</head>
<body>
	<form id="frm" action="./pay2.do" method="post" enctype="application/x-www-form-urlencoded">
		상품코드(숫자난수 최소 6 - 자동생성) : <input type="text" name="product_code" value="${pgcode}" readonly><br>
		상품명 : <input type="text" name="product_name" value="컴퓨터" readonly><br>
		상품금액 : <input type="text" name="product_money" value="10000" readonly ><br>
		상품수량 : 
		<select name="product_ea" onchange="select_ea(this.value)">
			<option value="1">1EA</option>
			<option value="2">2EA</option>
			<option value="3">3EA</option>
		</select><br>
		총 결제금액 : <input type="text" name="price" value="10000" readonly ><br><br>
		<input type="button" value="상품구매확정" onclick="basket()"> 
	</form>
</body>

<script type="text/javascript">
	function select_ea(z){
		var money = frm.product_money.value;
		var sum = z*money;
		frm.price.value = sum;
	}

	function basket(){
		frm.submit();
	}
</script>

</html>