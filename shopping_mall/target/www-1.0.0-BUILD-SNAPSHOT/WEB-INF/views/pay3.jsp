<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String data1 = (String)request.getAttribute("goodcode");
String data2 = (String)request.getAttribute("price");

String mid = "INIpayTest";
String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";
String goodcode = data1; 
String mKey = SignatureUtil.hash(signKey, "SHA-256");
String timestamp = SignatureUtil.getTimestamp();
String orderNumber = mid+"_"+goodcode;	
String price = data2;

Map<String, String> signParam = new HashMap<String, String>();
signParam.put("oid", orderNumber);
signParam.put("price", price);
signParam.put("timestamp", timestamp);

/*out.print(goodcode+"+"+price+"<br>");
out.print(orderNumber+"<br>");
out.print(timestamp+"<br>");*/

String signature = SignatureUtil.makeSignature(signParam);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 및 결제 최종 정보 확정, 결제 API연동</title>
</head>
<body>
<form id="frm">
	<input type="hidden" name="version" value="1.0"> <!-- api 버전 -->
	<!-- 결제방식 -->
	<input type="hidden" name="gopaymethod" value="<%= (String)request.getAttribute("gopaymethod")%>">
	<!-- PG가입 아이디 -->
	<input type="hidden" name="mid" value="<%=mid%>">
	<!-- PG아이디 및 상품코드번호 -->
	<input type="hidden" name="oid" value="<%=orderNumber%>">
	<!-- 최종결제금액 -->
	<input type="hidden" name="price" value="<%=price%>">
	<!-- 결제를 진행해야하는 최장시간 -->
	<input type="hidden" name="timestamp" value="<%=timestamp%>">
	<!-- 결제사항에 대한 정보를 SHA-256으로 암호화한 코드 -->
	<input type="hidden" name="signature" value="<%=signature%>">
	<!-- 결제 비밀번호 key -->
	<input type="hidden" name="mKey" value="<%=mKey%>">
	<!-- 결제금액 단위(원) -->
	<input type="hidden" name="currency" value="WON">
	<!-- 결제자명 -->
	<input type="hidden" name="buyername" value="<%=request.getAttribute("buyername")%>">
	<!-- 결제자 연락처 -->
	<input type="hidden" name="buyertel" value="<%=request.getAttribute("buyertel")%>">
	<!-- 결제자 이메일 -->
	<input type="hidden" name="buyeremail" value="<%=request.getAttribute("buyeremail")%>">
	<!-- 상품명 -->
	<input type="hidden" name="goodname" value="<%=request.getAttribute("goodname")%>">
	<!-- 주소관련 -->
	<input type="hidden" name="rec_post" value="<%=request.getAttribute("rec_post")%>">
	<input type="hidden" name="rec_way" value="<%=request.getAttribute("rec_way")%>">
	<input type="hidden" name="rec_addr" value="<%=request.getAttribute("rec_addr")%>">
	<!-- 결제 성공및 실패에 대한 결과값 return 정보 -->
	<input type="hidden" name="returnUrl" value="http://localhost:8080/pay/return_url.jsp">
	<!-- 겨렟 취소시 사용되는 페이지 -->
	<input type="hidden" name="closeUrl" value="http://localhost:8080/pay/close.jsp">

	<p>-----------------상품 정보----------------</p>
	상품코드 : <%=request.getAttribute("goodcode") %> <br>
	상품명 : <%=request.getAttribute("goodname") %> <br>
	상품갯수 : <%=request.getAttribute("goodea") %> EA<br>
	
	<p>-----------------결제자 정보-----------------</p>
	결제자명 : <%=request.getAttribute("buyername") %> <br>
	연락처 : <%=request.getAttribute("buyertel") %> <br>
	이메일 : <%=request.getAttribute("buyeremail") %> <br>
	
	<p>-----------------배송 정보-----------------</p>
	수령 우편번호 : <%=request.getAttribute("rec_post") %> <br>
	도로명 주소 : <%=request.getAttribute("rec_way") %> <br>
	상세 주소 : <%=request.getAttribute("rec_addr") %> <br>
	
	<p>--------------결제 금액 및 수단--------------</p>
	최종 결제금액 : <%=request.getAttribute("price") %> <br>
	결제수단 : <%=request.getAttribute("gopaymethod") %> <br><br>
	
	<input type="button" value="결제하기" onclick="payok()"> 
</form>
</body>

<script src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js"></script>
<script type="text/javascript">
	function payok(){
		INIStdPay.pay("frm");
	}
</script>

</html>