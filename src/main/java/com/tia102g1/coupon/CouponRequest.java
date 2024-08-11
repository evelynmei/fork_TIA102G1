package com.tia102g1.coupon;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class CouponRequest {
    private Integer editCouponId;
    private String editCouponCode;
    private String editCouponName;
    private Integer editCouponStatus;
    private Date editStartDt;
    private Date editEndDt;
    private Integer editDiscType;
    private Integer editDiscAmount;
    private BigDecimal editDiscPercentage;
    private String editCreatedBy;
    private Timestamp editDateCreated;
    private String editLastUpdatedBy;
    private Timestamp editLastUpdated;


    // setter、getter
    public Integer getEditCouponId() {
        return editCouponId;
    }

    public void setEditCouponId(Integer editCouponId) {
        this.editCouponId = editCouponId;
    }

    public String getEditCouponCode() {
        return editCouponCode;
    }

    public void setEditCouponCode(String editCouponCode) {
        this.editCouponCode = editCouponCode;
    }

    public String getEditCouponName() {
        return editCouponName;
    }

    public void setEditCouponName(String editCouponName) {
        this.editCouponName = editCouponName;
    }

    public Integer getEditCouponStatus() {
        return editCouponStatus;
    }

    public void setEditCouponStatus(Integer editCouponStatus) {
        this.editCouponStatus = editCouponStatus;
    }

    public Date getEditStartDt() {
        return editStartDt;
    }

    public void setEditStartDt(Date editStartDt) {
        this.editStartDt = editStartDt;
    }

    public Date getEditEndDt() {
        return editEndDt;
    }

    public void setEditEndDt(Date editEndDt) {
        this.editEndDt = editEndDt;
    }

    public Integer getEditDiscType() {
        return editDiscType;
    }

    public void setEditDiscType(Integer editDiscType) {
        this.editDiscType = editDiscType;
    }

    public Integer getEditDiscAmount() {
        return editDiscAmount;
    }

    public void setEditDiscAmount(Integer editDiscAmount) {
        this.editDiscAmount = editDiscAmount;
    }

    public BigDecimal getEditDiscPercentage() {
        return editDiscPercentage;
    }

    public void setEditDiscPercentage(BigDecimal editDiscPercentage) {
        this.editDiscPercentage = editDiscPercentage;
    }

    public String getEditCreatedBy() {
        return editCreatedBy;
    }

    public void setEditCreatedBy(String editCreatedBy) {
        this.editCreatedBy = editCreatedBy;
    }

    public Timestamp getEditDateCreated() {
        return editDateCreated;
    }

    public void setEditDateCreated(Timestamp editDateCreated) {
        this.editDateCreated = editDateCreated;
    }

    public String getEditLastUpdatedBy() {
        return editLastUpdatedBy;
    }

    public void setEditLastUpdatedBy(String editLastUpdatedBy) {
        this.editLastUpdatedBy = editLastUpdatedBy;
    }

    public Timestamp getEditLastUpdated() {
        return editLastUpdated;
    }

    public void setEditLastUpdated(Timestamp editLastUpdated) {
        this.editLastUpdated = editLastUpdated;
    }

    // constructor

    public CouponRequest() {}

    public CouponRequest(Integer editCouponId, String editCouponCode, String editCouponName, Integer editCouponStatus, Date editStartDt, Date editEndDt, Integer editDiscType, Integer editDiscAmount, BigDecimal editDiscPercentage, String editCreatedBy, Timestamp editDateCreated, String editLastUpdatedBy, Timestamp editLastUpdated) {
        this.editCouponId = editCouponId;
        this.editCouponCode = editCouponCode;
        this.editCouponName = editCouponName;
        this.editCouponStatus = editCouponStatus;
        this.editStartDt = editStartDt;
        this.editEndDt = editEndDt;
        this.editDiscType = editDiscType;
        this.editDiscAmount = editDiscAmount;
        this.editDiscPercentage = editDiscPercentage;
        this.editCreatedBy = editCreatedBy;
        this.editDateCreated = editDateCreated;
        this.editLastUpdatedBy = editLastUpdatedBy;
        this.editLastUpdated = editLastUpdated;
    }

    @Override
    public String toString() {
        return "CouponRequest{" +
                "editCouponId=" + editCouponId +
                ", editCouponCode='" + editCouponCode + '\'' +
                ", editCouponName='" + editCouponName + '\'' +
                ", editCouponStatus=" + editCouponStatus +
                ", editStartDt=" + editStartDt +
                ", editEndDt=" + editEndDt +
                ", editDiscType=" + editDiscType +
                ", editDiscAmount=" + editDiscAmount +
                ", editDiscPercentage=" + editDiscPercentage +
                ", editCreatedBy='" + editCreatedBy + '\'' +
                ", editDateCreated=" + editDateCreated +
                ", editLastUpdatedBy='" + editLastUpdatedBy + '\'' +
                ", editLastUpdated=" + editLastUpdated +
                '}';
    }
}
