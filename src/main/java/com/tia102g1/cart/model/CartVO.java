package com.tia102g1.cart.model;

import java.sql.Date;

public class CartVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cartId;
	private Integer memberId;
	private Integer productId;
	private Integer pAmount;
	private Date joinDt;

	public CartVO() {
		super();
	}

	public CartVO(Integer cartId, Integer memberId, Integer productId, Integer pAmount, Date joinDt) {
		super();
		this.cartId = cartId;
		this.memberId = memberId;
		this.productId = productId;
		this.pAmount = pAmount;
		this.joinDt = joinDt;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getpAmount() {
		return pAmount;
	}

	public void setpAmount(Integer pAmount) {
		this.pAmount = pAmount;
	}

	public Date getJoinDt() {
		return joinDt;
	}

	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}

	@Override
	public String toString() {
		return "Cart {" + cartId + "," + memberId + "," + productId + "," + pAmount + "," + joinDt + "} ";
	}

}
