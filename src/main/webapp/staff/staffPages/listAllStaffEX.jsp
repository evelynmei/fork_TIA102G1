<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/staff/staffCSS/mainPage.css">
		<title>員工列表</title>

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
              <h1 class="m-0">員工列表</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item active">員工列表</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
		<c:if test="${staffPageQty > 0}">
			<b><font color=red>第${currentPage}/${staffPageQty}頁</font></b>
		</c:if>
		<br>
		<table style="width:50%; text-align:center;">
			<tr>
				<th>員工編號</th>
				<th>員工姓名</th>
				<th>員工電話</th>
				<th>電子信箱</th>
				<th>到職日</th>
				<th>是否仍在職</th>
				<th>離職日</th>
				<th>修改</th>
			</tr>
			<c:forEach var="staff" items="${staffList}">
				<tr>
					<td>${staff.staffId}</td>
					<td>${staff.name}</td>
					<td>${staff.phone}</td>
					<td>${staff.email}</td>
					<td>${staff.employDt}</td>
					 <td>
					 <c:choose>
	                    <c:when test="${staff.status == 1}">
	                        在職
	                    </c:when>
	                    <c:otherwise>
	                        已離職
	                    </c:otherwise>
	                </c:choose>
	                </td>
					<td>${staff.leaveDt}</td>
					
					<td>
						<form action="${pageContext.request.contextPath}/staff/staff.do" method="post">
						<input type="submit" value="修改">
						<input type="hidden" name="staffId" value="${staff.staffId}">
						<input type="hidden" name="action" value="get_one_update">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/staff/staff.do?action=getAll&page=1">至第一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage - 1 != 0}">
			<a href="${pageContext.request.contextPath}/staff/staff.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage + 1 <= staffPageQty}">
			<a href="${pageContext.request.contextPath}/staff/staff.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage != staffPageQty}">
			<a href="${pageContext.request.contextPath}/staff/staff.do?action=getAll&page=${staffPageQty}">至最後一頁</a>&nbsp;
		</c:if>
		
		<a href="${pageContext.request.contextPath}/staff/staffMainPage/mainPageEX.jsp">返回員工首頁</a>
		
	</section>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../../comPage3.file"%>

	</body>
</html>