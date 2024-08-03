package com.tia102g1.event.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g1.event.model.EventService;
import com.tia102g1.event.model.EventVO;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	EventService eventSvc;
	
	//帶著VO物件,導向addEvent.html
	@GetMapping("addEvent")
	public String addEvent(ModelMap model) {
		EventVO eventVO = new EventVO();
		model.addAttribute("eventVO", eventVO);
		return "event/addEvent";
	}
	
	//新增
	@PostMapping("insert")
	public String insert(@Valid EventVO eventVO, BindingResult result, ModelMap model, 
			@RequestParam("eventPic") MultipartFile[] parts) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中eventPic欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(eventVO, result, "eventPic");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "活動圖片: 請上傳圖片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				eventVO.setEventPic(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			return "event/addEvent";
		}
		/*************************** 2.開始新增資料 *****************************************/
		
		eventVO.setLastUpdatedBy(eventVO.getCreatedBy());
		eventSvc.addEvent(eventVO);
		
//		try {
//			eventVO.setLastUpdatedBy(eventVO.getCreatedBy());
//			eventSvc.addEvent(eventVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<EventVO> list = eventSvc.getAll();
		model.addAttribute("EventListData", list);
		model.addAttribute("success", "- (新增成功)");
		
		return "redirect:/event/listAllEvent";
	}
	
	
	//查出指定VO物件,轉交給updateEvent.html做修改
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("eventId") String eventId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		
		//先把指定id的VO物件查出來並顯示,準備交給updateEvent頁面做修改
		EventVO eventVO = eventSvc.getOneEvent(Integer.valueOf(eventId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("eventVO", eventVO);
		
		return "event/updateEvent";
	}
	
	
	//修改
	@PostMapping("update")
	public String update(@Valid EventVO eventVO, BindingResult result, ModelMap model, 
			@RequestParam("eventPic") MultipartFile[] parts) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中eventPic欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(eventVO, result, "eventPic");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時,就取原有的圖片塞入
			byte[] eventPic = eventSvc.getOneEvent(eventVO.getEventId()).getEventPic(); //getOneEmp()返回VO物件, 再呼叫圖片屬性getter
			eventVO.setEventPic(eventPic); //然後setter放入當前VO物件中
		} else { //使用者有選擇要上傳的新圖片時
			for (MultipartFile multipartFile : parts) { //逐一取出
				byte[] eventPic = multipartFile.getBytes(); //轉為Bytes
				eventVO.setEventPic(eventPic); //把Bytes setter進當前VO物件的圖片屬性
			}
		}
		if (result.hasErrors()) { //錯誤訊息result
			return "event/updateEvent";
		}
		/*************************** 2.開始修改資料 *****************************************/
		
		eventSvc.updateEvent(eventVO); //把更新好屬性的當前VO物件交給Service層做update
		
//		try {
//			eventSvc.updateEvent(eventVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		eventVO = eventSvc.getOneEvent(Integer.valueOf(eventVO.getEventId())); //取出剛更新完的VO物件,顯示在前端頁面上
		model.addAttribute("eventVO", eventVO);
			
		return "event/listOneEvent";
	}
	
	
	//刪除
	@PostMapping("delete")
	public String delete(@RequestParam("eventId") String eventId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		
		eventSvc.deleteEvent(Integer.valueOf(eventId)); //執行刪除
		
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<EventVO> list = eventSvc.getAll(); //全部查詢,刪完後顯示最新的全部資料
		model.addAttribute("eventListData", list);
		model.addAttribute("success", "- (刪除成功)");
		
		return "event/listAllEvent";
	}
	
	
	// BindingResult 是 Spring 框架中的一個接口，用來存儲表單驗證的錯誤信息
	// 去除BindingResult中某個欄位的FieldError紀錄
	// 動態處理表單驗證錯誤
	// 返回的 BindingResult 會包含所有原本的錯誤信息，但移除了指定欄位名稱的錯誤
	public BindingResult removeFieldError(EventVO eventVO, BindingResult result, String removedFieldname) {
		// 取得需要保留的錯誤列表
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream() // 取得所有的FieldError //轉換成一個流來處理這些錯誤
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)) // 過濾掉欄位名稱等於 removedFieldname 的錯誤
				.collect(Collectors.toList()); // 將過濾後的結果收集成一個 List<FieldError>
		// 重新建立 BindingResult
		result = new BeanPropertyBindingResult(eventVO, "eventVO"); // 創建一個新的 BeanPropertyBindingResult 對象，綁定到 empVO

		// 將保留的錯誤加回到新的 BindingResult
		for (FieldError fieldError : errorsListToKeep) { // 將保留的錯誤逐個加回到新的 BindingResult 中
			result.addError(fieldError);
			System.out.println(fieldError);
		}
		return result;
	}
	
	
	//複合查詢
	@PostMapping("listEvents_ByCompositeQuery")
	public String listAllEvent(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap(); //getParameterMap()返回一個map
		//該map傳入getAll()會執行複合查詢CompositeQuery_XXX class的getAllC()方法,回傳查詢結果list
		List<EventVO> list = eventSvc.getAll(map);
		model.addAttribute("eventListData", list);
		
		return "event/listAllEvent";
	}
	

}
