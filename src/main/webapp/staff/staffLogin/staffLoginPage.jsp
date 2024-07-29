<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>員工登入</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下問題:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form action="${pageContext.request.contextPath}/staff/staff.do" method="post">
		<table border = 1>
			<tr>
				<td colspan = 2>
					<p align = center>
						輸入帳號密碼
				</td>
			</tr>
			
			<tr>
				<td>
					<p align = left>
						<b>員工編號:</b>
				</td>
				<td>
					<p>
						<input type = text name = "staffId" value = "" size = 15>
				</td>
			</tr>
			
			<tr>
				<td>
					<p align = left>
						<b>密碼:</b>
				</td>
				<td>
					<p>
						<input type = password name = "password" value = "" size = 15>
				</td>
			<tr>
				<td colspan = 2 align = center>
					<input type = "hidden" name = "action" value = "login">
					<input type = "hidden" name = "staffId" value = "${staff.staffId}">
					<input type = submit value = "ok">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>