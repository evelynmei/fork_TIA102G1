<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g1.staff.entity.Staff" %>
<%@ page import="com.tia102g1.staff.service.StaffServiceImpl" %>
 <%
	Staff staff = (Staff) request.getAttribute("staff");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/staff/staffCSS/mainPage.css">
<title>新增員工</title>
</head>
<body>
    <h1>員工基本資料</h1>
    <c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下問題:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
    <form action="${pageContext.request.contextPath}/staff/staff.do" method="post">
        <table style="width:50%; text-align:center;">
  

            <tr>
                <td>員工姓名:</td>
                <td>
                	<c:choose>
                		<c:when test="${staff ==null}">
                			<input type="text" name="name">
                		</c:when>
                		<c:otherwise>
                			<input type="text" name="name" value="${staff.name}">
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>
            <tr>
                <td>員工電話:</td>
                <td>
                	<c:choose>
                		<c:when test="${staff == null}">
                			<input type="text" name="phone">
                		</c:when>
                		<c:otherwise>
                			<input type="text" name="phone" value="${staff.phone}">
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>
            <tr>    
                <td>員工信箱:</td>
                <td>
                	<c:choose>
                		<c:when test="${staff == null}">
                			<input type="text" name="email">
                		</c:when>
                		<c:otherwise>
                			<input type="text" name="email" value="${staff.email}">
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>
            <tr>    
                <td>到職日期:</td>
                <td>
                	<c:choose>
                		<c:when test="${staff == null}">
                			<input type="date" name="employDt" >
                		</c:when>
                		<c:otherwise>
                			<input type="date" name="employDt" value="${staff.employDt}">
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>    
            <tr>    
                <td>創建者:</td>
                <td>
                	<c:choose>
                		<c:when test="${staff ==null}">
                			<input type="text" name="createdBy" >
                		</c:when>
                		<c:otherwise>
                			<input type="text" name="createdBy" value="${staff.createdBy}">
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>

        </table>
        <br>
        
        <input type="hidden" name="action" value="addStaff">
        <input type="submit" value="送出新增">
        <a href="${pageContext.request.contextPath}/staff/staffMainPage/mainPage.jsp">回首頁</a>
    </form>
</body>
</html>
