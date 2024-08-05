package com.tia102g1.membercoin.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.tia102g1.member.model.Member;
import com.tia102g1.orderlist.model.OrderListVO;

@Entity
@Table(name = "MEMBERCOIN")
public class MemberCoinVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMCOINID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memCoinId;

//	@Column(name = "MEMBERID")
//	private Integer memberId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBERID", referencedColumnName = "MEMBERID")
	private Member member;
	
//	@Column(name = "ORDERLISTID")
//	private Integer orderListId;
	
	@ManyToOne
	@JoinColumn(name = "ORDERLISTID", referencedColumnName = "orderListId")
	private OrderListVO orderListVO;

	@Column(name = "GETDT")
	private Date getDt;

	@NotEmpty(message = "摘要: 請勿空白")
	@Column(name = "SUMMARY")
	private String summary;

	@Column(name = "TYPE")
	private Integer type;

	@Column(name = "AMOUNT")
	private Integer amount;

	@Column(name = "EXPIRYDT")
	private Date expiryDt;

	@Column(name = "CREATEDBY", updatable = false)
	private String createdBy;

	@Column(name = "DATECREATED", insertable = false, updatable = false)
	private Timestamp dateCreated;

	@Column(name = "LASTUPDATEDBY")
	private String lastUpdatedBy;

	@Column(name = "LASTUPDATED", insertable = false, updatable = false)
	private Timestamp lastUpdated;
	
	public MemberCoinVO() {
		super();
	}
	
	public MemberCoinVO(Integer memCoinId, Member memberVO, OrderListVO orderListVO, Date getDt,
			@NotEmpty(message = "摘要: 請勿空白") String summary, Integer type, Integer amount, Date expiryDt,
			String createdBy, Timestamp dateCreated, String lastUpdatedBy, Timestamp lastUpdated) {
		super();
		this.memCoinId = memCoinId;
		this.member = member;
		this.orderListVO = orderListVO;
		this.getDt = getDt;
		this.summary = summary;
		this.type = type;
		this.amount = amount;
		this.expiryDt = expiryDt;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdated = lastUpdated;
	}

	public Integer getMemCoinId() {
		return memCoinId;
	}

	public void setMemCoinId(Integer memCoinId) {
		this.memCoinId = memCoinId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public OrderListVO getOrderListVO() {
		return orderListVO;
	}

	public void setOrderListVO(OrderListVO orderListVO) {
		this.orderListVO = orderListVO;
	}

	public Date getGetDt() {
		return getDt;
	}

	public void setGetDt(Date getDt) {
		this.getDt = getDt;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


}
