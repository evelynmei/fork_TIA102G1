package com.tia102g1.fav_product;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tia102g1.member.model.Member;
import com.tia102g1.productinfo.entity.ProductInfo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "favProduct")
public class FavProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAVPRODUCTID", columnDefinition = "int UNSIGNED not null")
    private Integer favProduct;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBERID", nullable = false)
    private Member member;
//    @NotNull
//    private Integer memberId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCTID", nullable = false)
    private ProductInfo productInfo;
//    @NotNull
//    private Integer productId;

//    @CreatedDate
    @Column(name = "JOINDT", insertable = false, updatable = false)
    private Date joinDt;

}