package com.tia102g1.event.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g1.event.model.EventService;
import com.tia102g1.event.model.EventVO;

@WebServlet("/tia102g1/event/event.do")
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class EventServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//單筆查詢
		if("getOne_For_Display".equals(action)) { //來自select_page.jsp的請求
			//先準備裝錯誤訊息的集合
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); //錯誤訊息集合存進request屬性
			
			//接收請求參數
			String str = req.getParameter("eventId");
			
			//錯誤處理-未輸入資料
			if(str == null || (str.trim()).length() == 0) { //沒輸入資料
				errorMsgs.add("請輸入活動流水號");
			}
			//若有錯誤訊息,在原查詢頁面顯示
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/tia102g1/event/select_page.jsp");
				failureView.forward(req, res); //轉發原查詢頁面進行回應
				return; //程式中斷
			}
			
			//錯誤處理-格式非數字
			Integer eventId = null;
			try {
				eventId = Integer.valueOf(str);
			} catch(Exception e) {
				errorMsgs.add("活動流水號格式不正確");
			}
			//若有錯誤訊息,在原查詢頁面顯示
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/tia102g1/event/select_page.jsp");
				failureView.forward(req, res); //轉發原查詢頁面進行回應
				return; //程式中斷
			}
			
			//開始查詢資料
			EventService eventSvc = new EventService(); //其實就是DAO
			EventVO eventVO = eventSvc.getOneEvent(eventId); //DAO單筆查詢,回傳VO物件
			if(eventVO == null) {
				errorMsgs.add("查無資料");
			}
			//若有錯誤訊息,在原查詢頁面顯示
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/tia102g1/event/select_page.jsp");
				failureView.forward(req, res); //轉發原查詢頁面進行回應
				return; //程式中斷
			}
			
			//查詢完成(轉交查詢成功頁面)
			req.setAttribute("eventVO", eventVO); //查詢結果存進request屬性
			String url = "/tia102g1/event/listOneEvent.jsp"; //單筆查詢結果頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); //查詢成功轉交給查詢結果頁面
			successView.forward(req, res); //轉發查詢結果頁面進行回應
			
		}
		
		//全部查詢的單一修改按鈕
		if("getOne_For_Update".equals(action)) { //來自listAllEmp.jsp的請求(全部查詢的單一修改按鈕)
			//先準備裝錯誤訊息的集合
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); //錯誤訊息集合存進request屬性
			
			//接收請求參數
			Integer eventId = Integer.valueOf(req.getParameter("eventId"));
			
			//開始查詢資料
			EventService eventSvc = new EventService();
			EventVO eventVO = eventSvc.getOneEvent(eventId); //單筆查詢,回傳VO
			
			//查詢完成(轉交查詢成功頁面)
			req.setAttribute("eventVO", eventVO); //查詢結果存進request屬性
			String url = "/tia102g1/event/update_event_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //查詢成功轉交給查詢結果頁面
			successView.forward(req, res); //轉發查詢結果頁面進行回應
			
		}
		
		//修改活動資料
		if("update".equals(action)) { //來自update_event_input.jsp的請求(編輯頁面)
			//先準備裝錯誤訊息的集合
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); //錯誤訊息集合存進request屬性
			
			//接收請求參數
			Integer eventId = Integer.valueOf(req.getParameter("eventId").trim());
			
			//輸入欄位檢核-活動名稱
			String eventName = req.getParameter("eventName"); //活動名稱
			String eventNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,50}$"; //活動名稱字元與字數檢核
			if(eventName == null || eventName.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("活動名稱: 請勿空白");
			} else if (!eventName.trim().matches(eventNameReg)) { //若未符合字元與字數檢核
				errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在50字內");
			}
			
			//輸入欄位檢核-開始日期
			java.sql.Date startDt = null;
			try {
				startDt = java.sql.Date.valueOf(req.getParameter("startDt").trim());
			} catch(IllegalArgumentException e) {
				startDt = new java.sql.Date(System.currentTimeMillis()); //有錯就塞sysdate
				errorMsgs.add("請輸入活動開始日期!");
			}
			
			//輸入欄位檢核-結束日期
			java.sql.Date endDt = null;
			try {
				endDt = java.sql.Date.valueOf(req.getParameter("endDt").trim());
				//還未判斷結束日期不可早於開始日期
			} catch(IllegalArgumentException e) {
				endDt = new java.sql.Date(System.currentTimeMillis()); //有錯就塞sysdate
				errorMsgs.add("請輸入活動結束日期!");
			}
			
			//輸入欄位檢核-活動折數
			Double eventDiscount = null;
			try {
				eventDiscount = Double.valueOf(req.getParameter("eventDiscount").trim());
			} catch(NumberFormatException e) {
				eventDiscount = 0.00;
				errorMsgs.add("活動折數請輸入數值0.00");
			}
			
			//輸入欄位檢核-活動圖片
			InputStream in = req.getPart("eventPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的inputStream
			byte[] eventPic = null;
			if(in.available()!=0) {
				eventPic = new byte[in.available()];
				in.read(eventPic);
				in.close();
			} else {
				EventService eventSvc = new EventService();
				eventPic = eventSvc.getOneEvent(eventId).getEventPic(); //原圖
			}
			
			//輸入欄位檢核-活動狀態
			Integer status = Integer.valueOf(req.getParameter("status").trim());
			
			//輸入欄位檢核-活動內容
			String eventContent = req.getParameter("eventContent"); //活動內容
			if(eventContent == null || eventContent.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("活動內容: 請勿空白");
			}
			
			//輸入欄位檢核-最後更新者要從登入的帳號取,目前先用填的
			String lastUpdatedBy = req.getParameter("lastUpdatedBy"); //最後更新者
			if(lastUpdatedBy == null || lastUpdatedBy.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("最後更新者先用填的");
			}
			
			//將請求參數值放入setter成為物件的屬性
			EventVO eventVO = new EventVO();
			eventVO.setEventId(eventId);
			eventVO.setEventName(eventName);
			eventVO.setStartDt(startDt);
			eventVO.setEndDt(endDt);
			eventVO.setEventDiscount(eventDiscount);
			eventVO.setEventPic(eventPic);
			eventVO.setStatus(status);
			eventVO.setEventContent(eventContent);
			eventVO.setLastUpdatedBy(lastUpdatedBy);
			
			//若有錯誤訊息,就轉發回編輯畫面
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("eventVO", eventVO); //輸入格式錯誤的VO物件存入req,保留原輸入錯誤資料
				RequestDispatcher failureView = req.getRequestDispatcher("/tia102g1/event/update_event_input.jsp");
				failureView.forward(req, res); //轉發原查詢頁面進行回應
				return; //程式中斷
			}
			
			//開始修改資料(資料無格式錯誤)
			EventService eventSvc = new EventService();
			eventVO = eventSvc.updateEvent(eventId, eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, lastUpdatedBy);
			
			//修改完成(轉交單筆查詢結果頁面)
			req.setAttribute("eventVO", eventVO); //修改好的VO存入req屬性
			String url = "/tia102g1/event/listOneEvent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //查詢成功轉交給查詢結果頁面
			successView.forward(req, res); //轉發查詢結果頁面進行回應
			
		}
		
		//新增活動資料
		if("insert".equals(action)) { //來自addEvent.jsp的請求
			//先準備裝錯誤訊息的集合
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); //錯誤訊息集合存進request屬性
			
			//接收請求參數
			//輸入欄位檢核-活動名稱
			String eventName = req.getParameter("eventName"); //活動名稱
			String eventNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,50}$"; //活動名稱字元與字數檢核
			if(eventName == null || eventName.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("活動名稱: 請勿空白");
			} else if (!eventName.trim().matches(eventNameReg)) { //若未符合字元與字數檢核
				errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在50字內");
			}
			
			//輸入欄位檢核-開始日期
			java.sql.Date startDt = null;
			try {
				startDt = java.sql.Date.valueOf(req.getParameter("startDt").trim());
			} catch(IllegalArgumentException e) {
				startDt = new java.sql.Date(System.currentTimeMillis()); //有錯就塞sysdate
				errorMsgs.add("請輸入活動開始日期!");
			}
			
			//輸入欄位檢核-結束日期
			java.sql.Date endDt = null;
			try {
				endDt = java.sql.Date.valueOf(req.getParameter("endDt").trim());
				//還未判斷結束日期不可早於開始日期
			} catch(IllegalArgumentException e) {
				endDt = new java.sql.Date(System.currentTimeMillis()); //有錯就塞sysdate
				errorMsgs.add("請輸入活動結束日期!");
			}
			
			//輸入欄位檢核-活動折數
			Double eventDiscount = null;
			try {
				eventDiscount = Double.valueOf(req.getParameter("eventDiscount").trim());
			} catch(NumberFormatException e) {
				eventDiscount = 0.00;
				errorMsgs.add("活動折數請輸入數值0.00");
			}
			
			//輸入欄位檢核-活動圖片
			InputStream in = req.getPart("eventPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的inputStream
			byte[] eventPic = null;
			if(in.available()!=0) {
				eventPic = new byte[in.available()];
				in.read(eventPic);
				in.close();
			} else {
				errorMsgs.add("請上傳圖片");
			}
			
			//輸入欄位檢核-活動狀態
			Integer status = Integer.valueOf(req.getParameter("status").trim());
			
			//輸入欄位檢核-活動內容
			String eventContent = req.getParameter("eventContent"); //活動內容
			if(eventContent == null || eventContent.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("活動內容: 請勿空白");
			}
			
			//輸入欄位檢核-建檔者要從登入的帳號取,目前先用填的
			String createdBy = req.getParameter("createdBy"); //建檔者
			if(createdBy == null || createdBy.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("建檔者先用填的");
			}
			
			//輸入欄位檢核-最後更新者要從登入的帳號取,目前先用填的
			String lastUpdatedBy = req.getParameter("lastUpdatedBy"); //最後更新者
			if(lastUpdatedBy == null || lastUpdatedBy.trim().length() == 0) { //沒輸入內容
				errorMsgs.add("最後更新者先用填的");
			}
			
			//將請求參數值放入setter成為物件的屬性
			EventVO eventVO = new EventVO();
			eventVO.setEventName(eventName);
			eventVO.setStartDt(startDt);
			eventVO.setEndDt(endDt);
			eventVO.setEventDiscount(eventDiscount);
			eventVO.setEventPic(eventPic);
			eventVO.setStatus(status);
			eventVO.setEventContent(eventContent);
			eventVO.setCreatedBy(createdBy);
			eventVO.setLastUpdatedBy(lastUpdatedBy);
			
			//若有錯誤訊息,就轉發回編輯畫面
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("eventVO", eventVO); //輸入格式錯誤的VO物件存入req,保留原輸入錯誤資料
				RequestDispatcher failureView = req.getRequestDispatcher("/tia102g1/event/addEvent.jsp");
				failureView.forward(req, res); //轉發原查詢頁面進行回應
				return; //程式中斷
			}
			
			//開始修改資料(資料無格式錯誤)
			EventService eventSvc = new EventService();
			eventVO = eventSvc.addEvent(eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, createdBy, lastUpdatedBy);
			
			//修改完成(轉交全部查詢結果頁面)
			req.setAttribute("eventVO", eventVO); //修改好的VO存入req屬性
			String url = "/tia102g1/event/listAllEvent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //修改成功轉交給查詢結果頁面
			successView.forward(req, res); //轉發查詢結果頁面進行回應
			
		}
		
		//全部查詢的單一刪除
		if("delete".equals(action)) { //來自listAllEvent.jsp的請求
			//先準備裝錯誤訊息的集合
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); //錯誤訊息集合存進request屬性
			
			//接收請求參數
			Integer eventId = Integer.valueOf(req.getParameter("eventId").trim());
			
			//開始刪除資料
			EventService eventSvc = new EventService();
			eventSvc.deleteEvent(eventId);
			
			//刪除完成(轉交全部查詢結果頁面)
			String url = "/tia102g1/event/listAllEvent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
