package com.tia102g1.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Service("CouponService")
public class CouponService {

    @Autowired
    CouponRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 新增優惠券
     * @param coupon
     * @return
     */
    public Coupon addCoupon(Coupon coupon) {
        repository.save(coupon);
        return coupon;
    }

    /**
     * 修改優惠券
     * @param coupon
     */
    public void updateCoupon(Coupon coupon) {
        repository.save(coupon);
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
        return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

    /**
     * 查詢全部優惠券
     * @return
     */
    public List<Coupon> getAllCoupons() {
        return repository.findAll();
    }


}
