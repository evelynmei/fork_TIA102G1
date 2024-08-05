package com.tia102g1.membercoin.controller;

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

import com.tia102g1.member.model.Member;
import com.tia102g1.member.model.MemberService;
import com.tia102g1.membercoin.model.MemberCoinService;
import com.tia102g1.membercoin.model.MemberCoinVO;
import com.tia102g1.orderlist.model.OrderListService;
import com.tia102g1.orderlist.model.OrderListVO;

@Controller
@RequestMapping("/memberCoin")
public class MemberCoinController {
	
	@Autowired
	MemberCoinService memberCoinSvc;
	
	@Autowired
	MemberService memberSvc;
	
	@Autowired
	OrderListService orderListSvc;
	
	//帶著VO物件,導向addMemberCoin.html
	@GetMapping("addMemberCoin")
	public String addMemberCoin(ModelMap model) {
		MemberCoinVO memberCoinVO = new MemberCoinVO();
		model.addAttribute("memberCoinVO", memberCoinVO);
		return "memberCoin/addMemberCoin";
	}
	
	//新增
	@PostMapping("insert")
	public String insert(@Valid MemberCoinVO memberCoinVO, BindingResult result, ModelMap model) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中memberCoinPic欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(memberCoinVO, result, "memberCoinPic");
//
//		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
//			model.addAttribute("errorMessage", "活動圖片: 請上傳圖片");
//		} else {
//			for (MultipartFile multipartFile : parts) {
//				byte[] buf = multipartFile.getBytes();
//				memberCoinVO.setMemberCoinPic(buf);
//			}
//		}
		if (result.hasErrors()) {
			return "memberCoin/addMemberCoin";
		}
		/*************************** 2.開始新增資料 *****************************************/
		
		memberCoinVO.setLastUpdatedBy(memberCoinVO.getCreatedBy());
		memberCoinSvc.addMemberCoin(memberCoinVO);
		
//		try {
//			memberCoinVO.setLastUpdatedBy(memberCoinVO.getCreatedBy());
//			memberCoinSvc.addMemberCoin(memberCoinVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<MemberCoinVO> list = memberCoinSvc.getAll();
		model.addAttribute("MemberCoinListData", list);
		model.addAttribute("success", "- (新增成功)");
		
		return "redirect:/memberCoin/listAllMemberCoin";
	}
	
	
	//查出指定VO物件,轉交給updateMemberCoin.html做修改
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("memCoinId") String memCoinId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		
		//先把指定id的VO物件查出來並顯示,準備交給updateMemberCoin頁面做修改
		MemberCoinVO memberCoinVO = memberCoinSvc.getOneMemberCoin(Integer.valueOf(memCoinId));
		
		System.out.println("查詢到的VO:" + memberCoinVO);
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("memberCoinVO", memberCoinVO);
		
		return "memberCoin/updateMemberCoin";
	}
	
	
	//修改
	@PostMapping("update")
	public String update(@Valid MemberCoinVO memberCoinVO, BindingResult result, ModelMap model) throws IOException {
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中memberCoinPic欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(memberCoinVO, result, "memberCoinPic");
//
//		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時,就取原有的圖片塞入
//			byte[] memberCoinPic = memberCoinSvc.getOneMemberCoin(memberCoinVO.getMemberCoinId()).getMemberCoinPic(); //getOneEmp()返回VO物件, 再呼叫圖片屬性getter
//			memberCoinVO.setMemberCoinPic(memberCoinPic); //然後setter放入當前VO物件中
//		} else { //使用者有選擇要上傳的新圖片時
//			for (MultipartFile multipartFile : parts) { //逐一取出
//				byte[] memberCoinPic = multipartFile.getBytes(); //轉為Bytes
//				memberCoinVO.setMemberCoinPic(memberCoinPic); //把Bytes setter進當前VO物件的圖片屬性
//			}
//		}
		if (result.hasErrors()) { //錯誤訊息result
			return "memberCoin/updateMemberCoin";
		}
		/*************************** 2.開始修改資料 *****************************************/
		System.out.println("準備更新的VO:" + memberCoinVO);
		
		memberCoinSvc.updateMemberCoin(memberCoinVO); //把更新好屬性的當前VO物件交給Service層做update
		
//		try {
//			memberCoinSvc.updateMemberCoin(memberCoinVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		memberCoinVO = memberCoinSvc.getOneMemberCoin(Integer.valueOf(memberCoinVO.getMemCoinId())); //取出剛更新完的VO物件,顯示在前端頁面上
		model.addAttribute("memberCoinVO", memberCoinVO);
			
		return "memberCoin/listOneMemberCoin";
	}
	
	
	//刪除
	@PostMapping("delete")
	public String delete(@RequestParam("memCoinId") String memCoinId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		
		memberCoinSvc.deleteMemberCoin(Integer.valueOf(memCoinId)); //執行刪除
		
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<MemberCoinVO> list = memberCoinSvc.getAll(); //全部查詢,刪完後顯示最新的全部資料
		model.addAttribute("memberCoinListData", list);
		model.addAttribute("success", "- (刪除成功)");
		
		return "memberCoin/listAllMemberCoin";
	}
	
//	fk用
	@ModelAttribute("memberListData")
	protected List<Member> referenceListData_Member() {
		List<Member> list = memberSvc.getAll();
		return list;
	}
	
	@ModelAttribute("orderListListData")
	protected List<OrderListVO> referenceListData_OrderList() {
		List<OrderListVO> list = orderListSvc.getAll();
		return list;
	}
	
	
	// BindingResult 是 Spring 框架中的一個接口，用來存儲表單驗證的錯誤信息
	// 去除BindingResult中某個欄位的FieldError紀錄
	// 動態處理表單驗證錯誤
	// 返回的 BindingResult 會包含所有原本的錯誤信息，但移除了指定欄位名稱的錯誤
	public BindingResult removeFieldError(MemberCoinVO memberCoinVO, BindingResult result, String removedFieldname) {
		// 取得需要保留的錯誤列表
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream() // 取得所有的FieldError //轉換成一個流來處理這些錯誤
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)) // 過濾掉欄位名稱等於 removedFieldname 的錯誤
				.collect(Collectors.toList()); // 將過濾後的結果收集成一個 List<FieldError>
		// 重新建立 BindingResult
		result = new BeanPropertyBindingResult(memberCoinVO, "memberCoinVO"); // 創建一個新的 BeanPropertyBindingResult 對象，綁定到 empVO

		// 將保留的錯誤加回到新的 BindingResult
		for (FieldError fieldError : errorsListToKeep) { // 將保留的錯誤逐個加回到新的 BindingResult 中
			result.addError(fieldError);
			System.out.println(fieldError);
		}
		return result;
	}
	
	
	//複合查詢
	@PostMapping("listMemberCoins_ByCompositeQuery")
	public String listAllMemberCoin(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap(); //getParameterMap()返回一個map
		//該map傳入getAll()會執行複合查詢CompositeQuery_XXX class的getAllC()方法,回傳查詢結果list
		List<MemberCoinVO> list = memberCoinSvc.getAll(map);
		model.addAttribute("memberCoinListData", list);
		
		return "memberCoin/listAllMemberCoin";
	}
}
