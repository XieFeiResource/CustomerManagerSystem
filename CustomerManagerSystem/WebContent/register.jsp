<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<font color="red">
		<%= request.getAttribute("mess") == null ? "" : request.getAttribute("mess")%>
	</font>
<form action="<%= request.getContextPath() %>/Register.DI" method="post">
		用户名: <input type="text" name="name"/>
		<br><br>
		密&nbsp;&nbsp;&nbsp;&nbsp;码: <input type="text" name="password"/>
		<br><br>
		<input type="submit" value="注册"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置"/>
		<br><br>
		点此<a href="login.jsp">登录</a>
</form>
</center>
</body>
</html>