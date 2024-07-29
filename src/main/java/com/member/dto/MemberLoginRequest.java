package com.member.dto;

import javax.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;

public class MemberLoginRequest {
    @NotBlank
    private String account;
    @NotBlank
    private String password;

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
}
