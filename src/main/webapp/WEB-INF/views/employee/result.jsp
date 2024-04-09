<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		let msg = '${msg}';
		alert(msg);
		console.log("path");
		location.href = '${path}';
	</script>
	
</body>
</html>