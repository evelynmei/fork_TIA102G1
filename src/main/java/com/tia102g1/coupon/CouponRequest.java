package com.tia102g1.coupon;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class CouponRequest {
    private Integer editCouponId;
    private String editCouponCode;
    private String editCouponName;
    private Integer editCouponStatus;
    private Date editStartDt;
    private Date editEndDt;
    private Integer editDiscType;
    private Integer editDiscAmount;
    private Float editDiscPercentage;
    private String editCreatedBy;
    private Timestamp editDateCreated;
    private String editLastUpdatedBy;
    private Timestamp editLastUpdated;

}
