<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.service.StaffServiceImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>變更密碼</title>
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
						變更密碼
				</td>
			</tr>
			
			<tr>
				<td>
					<p align = left>
						<b>員工編號:</b>
				</td>
				<td>${staffId}</td>
			</tr>
			
			<tr>
				<td>
					<p align = left>
						<b>輸入舊密碼:</b>
				</td>
				<td>
					<p>
						<input type = password name = "pastPassword" value = "" size = 15>
				</td>
			</tr>
			
			<tr>
				<td>
					<p align = left>
						<b>輸入新密碼:</b>
				</td>
				<td>
					<p>
						<input type = password name = "newPassword">
				</td>
			</tr>
		
			<tr>
				<td colspan = 2 align = center>
					<input type = "hidden" name = "action" value = "changePW">
					<input type = submit value = "確認修改">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>