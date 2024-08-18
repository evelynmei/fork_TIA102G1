package com.tia102g1.fav_product;

import com.tia102g1.coupon.CouponService;
import com.tia102g1.member.model.Member;
import com.tia102g1.member.service.MemberService;
import com.tia102g1.productinfo.entity.ProductInfo;
import com.tia102g1.productinfo.model.ProductInfoServiceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FavProductController {

    @Autowired
    FavProductService favProductService;
    
    @Autowired
    MemberService memberSvc;
    
    @Autowired
    ProductInfoServiceS prodInfoSvc;

    @Autowired
    CouponService couponService;
    
    @GetMapping("/favProduct")
    public String getFavProductsByMemberId(Authentication authentication, Model model) {
        System.out.println("=========================================");
        Member member = memberSvc.getMemberByAccount(authentication.getName());
        Integer memberId = member.getMemberId();

        // 調用服務層來獲取該會員的最愛商品資訊
        List<ProductInfo> productInfos = favProductService.getFavProdInfo(memberId);
        
        // 將商品資訊添加到模型中，傳遞給前端頁面
        model.addAttribute("favProducts", productInfos);
        model.addAttribute("couponList", couponService.getAllCoupons());
        
        return "frontendapp/favProduct";  // 返回對應的視圖名稱
    }
    
    @GetMapping("/favProduct/isFavorite")
    public ResponseEntity<Boolean> isFavorite(@RequestParam("productId") Integer productId, Authentication authentication) {
        try {
            Member member = memberSvc.getMemberByAccount(authentication.getName());
            Integer memberId = member.getMemberId();

            // 檢查商品是否在最愛中
            boolean isFavorite = favProductService.isFavorite(memberId, productId);

            return ResponseEntity.ok(isFavorite);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }





    /**
     * todo 前端請求時須給預設值欄位(favProductId, joinDt)
     * 新增最愛商品
     * @param favPrd
     * @return
     */
    @PostMapping("/favProduct/add")
    public ResponseEntity<FavProduct> addFavPrd(Authentication authentication, @RequestParam int productId) {
    	String username = authentication.getName();
    	Member memberId = memberSvc.getMemberByAccount(username);
    	
    	ProductInfo product = new ProductInfo();
    	product.setProductId(productId);

    	FavProduct favPrd = new FavProduct();
        favPrd.setMember(memberId);
        favPrd.setProductInfo(product);

        FavProduct newFavPrd = favProductService.addFavPrd(favPrd);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFavPrd);
    }

    /**
     * 刪除最愛商品
     * @param favProductId
     * @return
     */
    @DeleteMapping("/favProduct/remove")
    public ResponseEntity<?> deleteFavPrd(@RequestParam("productId") Integer productId, Authentication authentication) {
        try {
            Member member = memberSvc.getMemberByAccount(authentication.getName());
            Integer memberId = member.getMemberId();

            // 移除最愛商品邏輯
            favProductService.removeFavorite(memberId, productId);
        	System.out.println("=========="+ memberId + productId + "===================================");

            return ResponseEntity.ok().build();  // 成功移除返回 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("移除最愛時發生錯誤");
        }
    }



    }
