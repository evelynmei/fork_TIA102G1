package com.tia102g1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g1.productType.model.ProductTypeService;
import com.tia102g1.productType.model.ProductTypeVO;

@Controller
public class IndexController {
	
	@Autowired
	ProductTypeService typeSvc;
	
	//首頁
	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}
	
	//促銷活動
	@GetMapping("/event/select_page")
	public String select_page(Model model) {		
		return "event/select_page";
	}
	
	//商品類型
	@GetMapping("/productType/select_page")
	public String productType_select_page(Model model) {		
		return "productType/select_page";
	}
	
	@GetMapping("/productType/listAllType")
	public String listAllType(Model model) {
		return "productType/listAllType";
	}
	
	@ModelAttribute("typeListData")
	protected List<ProductTypeVO> referenceListData(Model model){
		List<ProductTypeVO> list = typeSvc.getAll();
		return list;
	}
	
	
	
}
