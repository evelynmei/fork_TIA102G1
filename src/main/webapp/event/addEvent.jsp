<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g1.event.model.*"%>

<% //見com.event.controller.EventServlet.java第277行存入req的eventVO物件 (此為輸入格式有錯誤時的eventVO物件)
   EventVO eventVO = (EventVO) request.getAttribute("eventVO");
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>活動資料新增 - addEvent.jsp</title>

		<!-- header-css etc. -->
		<%@ include file="../comPage1.file"%>
		
		<style>
		  table#table-1 {
			background-color: #CCCCFF;
		    border: 2px solid black;
		    text-align: center;
		  }
		  table#table-1 h4 {
		    color: red;
		    display: block;
		    margin-bottom: 1px;
		  }
		  h4 {
		    color: blue;
		    display: inline;
		  }
		</style>
		
		<style>
		  table {
			width: 450px;
			background-color: white;
			margin-top: 1px;
			margin-bottom: 1px;
		  }
		  table, th, td {
		    border: 0px solid #CCCCFF;
		  }
		  th, td {
		    padding: 1px;
		  }
		</style>

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
              <h1 class="m-0">促銷活動</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="<%=URIPath%>index.jsp">首頁</a></li>
                <li class="breadcrumb-item "><a href="<%=URIPath%>event/select_page.jsp">促銷活動</a></li>
                <li class="breadcrumb-item active">新增活動資料</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->

	<!-- 功能內容開始 -->
	<section class="content">
		<table id="table-1">
			<tr><td>
				 <h3>活動資料新增 - addEvent.jsp</h3></td><td>
				 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
			</td></tr>
		</table>
		
		<h3>資料新增:</h3>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<FORM METHOD="post" ACTION="event.do" name="form1" enctype="multipart/form-data">
		<table>
			
			<tr>
				<td>活動名稱:</td>
				<td><input type="TEXT" name="eventName" value="<%= (eventVO==null)? "夏季特賣會" : eventVO.getEventName()%>" size="45"/></td>
			</tr>
			<tr>
				<td>活動開始日期:</td>
				<td><input type="TEXT" name="startDt" id="f_date1" ></td>
			</tr>
			<tr>
				<td>活動結束日期:</td>
				<td><input type="TEXT" name="endDt" id="f_date2" ></td>
			</tr>
			<tr>
				<td>活動折數:</td>
				<td><input type="TEXT" name="eventDiscount"   value="<%= (eventVO==null)? "9.0" : eventVO.getEventDiscount()%>" size="45"/></td>
			</tr>
			<tr>
				<td>活動圖片:</td>
				<td><input type="file" name="eventPic" multiple="multiple"/></td>
			</tr>
			<tr>
				<td>活動狀態:</td>
		<%-- 		<td><input type="TEXT" name="status"  value="<%= (eventVO==null)? "1" : eventVO.getStatus()%>"/></td> --%>		
				<td>
					<select name="status">
						<option value="0">待上架</option>
						<option value="1">進行中</option>
						<option value="2">已結束</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>活動文案內容:</td>
				<td><input type="TEXT" name="eventContent"  value="<%= (eventVO==null)? "請輸入活動內容" : eventVO.getEventContent()%>" size="45"/></td>
			</tr>
		<!-- 	<tr> -->
		<!-- 		<td>活動文案內容:</td> -->
		<!-- 		<td> -->
		<%-- 			<textarea name="eventContent"  value="<%= (eventVO==null)? "請輸入活動內容" : eventVO.getEventContent()%>" rows="4" cols="50"></textarea> --%>
		<!-- 		</td> -->
		<!-- 	</tr> -->
			<tr>
				<td>建檔者:</td>
				<td><input type="TEXT" name="createdBy"  value="<%= (eventVO==null)? "chris" : eventVO.getCreatedBy()%>" size="45"/></td>
			</tr>
			<tr>
				<td>最後更新者:</td>
				<td><input type="TEXT" name="lastUpdatedBy"  value="<%= (eventVO==null)? "chris" : eventVO.getLastUpdatedBy()%>" size="45"/></td>
			</tr>
		
		<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
		<!-- 	<tr> -->
		<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
		<!-- 		<td><select size="1" name="deptno"> -->
		<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
		<%-- 				<option value="${deptVO.deptno}" ${(eventVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
		<%-- 			</c:forEach> --%>
		<!-- 		</select></td> -->
		<!-- 	</tr> -->
		
		</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增"></FORM>
	
	</section>
	
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

	<% 
	  java.sql.Date startDt = null;
	  java.sql.Date endDt = null;
	  try {
		  startDt = eventVO.getStartDt();
		  endDt = eventVO.getEndDt();
	   } catch (Exception e) {
		   startDt = new java.sql.Date(System.currentTimeMillis());
		   endDt = new java.sql.Date(System.currentTimeMillis());
	   }
	%>
	<link rel="stylesheet" type="text/css" href="<%=URIPath%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=URIPath%>/datetimepicker/jquery.js"></script>
	<script src="<%=URIPath%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<style>
	  .xdsoft_datetimepicker .xdsoft_datepicker {
	           width:  300px;   /* width:  300px; */
	  }
	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	           height: 151px;   /* height:  151px; */
	  }
	</style>
	
	<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=startDt%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=endDt%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
	</script>
	<!-- 功能內容結束 -->
	
	<!-- footer and script link -->
	<%@ include file="../comPage3.file"%>

	</body>
</html>