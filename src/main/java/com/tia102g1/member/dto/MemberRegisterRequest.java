package com.tia102g1.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class MemberRegisterRequest implements Serializable {


    @NotNull
    Integer memberLvId = 1;

    Integer staffId;

    @NotBlank
    @Size(min = 6, max = 10, message = "帳號長度必須在6-10個字元之間")
    @Pattern(regexp = "^[a-z0-9]*$", message = "帳號只能包含小寫字母和數字")
    String account;

    @NotBlank
    @Size(min = 6, max = 10, message = "密碼長度必須在6-10個字元之間")
    @Pattern(regexp = "^[a-z0-9]*$", message = "密碼只能包含小寫字母和數字")
    String password;

    @NotBlank
    String name;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date birthDt;

    @NotNull
    @Pattern(regexp = "^09\\d{8}$", message = "請輸入09+末8碼")
    String phone;

    @NotNull
    @Email(message = "請輸入正確的Email格式")
    String email;

    @NotNull
    Integer cntCode;

    @NotNull
    Integer distCode;

    @NotNull
    String address;

    @NotNull
    Integer accumulate = 0;

    @NotNull
    Integer coinBalance = 0;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date joinDate;

    @NotNull
    Integer noShow = 0;

    String cardHolder;

    String cardNumber;

    Integer cardYY;

    Integer cardMM;

    String cardVerifyCode;

    @NotNull
    Integer status = 1;

    Timestamp blockedTime;

    String blockedReason;

    @NotNull
    String createdBy;

    Timestamp dateCreated;

    @NotNull
    String lastUpdatedBy;

    Timestamp lastUpdated;

    public MemberRegisterRequest() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(Timestamp blockedTime) {
        this.blockedTime = blockedTime;
    }

    public String getBlockReason() {
        return blockedReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockedReason = blockReason;
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
