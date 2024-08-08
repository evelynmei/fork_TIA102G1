package com.tia102g1.coupon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @Column(name = "CREATEDBY", updatable = false, nullable = false, length = 50)
    private String createdBy;

    @CreatedDate
    @Column(name = "DATECREATED", insertable = false, nullable = false)
    private Timestamp dateCreated;

    @Size(max = 50)
    @Column(name = "LASTUPDATEDBY", nullable = false, length = 50)
    private String lastUpdatedby;

    @CreatedDate
    @Column(name = "LASTUPDATED", insertable = false, nullable = false)
    private Timestamp lastUpdated;

}