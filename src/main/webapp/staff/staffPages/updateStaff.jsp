<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.entity.Staff"%>
<%@ page import="com.staff.service.StaffServiceImpl"%>
<%
Staff staff = (Staff) request.getAttribute("staff");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/staff/staffCSS/mainPage.css">
<title>員工資料修改</title>
</head>
<body>
	<h1>員工資料修改</h1>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下問題:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form
		action="${pageContext.request.contextPath}/staff/staff.do"
		method="post">
		<table style="width: 50%; text-align: center;">
			<c:forEach var="staff" items="${staffList}">
				<tr>
					<td>員工編號:</td>
					<td>${staff.staffId}</td>
				</tr>

				<input type="hidden" name="password" value="${staff.password}">

				<tr>
					<td>員工姓名:</td>
					<td><c:choose>
							<c:when test="${staff ==null}">
								<input type="text" name="name">
							</c:when>
							<c:otherwise>
								<input type="text" name="name" value="${staff.name}">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>員工電話:</td>
					<td><c:choose>
							<c:when test="${staff == null}">
								<input type="text" name="phone">
							</c:when>
							<c:otherwise>
								<input type="text" name="phone" value="${staff.phone}">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>員工信箱:</td>
					<td><c:choose>
							<c:when test="${staff == null}">
								<input type="text" name="email">
							</c:when>
							<c:otherwise>
								<input type="text" name="email" value="${staff.email}">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<input type="hidden" name="employDt" value="${staff.employDt}">
				<tr>
					<td>到職日期:</td>
					<td><c:choose>
							<c:when test="${staff == null}">
								<input type="date" name="leaveDt">
							</c:when>
							<c:otherwise>
								<input type="date" name="leaveDt" value="${staff.leaveDt}">
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>在職狀態:</td>
					<td><c:choose>
							<c:when test="${staff == null}">
								<select name="status">
									<option value="1">在職</option>
									<option value="0">已離職</option>
								</select>
							</c:when>
							<c:otherwise>
								<select name="status">
									<option value="1" ${staff.status == 1 ? "selected" : ""}>在職</option>
									<option value="0" ${staff.status == 0 ? "selected" : ""}>已離職</option>
								</select>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<input type="hidden" name="createdBy" value="${staff.createdBy}">
				<input type="hidden" name="dateCreated" value="${staff.dateCreated}">
				<tr>
					<td>最後更新者:</td>
					<td><input type="text" name="lastUpdatedBy"
						value="${staff.lastUpdatedBy}"></td>
				</tr>
				<input type="hidden" name="lastUpdated" value="${staff.lastUpdated}">
				<input type="hidden" name="staffId" value="${staff.staffId}">

			</c:forEach>
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="staffId" value="${staff.staffId}"> 
		<input type="submit" value="確認修改">
	</form>
	<br>
	<a
		href="${pageContext.request.contextPath}/staff/staffMainPage/mainPage.jsp">回首頁</a>
</body>
</html>