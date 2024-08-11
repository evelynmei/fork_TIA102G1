package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;


    @GetMapping({"admin/coupon", "/coupon/mainPageCoupon" })
    public String mainPageCoupon(Model model) {
        List<Coupon> couponList = couponService.getAllCoupons();
        model.addAttribute("couponList", couponList);
        return "/coupon/mainPageCoupon";
    }

    /**
     * 查詢全部優惠券
     * @return
     */
    @GetMapping("coupon")
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
    public String addCoupon(@ModelAttribute("coupon") Coupon coupon) {
        couponService.addCoupon(coupon);
        return "redirect:/coupon/mainPageCoupon";
    }

    /**
     * 編輯優惠券
     * @param couponRequest: 修改用的 Coupon.java
     * @return 重導回 coupon 管理首頁
     */
    @PutMapping("coupon/{couponId}")
    public String updateCoupon(@PathVariable Integer couponId, @ModelAttribute("coupon") CouponRequest couponRequest, RedirectAttributes redirectAttributes) {
        couponRequest.setEditCouponId(couponId);
        System.out.println(couponRequest);
        Coupon updatedCoupon = couponService.updateCoupon(couponRequest);

        // 若需要傳遞更新後的優惠券信息到重定向的頁面，可以使用 RedirectAttributes
        redirectAttributes.addFlashAttribute("updatedCoupon", updatedCoupon);

        return "redirect:/coupon/mainPageCoupon";
    }

    /**
     * 刪除優惠券
     * @param couponId
     * @return
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
     * @return
     */
    @GetMapping("coupon/{couponId}")
    public ResponseEntity<?> getCoupon(@PathVariable Integer couponId) {
        Coupon coupon = couponService.getCoupon(couponId);
        return ResponseEntity.status(HttpStatus.OK).body(coupon);
    }

    /**
     * 搜尋優惠券
     * @param searchParams
     * @return
     */

    @GetMapping("coupon/search")
    public ResponseEntity<List<Coupon>> searchCoupons(@RequestParam Map<String, String> searchParams) {
        List<Coupon> coupons = couponService.searchCoupons(searchParams);
        return ResponseEntity.ok(coupons);
    }
}
