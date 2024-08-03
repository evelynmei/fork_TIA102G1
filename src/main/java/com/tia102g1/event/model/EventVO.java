package com.tia102g1.event.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "event")
public class EventVO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventId")
	private Integer eventId;
	
	
	@Column(name = "eventName")
	@NotEmpty(message="活動名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$", message = "活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間")
	private String eventName;
	
	@Column(name = "startDt")
	private Date startDt;
	
	@Column(name = "endDt")
	private Date endDt;
	
	@Column(name = "eventDiscount")
	@NotNull(message="活動折數: 請勿空白")
	@DecimalMin(value = "0.1", message = "活動折數: 不能小於{value}")
	@DecimalMax(value = "10.0", message = "活動折數: 不能超過{value}")
	private Double eventDiscount;
	
	@Column(name = "eventPic")
	private byte[] eventPic;
	
	@Column(name = "status")
	@NotNull(message="活動狀態: 請勿空白")
	private Integer status;
	
	@Column(name = "eventContent")
	@NotEmpty(message="活動內容: 請勿空白")
	private String eventContent;
	
	@Column(name = "createdBy", updatable = false)
	private String createdBy;
	
	@Column(name = "dateCreated", insertable = false, updatable = false)
	private Timestamp dateCreated;
	
	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;
	
	@Column(name = "lastUpdated", insertable = false, updatable = false)
	private Timestamp lastUpdated;
	
	
	public EventVO() {
		super();
	}


	public EventVO(Integer eventId, String eventName, Date startDt, Date endDt, Double eventDiscount, byte[] eventPic,
			Integer status, String eventContent, String createdBy, Timestamp dateCreated, String lastUpdatedBy,
			Timestamp lastUpdated) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.startDt = startDt;
		this.endDt = endDt;
		this.eventDiscount = eventDiscount;
		this.eventPic = eventPic;
		this.status = status;
		this.eventContent = eventContent;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdated = lastUpdated;
	}


	public Integer getEventId() {
		return eventId;
	}


	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public Date getStartDt() {
		return startDt;
	}


	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}


	public Date getEndDt() {
		return endDt;
	}


	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}


	public Double getEventDiscount() {
		return eventDiscount;
	}


	public void setEventDiscount(Double eventDiscount) {
		this.eventDiscount = eventDiscount;
	}


	public byte[] getEventPic() {
		return eventPic;
	}


	public void setEventPic(byte[] eventPic) {
		this.eventPic = eventPic;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getEventContent() {
		return eventContent;
	}


	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
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
