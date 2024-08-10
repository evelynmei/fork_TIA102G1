package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 新增優惠券
     * @param coupon
     * @return
     */
    @PostMapping("coupon/add")
//    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon) {
//        Coupon newCoupon = couponService.addCoupon(coupon);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
//    }

    public String addCoupon(@ModelAttribute("coupon") Coupon coupon) {
        couponService.addCoupon(coupon);
        return "redirect:/coupon/mainPageCoupon";
    }

    /**
     * 修改優惠券
     * @param coupon
     * @return
     */
    @PutMapping("coupon/{couponId}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Integer couponId, @RequestBody Coupon coupon) {
        coupon.setCouponId(couponId);
       Coupon updatedCoupon = couponService.updateCoupon(coupon);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCoupon);
}
    /**
     * 刪除優惠券
     * @param couponId
     * @return
     */
    @DeleteMapping("coupon/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Integer couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.status(HttpStatus.OK).build();
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
     * 查詢全部優惠券
     * @return
     */
    @GetMapping("coupon")
    public ResponseEntity<?> getAllCoupons(ModelMap modelMap) {
        List<Coupon> couponList = couponService.getAllCoupons();
        modelMap.addAttribute("couponList", couponList);
        return ResponseEntity.status(HttpStatus.OK).body(couponList);
    }
}
