package com.tia102g1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    /* ======================= 前台使用者頁面 ======================= */
    //首頁
    @RequestMapping({"/index","/index.html"})
    public String frontendIndex(Model model) {
        return "/frontendApp/index";
    }

    //購物車
    @GetMapping({"/cart","/cart.html"})
    public String frontendShoppingCart(Model model){
        return "/frontendApp/cart";
    }

    //關於我們
    @GetMapping({"/aboutus","/aboutUs.html"})
    public String frontendAboutUs(Model model){
        return "/frontendApp/aboutUs";
    }

    //聯絡我們
    @GetMapping({"/contactus","/contactUs.html"})
    public String frontendContactUs(Model model){
        return "/frontendApp/contactUs";
    }

    //商品詳細資訊
    @GetMapping({"/productdetails","/productDetails.html"})
    public String frontendProductDetail(Model model) {
        return "/frontendApp/productDetails";
    }

    //我的最愛商品
    @GetMapping({"/favproduct","/favProduct.html"})
    public String frontendFavProduct(Model model) { return "/frontendApp/favProduct"; }
}
