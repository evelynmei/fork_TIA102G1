package com.tia102g1.productinfo.controller;

import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.orderlistinfo.model.OrderListInfoVO;
import com.tia102g1.productcomment.model.ProductCommentService;
import com.tia102g1.productcomment.model.ProductCommentVO;
import com.tia102g1.productinfo.entity.ProductInfo;
import com.tia102g1.productinfo.model.ProductInfoServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class productInfoUserController {

	@Autowired
	ProductInfoServiceS productInfoServiceS;
	
	@Autowired
	ProductCommentService productCommentService;


	/**
	 * 取得單一商品資訊
	 * 
	 * @param productId
	 * @return
	 */
	// 測api
	@GetMapping("product/api/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
		ProductInfo product = productInfoServiceS.getOneProductInfo(productId);
		Map<String, String> map = new HashMap<>();
//        System.out.println(product.toString());
		map.put("productId", product.getProductId().toString());
		map.put("productName", product.getProName());
		map.put("productPrice", product.getProPrice().toString());
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@GetMapping("product/{productId}")
	public String getProduct(@PathVariable Integer productId, Model model) {
		try {
	        // 獲取產品信息
	        ProductInfo product = productInfoServiceS.getOneProductInfo(productId);
	        model.addAttribute("product", product);
	        
	        // 獲取 productCommentVO
	        List<ProductCommentVO> productCommentVO = productCommentService.getOneProdComment(productId);
	        model.addAttribute("productCommentVO", productCommentVO);

	        // 計算商品的平均星星數
	        Integer averageRating = 0; // 預設值為0
	        if (product.getCommentUsers() != null && product.getCommentUsers() != 0) {
	            averageRating = product.getCommentStars() / product.getCommentUsers();
	        } else {
	            averageRating = 0; // 或者其他合理的預設值
	        }

	        model.addAttribute("averageRating", averageRating);

	    } catch (Exception e) {
	        // 捕獲並記錄異常，這樣你可以看到更詳細的錯誤信息
	        e.printStackTrace();
	        // 可以返回一個錯誤頁面或顯示友好的錯誤消息
	        return "error";
	    }
	    return "/frontendapp/productDetails";
	}
	
	// 商品總覽
	@GetMapping({ "/productcategory", "/productCategory.html" })
	public String frontendCategory(Model model) {
		List<ProductInfo> list = productInfoServiceS.getAll();
		model.addAttribute("productInfoListData", list);
		return "/frontendapp/productCategory";
	}
	
	@PostMapping("listComment_byProduct")
	public String listProdComments(@RequestParam("productId") String productId, Model model) {
		List<ProductCommentVO> productCommentListData = productCommentService.getOneProdComment(Integer.valueOf(productId));
		model.addAttribute("productCommentListData", productCommentListData);
		
		return "/frontendapp/productDetails";
	}
	
}
