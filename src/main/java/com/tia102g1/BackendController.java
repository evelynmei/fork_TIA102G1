package com.tia102g1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g1.county.model.CountyService;
import com.tia102g1.county.model.CountyVO;
import com.tia102g1.dist.model.DistService;
import com.tia102g1.dist.model.DistVO;
import com.tia102g1.event.model.EventService;
import com.tia102g1.event.model.EventVO;
import com.tia102g1.productType.model.ProductTypeService;
import com.tia102g1.productType.model.ProductTypeVO;
import com.tia102g1.qutype.model.QuTypeService;
import com.tia102g1.qutype.model.QuTypeVO;
import com.tia102g1.staff.model.StaffService;
import com.tia102g1.staff.model.StaffVO;
import com.tia102g1.store.model.StoreService;
import com.tia102g1.store.model.StoreVO;
import com.tia102g1.sysMsg.model.SysMsgService;
import com.tia102g1.sysMsg.model.SysMsgVO;

@Controller
public class BackendController {

	@Autowired
	ProductTypeService typeSvc;

	@Autowired
	EventService eventSvc;
	
	@Autowired
	StoreService storeSvc;
	
	@Autowired
	CountyService countySvc;
	
	@Autowired
	DistService distSvc;

	@Autowired
	SysMsgService sysMsgSvc;

	@Autowired
	QuTypeService quTypeSvc;
	
	@Autowired
	StaffService staffSvc;

	/* ======================= 後台管理頁面 ======================= */
	// 首頁
	@GetMapping({ "", "/admin", "/admin/index" })
	public String index(Model model) {
		return "index";
	}

	/* ======================= mainPage ======================= */

	// 會員資料
	@GetMapping({ "/member", "/member/mainPageMember" })
	public String mainPageMember(Model model) {
		return "/member/mainPageMember";
	}

	// 會員黑名單
	@GetMapping("member/blockedMember")
	public String blockedMember(Model model) {
		return "member/blockedMember";
	}

	// 會員等級對照
	@GetMapping({ "/memberLv", "/memberLv/mainPageMemberLv" })
	public String mainPageMemberLv(Model model) {
		return "/memberLv/mainPageMemberLv";
	}

	// 優惠券持有紀錄
	@GetMapping({ "/memberCoupon", "/memberCoupon/mainPageMemberCoupon" })
	public String mainPageMemberCoupon(Model model) {
		return "/memberCoupon/mainPageMemberCoupon";
	}

	// 購物金持有紀錄
	@GetMapping({ "/memberCoin", "/memberCoin/mainPageMemberCoin" })
	public String mainPageMemberCoin(Model model) {
		return "/memberCoin/mainPageMemberCoin";
	}

	// 商品類型對照
	@GetMapping({ "/productType", "/productType/mainPageProductType" })
	public String mainPageProductType(Model model) {
		return "/productType/mainPageProductType";
	}

	@GetMapping("/productType/listAllType")
	public String listAllType(Model model) {
		return "productType/listAllType";
	}

	@ModelAttribute("typeListData")
	protected List<ProductTypeVO> referenceListData(Model model) {
		List<ProductTypeVO> list = typeSvc.getAll();
		return list;
	}

	// 商品基本資料
//	@GetMapping({ "/productInfo", "/productInfo/mainPageProductInfo" })
//	public String mainPageProductInfo(Model model) {
//		return "/productInfo/mainPageProductInfo";
//	}

	// 商品入庫紀錄
	@GetMapping({ "/productStock", "/productStock/mainPageProductStock" })
	public String mainPageProductStock(Model model) {
		return "/productStock/mainPageProductStock";
	}

	// 商品評價紀錄
	@GetMapping({ "/productComment", "/productComment/mainPageProductComment" })
	public String mainPageProductComment(Model model) {
		return "/productComment/mainPageProductComment";
	}

	// 最愛商品紀錄
	@GetMapping({ "/favProduct", "/favProduct/mainPageFavProduct" })
	public String mainPageFavProduct(Model model) {
		return "/favProduct/mainPageFavProduct";
	}

	// 購物車紀錄
	@GetMapping({ "/cart", "/cart/mainPageCart" })
	public String mainPageCart(Model model) {
		return "/cart/mainPageCart";
	}

	// 加購商品關係
	@GetMapping({ "/addOn", "/addOn/mainPageAddOn" })
	public String mainPageAddOn(Model model) {
		return "/addOn/mainPageAddOn";
	}

	// 訂單紀錄
	@GetMapping({ "/orderList", "/orderList/mainPageOrderList" })
	public String mainPageOrderList(Model model) {
		return "/orderList/mainPageOrderList";
	}

