package com.tia102g1.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupon WHERE couponId =?1", nativeQuery = true)
    void deleteByCouponId(int couponId);





}
