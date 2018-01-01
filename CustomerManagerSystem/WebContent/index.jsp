<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定要是删除" + content + "的信息吗?");
			return flag;
		});
	});

</script>
</head>
<body>
	<center>
	<form action="query.do" method="post">
		<table>
			<tr>
				<td>客户姓名:</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>家庭住址:</td>
				<td><input type="text" name="address"/></td>
			</tr>
			<tr>
				<td>联系方式:</td>
				<td><input type="text" name="phone"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="查询"/></td>
				<td><a href="newcustomer.jsp">添加一个新用户</a></td>
			</tr>
		</table>
	</form>
	
	<br><br>
	
	<c:if test="${!empty requestScope.customers }">

		<hr>	
		<br><br>
	 
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>CustomerName</th>
				<th>Address</th>
				<th>Phone</th>
				<th>UPDATE\DELETE</th>
			</tr>
			
			<c:forEach items="${requestScope.customers }" var="cust">
					
					<tr>
						<td>${cust.id }</td>
						<td>${cust.name }</td>
						<td>${cust.address }</td>
						<td>${cust.phone }</td>
						<td>
							<c:url value="/edit.do" var="editurl">
								<c:param name="id" value="${cust.id }"></c:param>
							</c:url>
							<a href="${editurl }">UPDATE</a>
							<c:url value="/delete.do" var="deleteurl">
								<c:param name="id" value="${cust.id }"></c:param>
							</c:url>
							<a href="${deleteurl }" class="delete">DELETE</a>
						</td>
					</tr>
			
			</c:forEach>
			
		</table>
	</c:if>
	</center>
</body>
</html>