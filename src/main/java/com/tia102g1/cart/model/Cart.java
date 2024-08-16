package com.tia102g1.cart.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Cart {

    private Integer cartId;
    private Integer memberId;
    private Integer productId;
    private Integer proAmount;
    private Date joinDt;

}
