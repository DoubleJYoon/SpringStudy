<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${vo.title}</h2>
	<div>${vo.writer}</div><br>
	<div>${vo.body}</div><br>
	<div>${vo.rdate}</div>
	<form action ="delete.do" method ="post">
		<input type ="hidden" name = "sno" value ="${vo.sno}">
		<input type ="submit" value ="삭제">
	</form>
</body>
</html>