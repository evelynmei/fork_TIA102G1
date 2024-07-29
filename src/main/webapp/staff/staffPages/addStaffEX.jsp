<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.staff.entity.Staff" %>
<%@ page import="com.staff.service.StaffServiceImpl" %>
 <%
	Staff staff = (Staff) request.getAttribute("staff");
%>


<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>新增員工</title>

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
              <h1 class="m-0">員工基本資料</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item active">新增員工</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
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
        <a href="${pageContext.request.contextPath}/staff/staffMainPage/mainPageEX.jsp">返回員工首頁</a>
    </form>
	
	</section>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../../comPage3.file"%>

	</body>
</html>