	// 訂單商品明細
	@GetMapping({ "/orderListInfo", "/orderListInfo/mainPageOrderListInfo" })
	public String mainPageOrderListInfo(Model model) {
		return "/orderListInfo/mainPageOrderListInfo";
	}

	// 促銷活動
	@GetMapping({ "/event", "/event/mainPageEvent" })
	public String mainPageEvent(Model model) {
		return "/event/mainPageEvent";
	}

	@GetMapping("/event/listAllEvent")
	public String listAllEvent(Model model) {
		return "event/listAllEvent";
	}

	@ModelAttribute("eventListData")
	protected List<EventVO> referenceEventListData(Model model) {
		List<EventVO> list = eventSvc.getAll();
		return list;
	}

	// 優惠券
	@GetMapping({ "/coupon", "/coupon/mainPageCoupon" })
	public String mainPageCoupon(Model model) {
		return "/coupon/mainPageCoupon";
	}

	// 問題類型對照
	@GetMapping({ "/quType", "/quType/mainPageQuType" })
	public String mainPageQuType(Model model) {
		return "/quType/mainPageQuType";
	}

	@GetMapping("/quType/listAllQuType")
	public String listAllQuType(Model model) {
		return "quType/listAllQuType";
	}

	@ModelAttribute("quTypeListData")
	protected List<QuTypeVO> referenceQuTypeListData(Model model) {
		List<QuTypeVO> list = quTypeSvc.getAll();
		return list;
	}

	// 客服表單
	@GetMapping({ "/csForm", "/csForm/mainPageCsForm" })
	public String mainPageCsForm(Model model) {
		return "/csForm/mainPageCsForm";
	}

	// 報表分析管理
	@GetMapping({ "/report", "report/mainPageReport" })
	public String mainPageReport(Model model) {
		return "report/mainPageReport";
	}

	// 常見問題
	@GetMapping({ "/commonAsk", "/commonAsk/mainPageCommonAsk" })
	public String mainPageCommonAsk(Model model) {
		return "/commonAsk/mainPageCommonAsk";
	}

	// 商品公告
	@GetMapping({ "/news", "/news/mainPageNews" })
	public String mainPageNews(Model model) {
		return "/news/mainPageNews";
	}

	// 門市資訊
	@GetMapping({ "/store", "/store/mainPageStore" })
	public String mainPageStore(Model model) {
		return "/store/mainPageStore";
	}
	
	@GetMapping("/store/listAllStore")
	public String listAllStore(Model model) {
		return "store/listAllStore";
	}

	@ModelAttribute("storeListData")
	protected List<StoreVO> referenceStoreListData(Model model) {
		List<StoreVO> list = storeSvc.getAll();
		return list;
	}
	
	@ModelAttribute("countyListData")
	protected List<CountyVO> referenceListData_County(Model model) {
		model.addAttribute("countyVO", new CountyVO());
		List<CountyVO> list = countySvc.getAll();
		return list;
	}
	
	@ModelAttribute("distListData")
	protected List<DistVO> referenceListData_Dist(Model model) {
		model.addAttribute("distVO", new DistVO());
		List<DistVO> list = distSvc.getAll();
		return list;
	}

	// 縣市代碼對照
	@GetMapping({ "/county", "/county/mainPageCounty" })
	public String mainPageCounty(Model model) {
		return "/county/mainPageCounty";
	}

	// 鄉鎮區代碼對照
	@GetMapping({ "/dist", "/dist/mainPageDist" })
	public String mainPageDist(Model model) {
		return "/dist/mainPageDist";
	}

	// 系統通知訊息
	@GetMapping({ "/sysMsg", "/sysMsg/mainPageSysMsg" })
	public String mainPageSysMsg(Model model) {
		return "/sysMsg/mainPageSysMsg";
	}

	@GetMapping("/sysMsg/listAllSysMsg")
	public String listAllSysMsg(Model model) {
		return "sysMsg/listAllSysMsg";
	}

	@ModelAttribute("sysMsgListData")
	protected List<SysMsgVO> referenceSysMsgListData(Model model) {
		List<SysMsgVO> list = sysMsgSvc.getAll();
		return list;
	}

	// 員工資料
	@GetMapping("staff/mainPageStaff")
	public String mainPageStaff(Model model) {
		return "staff/mainPageStaff";
	}
	
	@GetMapping("staff/listAllStaff")
	public String listAllStaff(Model model) {
		return "staff/listAllStaff";
	}

	@ModelAttribute("staffListData")
	protected List<StaffVO> referenceStaffListData(Model model) {
		List<StaffVO> list = staffSvc.getAll();
		return list;
	}

}
