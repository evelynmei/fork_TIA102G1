package com.tia102g1.productcomment.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productComment")
public class ProductCommentVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proCommentId", updatable = false)
	private Integer proCommentId;
	
//	@ManyToOne
//	@JoinColumn(name = "memberId", referencedColumnName = "memberId")
//	private Member member;	
	@Column(name = "memberId")
	private Integer memberId;	
	
//	@ManyToOne
//	@JoinColumn(name = "productId", referencedColumnName = "productId")
//	private ProductInfo productinfo;	
	@Column(name = "productId")
	private Integer productId;
	
//	@ManyToOne
//	@JoinColumn(name = "orderListInfoId", referencedColumnName = "productIdorderListInfoId")
//	private OrderListInfoVO orderListInfoVO;
	@Column(name = "orderListInfoId")
	private Integer orderListInfoId;
	
//	@ManyToOne
//	@JoinColumn(name = "staffId", referencedColumnName = "staffId")
//	private StaffVO staffVO;
	@Column(name = "staffId")
	private Integer staffId;
	
	@Column(name = "storeReply", columnDefinition = "longtext")
	private String storeReply;
	
	@Column(name = "commentText", columnDefinition = "longtext")
	@NotEmpty(message="評論內容: 請勿空白")
	private String commentText;
	
	@Column(name = "commentPic")
	private byte[] commentPic;
	
	@Column(name = "commentRate")
	@NotNull(message="滿意度: 請勿空白")
	private Integer commentRate;
	
	@Column(name = "commentDate")
	private Date commentDate;
	
	@Column(name = "replyTime")
	private Date replyTime;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "dateCreated")
	private Date dateCreated;
	
	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;
	
	@Column(name = "lastUpdated")
	private Date lastUpdated;

}
