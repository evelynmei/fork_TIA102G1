package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 列出全部優惠券
     * @param model
     * @return 後台優惠券主頁
     */
    @GetMapping({"admin/coupon", "/coupon/mainPageCoupon" })
    public String listAllCoupon(Model model) {
        List<Coupon> couponList = couponService.getAllCoupons();
        model.addAttribute("couponList", couponList);
        return "/coupon/mainPageCoupon";
    }

    /**
     * 查詢全部優惠券
     * @return api
     * 前端請求時, 返回json格式的優惠券資料
     */
    @GetMapping("api/coupon")
    public ResponseEntity<?> getAllCoupons(ModelMap modelMap) {
        List<Coupon> couponList = couponService.getAllCoupons();
        modelMap.addAttribute("couponList", couponList);
        return ResponseEntity.status(HttpStatus.OK).body(couponList);
    }
    /**
     * 新增優惠券
     * @param coupon
     * @return 重導回 coupon 管理首頁
     */
    @PostMapping("coupon/add")
    public String addCoupon(@Valid @ModelAttribute("coupon") Coupon coupon) {
        Integer discAmount = coupon.getDiscAmount();
        BigDecimal discPercentage = coupon.getDiscPercentage();

        //判斷優惠券的折扣類型
        if(discPercentage != null) coupon.setDiscType(2);
        else if(discAmount != null) coupon.setDiscType(1);
        couponService.addCoupon(coupon);
        return "redirect:/coupon/mainPageCoupon";
    }

    /**
     * 前往編輯優惠券頁面
     * @param couponId: 用來拿到要更新的 coupon
     * @return 引導到更新頁 updateCoupon.html
     */
    @GetMapping("coupon/edit/{couponId}")
    public String editingCoupon(@PathVariable Integer couponId, Model model) {
        Coupon toUpdate = couponService.getCoupon(couponId);

        Integer discAmount = toUpdate.getDiscAmount();
        BigDecimal discPercentage = toUpdate.getDiscPercentage();

        //判斷優惠券的折扣類型
        if(discPercentage != null) toUpdate.setDiscType(2);
        else if(discAmount != null) toUpdate.setDiscType(1);

        model.addAttribute("couponToUpdate", toUpdate);
        return "/coupon/updateCoupon";
    }

    /**
     * 編輯優惠券的處理，送出修改到資料庫，然後回管理主頁
     * @param couponId: 用來拿到要更新的 coupon
     * @param coupon: 從 updateCoupon.html 傳來要更改的 coupon 資料
     * @return 引導回後台優惠券主頁
     */
    @PutMapping("coupon/{couponId}")
    public String updateCoupon(@PathVariable Integer couponId,
                               @Valid @ModelAttribute("coupon") Coupon coupon) {
        coupon.setLastUpdated(new Timestamp(new Date().getTime()));

        Integer discAmount = coupon.getDiscAmount();
        BigDecimal discPercentage = coupon.getDiscPercentage();

        //判斷優惠券的折扣類型
        if(discPercentage != null) coupon.setDiscType(2);
        else if(discAmount != null) coupon.setDiscType(1);

        couponService.updateCoupon(coupon);
        return "redirect:/admin/coupon";
    }

    /**
     * 刪除優惠券
     * @param couponId
     * @return 重導回優惠券主頁
     */
    @DeleteMapping("coupon/{couponId}")
    public String deleteCoupon(@PathVariable Integer couponId) {
        System.out.println("couponId = " + couponId);
        couponService.deleteCoupon(couponId);
        return "redirect:/coupon/mainPageCoupon";
    }

    /**
     * 查詢單項優惠券
     * @param couponId
     * @return api
     */
    @GetMapping("coupon/{couponId}")
    public ResponseEntity<?> getCoupon(@PathVariable Integer couponId) {
        Coupon coupon = couponService.getCoupon(couponId);
        return ResponseEntity.status(HttpStatus.OK).body(coupon);
    }

    /**
     * 搜尋優惠券
     * @param searchParams
     * @return 重導回優惠券主頁, 顯示搜尋結果
     */
    @GetMapping("coupon/search")
    public String searchResult(@RequestParam(required = false) Map<String, String> searchParams, Model model) {
        List<Coupon> couponList = couponService.searchCoupons(searchParams);
        model.addAttribute("couponList", couponList);
        //搜尋結果的boolean value
        model.addAttribute("isSearchOperation", true);

        return "coupon/mainPageCoupon";
    }
}
