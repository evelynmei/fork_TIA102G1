package com.tia102g1.store.controller;

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

import com.tia102g1.county.model.CountyService;
import com.tia102g1.county.model.CountyVO;
import com.tia102g1.dist.model.DistService;
import com.tia102g1.dist.model.DistVO;
import com.tia102g1.store.model.StoreService;
import com.tia102g1.store.model.StoreVO;


@Controller
@Validated
@RequestMapping("/store")
public class StoreIdController {
	
	@Autowired
	StoreService storeSvc;
	
	@Autowired
	CountyService countySvc;
	
	@Autowired
	DistService distSvc;
	
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="門市編號: 請勿空白")
		@RequestParam("storeId") String storeId,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
		
		StoreVO storeVO = storeSvc.getOneStore(Integer.valueOf(storeId));
		
		List<StoreVO> list = storeSvc.getAll();
		model.addAttribute("storeListData", list);     // for mainPageStore.html 第97 109行用
		
		//縣市
		model.addAttribute("countyVO", new CountyVO());
		List<CountyVO> list2 = countySvc.getAll();
		model.addAttribute("countyListData", list2);
		
		//鄉鎮區
		model.addAttribute("distVO", new DistVO());
		List<DistVO> list3 = distSvc.getAll();
		model.addAttribute("distListData", list3);
		
		if (storeVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "store/mainPageStore";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("storeVO", storeVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見mainPageStore.html的第156行 -->
		
//		return "back-end/store/listOneStore";  // 查詢完成後轉交listOneStore.html
		return "store/mainPageStore"; // 查詢完成後轉交select_page.html由其第158行insert listOneStore.html內的th:fragment="listOneStore-div
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

		List<StoreVO> list = storeSvc.getAll();
		model.addAttribute("storeListData", list);     // for select_page.html 第97 109行用
		
		//縣市
		model.addAttribute("countyVO", new CountyVO());
		List<CountyVO> list2 = countySvc.getAll();
		model.addAttribute("countyListData", list2);
		
		//鄉鎮區
		model.addAttribute("distVO", new DistVO());
		List<DistVO> list3 = distSvc.getAll();
		model.addAttribute("distListData", list3);
		
		String message = strBuilder.toString();
	    return new ModelAndView("store/mainPageStore", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
}
