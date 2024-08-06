package com.tia102g1.orderlistinfo.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.productinfo.entity.ProductInfo;


@Entity
@Table(name = "orderlistinfo")
public class OrderListInfoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderListInfoId", updatable = false)
	private Integer orderListInfoId;
	
	@Column(name = "orderListId")
	private Integer orderListId;
//	@ManyToOne
//	@JoinColumn(name = "orderListId", referencedColumnName = "orderListId")
//	private OrderListVO orderListVO;
	
	@Column(name = "productId")
	private Integer productId;
//	@ManyToOne
//	@JoinColumn(name = "productId", referencedColumnName = "productId")
//	private ProductInfo productInfo;
	
	
	@Column(name = "purchasedPrice")
	private Integer purchasedPrice;
	
	@Column(name = "proQuantity")
	@DecimalMin(value = "0", message = "商品數量 : 不能小於{value}")
	private Integer proQuantity;
	
	@Column(name = "createdBy", updatable = false)
	private String createdBy;
	
	@Column(name = "dateCreated", insertable = false, updatable = false)
	private Date dateCreated;
	
	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;
	
	@Column(name = "lastUpdated", insertable = false, updatable = false)
	private Date lastUpdated;

	public OrderListInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderListInfoVO(Integer orderListInfoId, Integer orderListId, Integer productId, Integer purchasedPrice,
			@DecimalMin(value = "0", message = "商品數量 : 不能小於{value}") Integer proQuantity, String createdBy,
			Date dateCreated, String lastUpdatedBy, Date lastUpdated) {
		super();
		this.orderListInfoId = orderListInfoId;
		this.orderListId = orderListId;
		this.productId = productId;
		this.purchasedPrice = purchasedPrice;
		this.proQuantity = proQuantity;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdated = lastUpdated;
	}

	public Integer getOrderListInfoId() {
		return orderListInfoId;
	}

	public void setOrderListInfoId(Integer orderListInfoId) {
		this.orderListInfoId = orderListInfoId;
	}

	public Integer getOrderListId() {
		return orderListId;
	}

	public void setOrderListId(Integer orderListId) {
		this.orderListId = orderListId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPurchasedPrice() {
		return purchasedPrice;
	}

	public void setPurchasedPrice(Integer purchasedPrice) {
		this.purchasedPrice = purchasedPrice;
	}

	public Integer getProQuantity() {
		return proQuantity;
	}

	public void setProQuantity(Integer proQuantity) {
		this.proQuantity = proQuantity;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	
	
}
