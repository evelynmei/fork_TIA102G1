package com.tia102g1.coupon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPONID", columnDefinition = "int UNSIGNED not null")
    private Integer CouponId;

    @Size(max = 20)
    @Column(name = "COUPONCODE", nullable = false, length = 20)
    private String couponCode;

    @Size(max = 30)
    @Column(name = "COUPONNAME", nullable = false, length = 30)
    private String couponName;

    @Column(name = "COUPONSTATUS", nullable = false)
    private Integer couponStatus;

    @Column(name = "STARTDT", nullable = false)
    private LocalDate startDt;

    @Column(name = "ENDDT", nullable = false)
    private LocalDate endDt;

    @Column(name = "DISCTYPE", nullable = false)
    private Integer discDype;

    @Column(name = "DISCAMOUNT")
    private Integer discAmount;

    @Column(name = "DISCPERCENTAGE", precision = 3, scale = 2)
    private BigDecimal discPercentage;

    @Size(max = 50)
    @Column(name = "CREATEDBY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "DATECREATED", nullable = false)
    private Instant dateCreated;

    @Size(max = 50)
    @Column(name = "LASTUPDATEDBY", nullable = false, length = 50)
    private String lastUpdatedby;

    @Column(name = "LASTUPDATED", nullable = false)
    private Instant lastUpdated;

}