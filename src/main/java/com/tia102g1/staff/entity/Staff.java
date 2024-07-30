package com.tia102g1.staff.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staffId", updatable = false)
	private Integer staffId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "employDt", updatable = false)
	private Date employDt;
	
	@Column(name = "leaveDt")
	private Date leaveDt;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "createdBy", updatable = false)
	private String createdBy;
	
	@Column(name = "dateCreated", updatable = false)
	private Timestamp dateCreated;
	
	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;
	
	@Column(name = "lastUpdated")
	private Timestamp lastUpdated;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public Date getEmployDt() {
		return employDt;
	}

	public void setEmployDt(Date employDt) {
		this.employDt = employDt;
	}

	public Date getLeaveDt() {
		return leaveDt;
	}

	public void setLeaveDt(Date leaveDt) {
		this.leaveDt = leaveDt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + ", employDt=" + employDt + ", leaveDt=" + leaveDt
				+ ", status=" + status + ", createdBy=" + createdBy + ", dateCreated=" + dateCreated
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdated=" + lastUpdated + "]";
	}

	
	
}
