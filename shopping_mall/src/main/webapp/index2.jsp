<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 연습</title>
</head>
<body>
<form id="frm">
	아이디 : <input type="text" name="mid"> 
	<input type="button" value="전송" onclick="post_data()">
</form>
</body>

<script type="text/javascript">
	function post_data(){
		frm.action = "./loginok.do";
		frm.method="post";
		frm.submit();
	}
</script>

</html>