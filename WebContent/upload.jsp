<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>批量导入试题</h1>
	<hr />
	<form action="question/import" method="post" enctype="multipart/form-data">
		<input type="file" name="upload" id="" />
		<button type="submit">上传</button>
	</form>
</body>
</html> 