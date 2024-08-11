package com.tia102g1.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tia102g1.member.constant.AccountStatus;
import com.tia102g1.membercoin.model.MemberCoinVO;
import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.productcomment.model.ProductCommentVO;


@Entity
@Table(name = "MEMBER")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMBERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;

	@Column(name = "MEMBERLVID")
	private Integer memberLvId;

	@Column(name = "STAFFID")
	private Integer staffId;

	@Column(name = "ACCOUNT")
	private String account;

	@JsonIgnore //必須隱藏起來不能再request body顯示給任何人看到
	@Column(name = "PASSWORD")
	private String password;

	@NotEmpty(message = "姓名: 請勿空白")
	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTHDT")
	private Date birthDt;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CNTCODE")
	private Integer cntCode;

	@Column(name = "DISTCODE")
	private Integer distCode;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "ACCUMULATE")
	private Integer accumulate;

	@Column(name = "COINBALANCE")
	private Integer coinBalance;

	@Column(name = "JOINDATE")
	private Date joinDate;

	@Column(name = "NOSHOW")
	private Integer noShow;

	@Column(name = "CARDHOLDER")
	private String cardHolder;

	@Column(name = "CARDNUMBER")
	private String cardNumber;

	@Column(name = "CARDYY")
	private Integer cardYY;

	@Column(name = "CARDMM")
	private Integer cardMM;

	@Column(name = "CARDVERIFYCODE")
	private String cardVerifyCode;

	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private AccountStatus status;

	@Column(name = "BLOCKEDTIME")
	private Timestamp blockedTime;

	@Column(name = "BLOCKEDREASON")
	private String blockedReason;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "DATECREATED")
	private Timestamp dateCreated;

	@Column(name = "LASTUPDATEDBY")
	private String lastUpdatedBy;

	@Column(name = "LASTUPDATED")
	private Timestamp lastUpdated;
	
	//此會員下的購物金持有紀錄
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER)
	@OrderBy("memCoinId asc")
	private Set<MemberCoinVO> memCoins = new HashSet<MemberCoinVO>();
	
	//此會員下關聯的訂單明細紀錄
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER)
	@OrderBy("orderListId asc")
	private Set<OrderListVO> orderLists = new HashSet<OrderListVO>();
	
	//此會員下關聯的商品評價紀錄
		@OneToMany(mappedBy = "member", fetch=FetchType.EAGER)
		@OrderBy("proCommentId asc")
		private Set<ProductCommentVO> productCommentVO = new HashSet<ProductCommentVO>();


	public Member() {
		super();
	}


	public Member(Integer memberId, Integer memberLvId, Integer staffId, String account, String password,
			@NotEmpty(message = "姓名: 請勿空白") String name, Date birthDt, String phone, String email, Integer cntCode,
			Integer distCode, String address, Integer accumulate, Integer coinBalance, Date joinDate, Integer noShow,
			String cardHolder, String cardNumber, Integer cardYY, Integer cardMM, String cardVerifyCode,
			AccountStatus status, Timestamp blockedTime, String blockedReason, String createdBy, Timestamp dateCreated,
			String lastUpdatedBy, Timestamp lastUpdated, Set<MemberCoinVO> memCoins, Set<OrderListVO> orderLists,
			Set<ProductCommentVO> productCommentVO) {
		super();
		this.memberId = memberId;
		this.memberLvId = memberLvId;
		this.staffId = staffId;
		this.account = account;
		this.password = password;
		this.name = name;
		this.birthDt = birthDt;
		this.phone = phone;
		this.email = email;
		this.cntCode = cntCode;
		this.distCode = distCode;
		this.address = address;
		this.accumulate = accumulate;
		this.coinBalance = coinBalance;
		this.joinDate = joinDate;
		this.noShow = noShow;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.cardYY = cardYY;
		this.cardMM = cardMM;
		this.cardVerifyCode = cardVerifyCode;
		this.status = status;
		this.blockedTime = blockedTime;
		this.blockedReason = blockedReason;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdated = lastUpdated;
		this.memCoins = memCoins;
		this.orderLists = orderLists;
		this.productCommentVO = productCommentVO;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Integer getMemberLvId() {
		return memberLvId;
	}


	public void setMemberLvId(Integer memberLvId) {
		this.memberLvId = memberLvId;
	}


	public Integer getStaffId() {
		return staffId;
	}


	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getBirthDt() {
		return birthDt;
	}


	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getCntCode() {
		return cntCode;
	}


	public void setCntCode(Integer cntCode) {
		this.cntCode = cntCode;
	}


	public Integer getDistCode() {
		return distCode;
	}


	public void setDistCode(Integer distCode) {
		this.distCode = distCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getAccumulate() {
		return accumulate;
	}


	public void setAccumulate(Integer accumulate) {
		this.accumulate = accumulate;
	}


	public Integer getCoinBalance() {
		return coinBalance;
	}


	public void setCoinBalance(Integer coinBalance) {
		this.coinBalance = coinBalance;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public Integer getNoShow() {
		return noShow;
	}


	public void setNoShow(Integer noShow) {
		this.noShow = noShow;
	}


	public String getCardHolder() {
		return cardHolder;
	}


	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public Integer getCardYY() {
		return cardYY;
	}


	public void setCardYY(Integer cardYY) {
		this.cardYY = cardYY;
	}


	public Integer getCardMM() {
		return cardMM;
	}


	public void setCardMM(Integer cardMM) {
		this.cardMM = cardMM;
	}


	public String getCardVerifyCode() {
		return cardVerifyCode;
	}


	public void setCardVerifyCode(String cardVerifyCode) {
		this.cardVerifyCode = cardVerifyCode;
	}


	public AccountStatus getStatus() {
		return status;
	}


	public void setStatus(AccountStatus status) {
		this.status = status;
	}


	public Timestamp getBlockedTime() {
		return blockedTime;
	}


	public void setBlockedTime(Timestamp blockedTime) {
		this.blockedTime = blockedTime;
	}


	public String getBlockedReason() {
		return blockedReason;
	}


	public void setBlockedReason(String blockedReason) {
		this.blockedReason = blockedReason;
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


	public Set<MemberCoinVO> getMemCoins() {
		return memCoins;
	}


	public void setMemCoins(Set<MemberCoinVO> memCoins) {
		this.memCoins = memCoins;
	}


	public Set<OrderListVO> getOrderLists() {
		return orderLists;
	}


	public void setOrderLists(Set<OrderListVO> orderLists) {
		this.orderLists = orderLists;
	}


	public Set<ProductCommentVO> getProductCommentVO() {
		return productCommentVO;
	}


	public void setProductCommentVO(Set<ProductCommentVO> productCommentVO) {
		this.productCommentVO = productCommentVO;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberLvId=" + memberLvId + ", staffId=" + staffId + ", account="
				+ account + ", password=" + password + ", name=" + name + ", birthDt=" + birthDt + ", phone=" + phone
				+ ", email=" + email + ", cntCode=" + cntCode + ", distCode=" + distCode + ", address=" + address
				+ ", accumulate=" + accumulate + ", coinBalance=" + coinBalance + ", joinDate=" + joinDate + ", noShow="
				+ noShow + ", cardHolder=" + cardHolder + ", cardNumber=" + cardNumber + ", cardYY=" + cardYY
				+ ", cardMM=" + cardMM + ", cardVerifyCode=" + cardVerifyCode + ", status=" + status + ", blockedTime="
				+ blockedTime + ", blockedReason=" + blockedReason + ", createdBy=" + createdBy + ", dateCreated="
				+ dateCreated + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdated=" + lastUpdated + ", memCoins="
				+ memCoins + ", orderLists=" + orderLists + ", productCommentVO=" + productCommentVO + "]";
	}


	
}
