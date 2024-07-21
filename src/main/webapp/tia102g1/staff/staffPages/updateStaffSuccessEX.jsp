<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g1.event.model.*"%>
<%@ page import="com.tia102g1.staff.entity.Staff" %>
<%@ page import="com.tia102g1.staff.service.StaffServiceImpl" %>
<%
	Staff staffList = (Staff) request.getAttribute("staffList");
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>修改員工成功</title>

		<!-- header-css etc. -->
		<%@ include file="../../comPage1.file"%>

	</head>
	
	<body class="hold-transition sidebar-mini layout-fixed">

	<!-- side bar -->
	<%@ include file="../../comPage2.file"%>
	
	<!-- Content Wrapper. Contains page content -->
    <!-- 儀錶板上面那排 -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">修改員工成功</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item active">修改員工成功</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
		<p>員工資料:</p>
		<table border="1">
		<%-- 	<c:forEach var="staff" items="${updateStaff}"> --%>
			
		    <tr><td>員工編號</td><td>${staff.staffId}</td></tr>
		<%--     <tr><td>預設密碼</td><td>${staff.password}</td></tr> --%>
		    <tr><td>姓名</td><td>${staff.name}</td></tr>
		    <tr><td>電話</td><td>${staff.phone}</td></tr>
		    <tr><td>電子郵件</td><td>${staff.email}</td></tr>
		    <tr><td>入職日期</td><td>${staff.employDt}</td></tr>
		    <tr><td>建立者</td><td>${staff.createdBy}</td></tr>
			<!--     ======================================================== -->
		    <tr><td>建立日期</td>
		    <td><fmt:formatDate value="${staff.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
		    <!--     ======================================================== -->
		    <tr><td>最後更新者</td><td>${staff.lastUpdatedBy}</td></tr>
		    <!--     ======================================================== -->
		    <tr><td>最後更新日期</td>
		    <td><fmt:formatDate value="${staff.lastUpdated}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
		    <!--     ======================================================== -->
		<%--     </c:forEach> --%>
		</table>
		<a href="${pageContext.request.contextPath}/tia102g1/staff/staff.do?action=getAll">返回列表</a>
		<a href="${pageContext.request.contextPath}/tia102g1/staff/staffMainPage/mainPageEX.jsp">返回員工首頁</a>
	
	</section>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../../comPage3.file"%>

	</body>
</html>