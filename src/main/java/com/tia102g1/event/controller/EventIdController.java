package com.tia102g1.event.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g1.event.model.EventService;
import com.tia102g1.event.model.EventVO;

@Controller
@Validated
@RequestMapping("/event")
public class EventIdController {
	
	@Autowired
	EventService eventSvc;
	
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="活動編號: 請勿空白")
		@RequestParam("eventId") String eventId,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
		
		EventVO eventVO = eventSvc.getOneEvent(Integer.valueOf(eventId));
		
		List<EventVO> list = eventSvc.getAll();
		model.addAttribute("eventListData", list);     // for mainPageEvent.html 第97 109行用
		
		if (eventVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "event/mainPageEvent";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("eventVO", eventVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見mainPageEvent.html的第156行 -->
		
//		return "back-end/event/listOneEvent";  // 查詢完成後轉交listOneEvent.html
		return "event/mainPageEvent"; // 查詢完成後轉交select_page.html由其第158行insert listOneEvent.html內的th:fragment="listOneEvent-div
	}
	
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   
//	    model.addAttribute("empVO", new EmpVO());
//    	EmpService empSvc = new EmpService();
		List<EventVO> list = eventSvc.getAll();
		model.addAttribute("eventListData", list);     // for select_page.html 第97 109行用
		String message = strBuilder.toString();
	    return new ModelAndView("event/mainPageEvent", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
}
