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
		<%= session.getAttribute("message") == null ? "" : session.getAttribute("message")%>
	</font>
	<font color="red">
	<%= session.getAttribute("count") == null ? "" : "密码与账号不匹配，还有"+(3-(int)session.getAttribute("count"))+"次机会！"%>
	</font>
	<form action="<%= request.getContextPath() %>/Login.DI" method="post">
		用户名: <input type="text" name="name"/>
		<br><br>
		密&nbsp;&nbsp;&nbsp;&nbsp;码: <input type="text" name="password"/>
		<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;
		验证码: <input type="text" name="CHECK_CODE_PARAM_NAME"/>
		<img alt="" src="<%= request.getContextPath() %>/CodeServlet"> 
		<br><br>
		<input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置"/>
	</form>
	<br><br>
	若未注册，点此<a href="register.jsp">注册</a>
	</center>
</body>
</html>