package com.tia102g1.productinfo.controller;

import com.tia102g1.coupon.CouponService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class productInfoUserController {

	@Autowired
	ProductInfoServiceS productInfoServiceS;

	@Autowired
	CouponService couponService;
	
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

	//單一商品詳細資訊
//	@GetMapping("product/{productId}")
//	public String getProduct(@PathVariable Integer productId, Model model) {
//		ProductInfo product = productInfoServiceS.getOneProductInfo(productId);
//		model.addAttribute("product", product);
//
//        //計算商品的平均星星數
//        Integer averageRating = product.getCommentStars() / product.getCommentUsers();
//        model.addAttribute("averageRating", averageRating);
//        return "/frontendapp/productDetails";
//    }
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
	        e.printStackTrace();
	        return "error";
	    }
	    return "/frontendapp/productDetails";
	}

	
	
	

	// 商品總覽
	@GetMapping({ "/productcategory", "/productCategory.html" })
	public String frontendCategory(Model model) {
		List<ProductInfo> list = productInfoServiceS.getAll();
		model.addAttribute("productInfoListData", list);
		model.addAttribute("couponList", couponService.getAllCoupons());
		return "/frontendapp/productCategory";
	}
}
