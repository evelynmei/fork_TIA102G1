package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CouponService")
public class CouponService {

    @Autowired
    CouponRepository repository;


    /**
     * 新增優惠券
     * @param coupon
     * @return
     */
    public Coupon addCoupon(Coupon coupon) {
        return repository.save(coupon);
    }

    /**
     * 修改優惠券
     *
     * @param couponRequest: 修改用的 Coupon.java
     * @return updateCoupon: 方便在 controller 調用時檢查
     */
    public Coupon updateCoupon(CouponRequest couponRequest) {
        Coupon updateCoupon = new Coupon();
        updateCoupon.setCouponId(couponRequest.getEditCouponId());
        updateCoupon.setCouponCode(couponRequest.getEditCouponCode());
        updateCoupon.setCouponName(couponRequest.getEditCouponName());
        updateCoupon.setCouponStatus(couponRequest.getEditCouponStatus());
        updateCoupon.setStartDt(couponRequest.getEditStartDt());
        updateCoupon.setEndDt(couponRequest.getEditEndDt());
        updateCoupon.setDiscType(couponRequest.getEditDiscType());
        if(couponRequest.getEditDiscPercentage() != null) updateCoupon.setDiscPercentage(couponRequest.getEditDiscPercentage());
        if(couponRequest.getEditDiscAmount() != null) updateCoupon.setDiscAmount(couponRequest.getEditDiscAmount());
        updateCoupon.setLastUpdatedBy(couponRequest.getEditLastUpdatedBy());
        return repository.save(updateCoupon);
    }

    /**
     * 刪除優惠券
     * @param couponId
     */
    public void deleteCoupon(Integer couponId) {
        if (repository.existsById(couponId))
            repository.deleteById(couponId);
    }

    /**
     * 查詢單項優惠券
     * @param couponId
     * @return
     */
    public Coupon getCoupon(Integer couponId) {
        Optional<Coupon> optional = repository.findById(couponId);
        return optional.orElse(null);
    }

    /**
     * 查詢全部優惠券
     * @return
     */
    public List<Coupon> getAllCoupons() {
        return repository.findAll();
    }
}

