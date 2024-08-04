package com.tia102g1.store.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g1.county.model.CountyService;
import com.tia102g1.county.model.CountyVO;
import com.tia102g1.dist.model.DistService;
import com.tia102g1.dist.model.DistVO;
import com.tia102g1.store.model.StoreService;
import com.tia102g1.store.model.StoreVO;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	StoreService storeSvc;
	
	@Autowired
	CountyService countySvc;
	
	@Autowired
	DistService distSvc;
	
	//帶著VO物件,導向addStore.html
	@GetMapping("addStore")
	public String addStore(ModelMap model) {
		StoreVO storeVO = new StoreVO();
		model.addAttribute("storeVO", storeVO);
		return "store/addStore";
	}
	
	//新增
	@PostMapping("insert")
	public String insert(@Valid StoreVO storeVO, BindingResult result, ModelMap model) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中storePic欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(storeVO, result, "storePic");
//
//		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
//			model.addAttribute("errorMessage", "活動圖片: 請上傳圖片");
//		} else {
//			for (MultipartFile multipartFile : parts) {
//				byte[] buf = multipartFile.getBytes();
//				storeVO.setStorePic(buf);
//			}
//		}
		if (result.hasErrors()) {
			return "store/addStore";
		}
		/*************************** 2.開始新增資料 *****************************************/
		
		storeVO.setLastUpdatedBy(storeVO.getCreatedBy());
		storeSvc.addStore(storeVO);
		
//		try {
//			storeVO.setLastUpdatedBy(storeVO.getCreatedBy());
//			storeSvc.addStore(storeVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StoreVO> list = storeSvc.getAll();
		model.addAttribute("StoreListData", list);
		model.addAttribute("success", "- (新增成功)");
		
		return "redirect:/store/listAllStore";
	}
	
	
	//查出指定VO物件,轉交給updateStore.html做修改
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("storeId") String storeId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		
		//先把指定id的VO物件查出來並顯示,準備交給updateStore頁面做修改
		StoreVO storeVO = storeSvc.getOneStore(Integer.valueOf(storeId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("storeVO", storeVO);
		
		return "store/updateStore";
	}
	
	
	//修改
	@PostMapping("update")
	public String update(@Valid StoreVO storeVO, BindingResult result, ModelMap model) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中storePic欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(storeVO, result, "storePic");
//
//		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時,就取原有的圖片塞入
//			byte[] storePic = storeSvc.getOneStore(storeVO.getStoreId()).getStorePic(); //getOneEmp()返回VO物件, 再呼叫圖片屬性getter
//			storeVO.setStorePic(storePic); //然後setter放入當前VO物件中
//		} else { //使用者有選擇要上傳的新圖片時
//			for (MultipartFile multipartFile : parts) { //逐一取出
//				byte[] storePic = multipartFile.getBytes(); //轉為Bytes
//				storeVO.setStorePic(storePic); //把Bytes setter進當前VO物件的圖片屬性
//			}
//		}
		if (result.hasErrors()) { //錯誤訊息result
			return "store/updateStore";
		}
		/*************************** 2.開始修改資料 *****************************************/
		
		storeSvc.updateStore(storeVO); //把更新好屬性的當前VO物件交給Service層做update
		
//		try {
//			storeSvc.updateStore(storeVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		storeVO = storeSvc.getOneStore(Integer.valueOf(storeVO.getStoreId())); //取出剛更新完的VO物件,顯示在前端頁面上
		model.addAttribute("storeVO", storeVO);
			
		return "store/listOneStore";
	}
	
	
	//刪除
	@PostMapping("delete")
	public String delete(@RequestParam("storeId") String storeId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		
		storeSvc.deleteStore(Integer.valueOf(storeId)); //執行刪除
		
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<StoreVO> list = storeSvc.getAll(); //全部查詢,刪完後顯示最新的全部資料
		model.addAttribute("storeListData", list);
		model.addAttribute("success", "- (刪除成功)");
		
		return "store/listAllStore";
	}
	
	
	@ModelAttribute("countyListData")
	protected List<CountyVO> referenceListData_County() {
		List<CountyVO> list = countySvc.getAll();
		return list;
	}
	
	@ModelAttribute("distListData")
	protected List<DistVO> referenceListData_Dist() {
		List<DistVO> list = distSvc.getAll();
		return list;
	}
	
	
	// BindingResult 是 Spring 框架中的一個接口，用來存儲表單驗證的錯誤信息
	// 去除BindingResult中某個欄位的FieldError紀錄
	// 動態處理表單驗證錯誤
	// 返回的 BindingResult 會包含所有原本的錯誤信息，但移除了指定欄位名稱的錯誤
	public BindingResult removeFieldError(StoreVO storeVO, BindingResult result, String removedFieldname) {
		// 取得需要保留的錯誤列表
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream() // 取得所有的FieldError //轉換成一個流來處理這些錯誤
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)) // 過濾掉欄位名稱等於 removedFieldname 的錯誤
				.collect(Collectors.toList()); // 將過濾後的結果收集成一個 List<FieldError>
		// 重新建立 BindingResult
		result = new BeanPropertyBindingResult(storeVO, "storeVO"); // 創建一個新的 BeanPropertyBindingResult 對象，綁定到 empVO

		// 將保留的錯誤加回到新的 BindingResult
		for (FieldError fieldError : errorsListToKeep) { // 將保留的錯誤逐個加回到新的 BindingResult 中
			result.addError(fieldError);
			System.out.println(fieldError);
		}
		return result;
	}
	
	
	//複合查詢
	@PostMapping("listStores_ByCompositeQuery")
	public String listAllStore(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap(); //getParameterMap()返回一個map
		//該map傳入getAll()會執行複合查詢CompositeQuery_XXX class的getAllC()方法,回傳查詢結果list
		List<StoreVO> list = storeSvc.getAll(map);
		model.addAttribute("storeListData", list);
		
		return "store/listAllStore";
	}
	
}
