package com.tia102g1.store.model;

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
import javax.validation.constraints.Pattern;

import com.google.gson.annotations.Expose;
import com.tia102g1.county.model.CountyVO;
import com.tia102g1.dist.model.DistVO;

@Entity
@Table(name = "STORE")
public class StoreVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOREID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer storeId;

	@Column(name = "STORENAME")
	@NotEmpty(message = "門市名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$", message = "門市名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間")
	private String storeName;

//	@Column(name = "CNTCODE")
//	private Integer cntCode;
	
	@ManyToOne
	@JoinColumn(name = "CNTCODE", referencedColumnName = "CNTCODE")
	private CountyVO countyVO;
	
//	@Column(name = "DISTCODE")
//	private Integer distCode;
	
	@ManyToOne
	@JoinColumn(name = "DISTCODE", referencedColumnName = "DISTCODE")
	private DistVO distVO;

	@Column(name = "STOREADDRESS")
	@NotEmpty(message = "門市地址: 請勿空白")
	private String storeAddress;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "STOREPHONE")
	private String storePhone;

	@Column(name = "OPENINGHOURS")
	private String openingHours;

	@Column(name = "STOREMAIL")
	private String storeMail;

	@Column(name = "CREATEDBY", updatable = false)
	private String createdBy;

	@Column(name = "DATECREATED", insertable = false, updatable = false)
	private Timestamp dateCreated;

	@Column(name = "LASTUPDATEDBY")
	private String lastUpdatedBy;

	@Column(name = "LASTUPDATED", insertable = false, updatable = false)
	private Timestamp lastUpdated;

	public StoreVO() {
		super();
	}
	
	public StoreVO(Integer storeId,
			@NotEmpty(message = "門市名稱: 請勿空白") @Pattern(regexp = "^[(一-龥)(a-zA-Z0-9_)]{1,30}$", message = "門市名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間") String storeName,
			CountyVO countyVO, DistVO distVO, @NotEmpty(message = "門市地址: 請勿空白") String storeAddress, String longitude,
			String latitude, String storePhone, String openingHours, String storeMail, String createdBy,
			Timestamp dateCreated, String lastUpdatedBy, Timestamp lastUpdated) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.countyVO = countyVO;
		this.distVO = distVO;
		this.storeAddress = storeAddress;
		this.longitude = longitude;
		this.latitude = latitude;
		this.storePhone = storePhone;
		this.openingHours = openingHours;
		this.storeMail = storeMail;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdated = lastUpdated;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public CountyVO getCountyVO() {
		return countyVO;
	}

	public void setCountyVO(CountyVO countyVO) {
		this.countyVO = countyVO;
	}

	public DistVO getDistVO() {
		return distVO;
	}

	public void setDistVO(DistVO distVO) {
		this.distVO = distVO;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getStoreMail() {
		return storeMail;
	}

	public void setStoreMail(String storeMail) {
		this.storeMail = storeMail;
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

//	@Override
//	public String toString() {
//		return "StoreVO [storeId=" + storeId + ", storeName=" + storeName + ", countyVO=" + countyVO + ", distVO="
//				+ distVO + ", storeAddress=" + storeAddress + ", longitude=" + longitude + ", latitude=" + latitude
//				+ ", storePhone=" + storePhone + ", openingHours=" + openingHours + ", storeMail=" + storeMail
//				+ ", createdBy=" + createdBy + ", dateCreated=" + dateCreated + ", lastUpdatedBy=" + lastUpdatedBy
//				+ ", lastUpdated=" + lastUpdated + "]";
//	}

}
