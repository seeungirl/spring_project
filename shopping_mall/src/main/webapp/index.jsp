<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 50px;
	text-align: center;
}

table {
	width: 60%;
	margin: auto;
	border-collapse: collapse;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	overflow: hidden;
}

th, td {
	padding: 8px;
	text-align: center;
	border: 1px solid #ddd;
}

th {
	background-color: rgb(115, 79, 79);
	color: white;
}

button {
	width: 150px;
	height: 50px;
	background-color: rgb(115, 79, 79);
	border-radius: 5px;
	border: 0;
	color: white;
	font-size: 16px;
	cursor:pointer;
}
</style>
<body>
	<h1>쇼핑몰 관리 서비스</h1>
	<br>
	<br>
	<table>
		<thead>
			<tr>
				<th>구분</th>
				<th>아이디</th>
				<th>비밀번호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>최고 관리자</td>
				<td>master</td>
				<td>shop_master1234</td>
			</tr>
			<tr>
				<td>관리자</td>
				<td>test1234</td>
				<td>test1234</td>
			</tr>
		</tbody>
	</table>
	<br><br><br><br>
	<button onclick="openNewPage()">페이지 열기</button>
</body>
<script>
	function openNewPage() {
		window.open('/admin/index.jsp', '_blank');
	}

</script>
</html>