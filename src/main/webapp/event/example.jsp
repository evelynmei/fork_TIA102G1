<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>功能名稱</title>

		<!-- header-css etc. -->
		<%@ include file="../comPage1.file"%>

	</head>
	
	<body class="hold-transition sidebar-mini layout-fixed">

	<!-- side bar -->
	<%@ include file="../comPage2.file"%>
	
	<!-- Content Wrapper. Contains page content -->
    <!-- 儀錶板上面那排 -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">功能名稱</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item active">功能名稱</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
	
	
	</section>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../comPage3.file"%>

	</body>
</html>