<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g1.event.model.*"%>
<%@ page import="com.tia102g1.staff.entity.Staff" %>
<%@ page import="com.tia102g1.staff.service.StaffServiceImpl" %>
<%@ page import="java.util.*"%>

<%
	StaffServiceImpl svc = new StaffServiceImpl();
	List<Staff> allStaff = svc.getAllStaff();
	request.setAttribute("allStaff", allStaff);
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/tia102g1/staff/staffCSS/mainPage.css">
		<title>員工資料系統</title>

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
              <h1 class="m-0">Desserter 員工一覽</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item active">員工一覽</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
	<a href="${pageContext.request.contextPath}/tia102g1/staff/staff.do?action=getAll">查詢所有員工</a>
	<br>
	<a href="${pageContext.request.contextPath}/tia102g1/staff/staffPages/addStaffEX.jsp">新增員工</a>
	<form
		action="${pageContext.request.contextPath}/tia102g1/staff/staff.do"
		method="post">

		<p>
			<label>員工編號:</label> <select size="1" name="staffId">
				<c:forEach var="staff" items="${allStaff}">
					<option value="${staff.staffId}">${staff.staffId}</option>
				</c:forEach>
			</select>
		<input type="submit" value="送出">
		<input type="hidden" name="action" value="getById">
		</p>
		</form>
		<hr>
		<form
		action="${pageContext.request.contextPath}/tia102g1/staff/staff.do"
		method="post">
		<p>
			<label>到職日期區間:</label> <input type="date" name="startemploydate">
			~ <input type="date" name="endemploydate"><br>
		<p>
			<label>是否還在職:</label> <select name="status">
				<option value="1">在職</option>
				<option value="0">已離職</option>
			</select>
		<p>
			<input type="submit" value="送出">
		</p>
			<input type="hidden" name="action" value="compositeQuery">
	</form>
	
	</section>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../../comPage3.file"%>

	</body>
</html>