<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 리스트 출력 페이지</title>
<%
//자동갱신
Date today = new Date();
%>
<script src="./coupon_list.js?v=<%=today%>"></script>
</head>
<body>
	<p>쿠폰리스트 총 게시물 : <span id="total"></span>개</p>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>쿠폰명</th>
				<th>쿠폰 할인율</th>
				<th>사용가능여부</th>
				<th>만료기한</th>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
	
	<!-- 페이지 번호 출력 -->
	<table>
		<tr id="pages">
			
		</tr>
	</table>
</body>
</html>