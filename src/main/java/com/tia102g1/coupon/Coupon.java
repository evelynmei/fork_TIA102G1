package com.tia102g1.coupon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tia102g1.orderlist.model.OrderListVO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPONID", columnDefinition = "int UNSIGNED not null")
    private Integer CouponId;

    @Size(max = 20)
    @Column(name = "COUPONCODE", nullable = false, length = 20)
    private String couponCode;

    @Size(max = 30)
    @Column(name = "COUPONNAME", nullable = false, length = 30)
    private String couponName;

    @Column(name = "COUPONSTATUS", nullable = false)
    private Integer couponStatus;

    @Column(name = "STARTDT", nullable = false)
    private LocalDate startDt;

    @Column(name = "ENDDT", nullable = false)
    private LocalDate endDt;

    @Column(name = "DISCTYPE", nullable = false)
    private Integer discDype;

    @Column(name = "DISCAMOUNT")
    private Integer discAmount;

    @Column(name = "DISCPERCENTAGE", precision = 3, scale = 2)
    private BigDecimal discPercentage;

    @Size(max = 50)
    @Column(name = "CREATEDBY", updatable = false)
    private String createdBy;

    @Column(name = "DATECREATED", insertable = false, updatable = false)
    private Timestamp dateCreated;

    @Size(max = 50)
    @Column(name = "LASTUPDATEDBY")
    private String lastUpdatedby;

    @Column(name = "LASTUPDATED", insertable = false, updatable = false)
    private Timestamp lastUpdated;
    
 // 此優惠券下關聯的訂單明細紀錄
 	@OneToMany(mappedBy = "coupon", fetch = FetchType.EAGER)
 	@OrderBy("orderListId asc")
 	private Set<OrderListVO> orderLists = new HashSet<OrderListVO>();

	public Integer getCouponId() {
		return CouponId;
	}

	public void setCouponId(Integer couponId) {
		CouponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
	}

	public Integer getDiscDype() {
		return discDype;
	}

	public void setDiscDype(Integer discDype) {
		this.discDype = discDype;
	}

	public Integer getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Integer discAmount) {
		this.discAmount = discAmount;
	}

	public BigDecimal getDiscPercentage() {
		return discPercentage;
	}

	public void setDiscPercentage(BigDecimal discPercentage) {
		this.discPercentage = discPercentage;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastUpdatedby() {
		return lastUpdatedby;
	}

	public void setLastUpdatedby(String lastUpdatedby) {
		this.lastUpdatedby = lastUpdatedby;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<OrderListVO> getOrderLists() {
		return orderLists;
	}

	public void setOrderLists(Set<OrderListVO> orderLists) {
		this.orderLists = orderLists;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}