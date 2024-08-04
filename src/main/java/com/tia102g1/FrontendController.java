package com.tia102g1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    /* ======================= 前台使用者頁面 ======================= */
    // 首頁
    @RequestMapping({"/index","/index.html"})
    public String frontendIndex(Model model) {
        return "/frontendApp/index";
    }

    /*  ====== 會員  ====== */
    // 註冊登入
    @GetMapping({"/loginsignup", "/loginSignup.html"})
    public String frontendLoginSignup(Model model) {
        return "/frontendApp/loginSignup";
    }
    // 我的最愛
    @GetMapping({"/favproduct","/favProduct.html"})
    public String frontendFavProduct(Model model) { return "/frontendApp/favProduct"; }

    //會員資料
    @GetMapping({"/memberinfo", "/memberInfo.html"})
    public String frontendMemberInfo(Model model) {
        return "/frontendApp/memberInfo";
    }

    /*  ====== 購物流程 ====== */
    // 商品總覽
    @GetMapping({"/productcategory", "/productCategory.html"})
    public String frontendCategory(Model model) {
        return "/frontendApp/productCategory";
    }

    // 商品詳細資訊
    @GetMapping({"/productdetails","/productDetails.html"})
    public String frontendProductDetail(Model model) {
        return "/frontendApp/productDetails";
    }

    //購物車
    @GetMapping({"/cart","/cart.html"})
    public String frontendShoppingCart(Model model){
        return "/frontendApp/cart";
    }

    /* ====== 結帳付款 ====== */
    // 填寫付款資訊
    @GetMapping({"/checkout", "/checkout.html"})
    public String frontendCheckout(Model model) {
        return "/frontendApp/checkout";
    }

    // 訂單成立確認
    @GetMapping({"/orderconfirmation", "/orderConfirmation.html"})
    public String frontendOrderConfirm(Model model) {
        return "/frontendApp/orderConfirmation";
    }

    /* ====== 常見問題 ====== */
    @GetMapping({"/commonask", "/commonAsk.html"})
    public String frontendCommonAsk(Model model) {
        return "/frontendApp/commonAsk";
    }

    /* ====== 客服 ====== */
    // 聯絡我們
    @GetMapping({"/contactus","/contactUs.html"})
    public String frontendContactUs(Model model){
        return "/frontendApp/contactUs";
    }

    /* ====== 門市資訊 ====== */
    @GetMapping({"/store","/store.html"})
    public String frontendStore(Model model) { return "/frontendApp/store"; }



    //===================================================
    //關於我們 (有空再做)
    @GetMapping({"/aboutus","/aboutUs.html"})
    public String frontendAboutUs(Model model){
        return "/frontendApp/aboutUs";
    }

    //測試頁
    @GetMapping({"/sample"})
    public String frontendSample(Model model) {
        return "/frontendApp/sample";
    }
}
