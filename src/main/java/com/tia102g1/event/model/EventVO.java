package com.tia102g1.event.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;


public class EventVO implements Serializable{
	private Integer eventId;
	private String eventName;
	private Date startDt;
	private Date endDt;
	private Double eventDiscount;
	private byte[] eventPic;
	private Integer status;
	private String eventContent;
	private String createdBy;
	private Timestamp dateCreated;
	private String lastUpdatedBy;
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
