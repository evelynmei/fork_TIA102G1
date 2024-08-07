package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
//    @PostMapping("coupon/{couponId}")
//    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon) {
//        Coupon newCoupon = couponService.addCoupon(coupon);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
//    }

    /**
     * 查詢全部優惠券
     * @return
     */
    @GetMapping("coupon")
    public ResponseEntity<?> getAllCoupons() {
        List<Coupon> couponList = couponService.getAllCoupons();
        return ResponseEntity.status(HttpStatus.OK).body(couponList);
    }

}
