package com.tia102g1.cart;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "productinfo")
public class CartPrd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTID", columnDefinition = "int UNSIGNED not null")
    private Integer prdId;

    @NotNull
    @Column(name = "PRODUCTTYPEID", nullable = false)
    private Integer prdTypeId;

    @NotNull
    @Column(name = "PRONAME", nullable = false)
    private String prdName;

    @NotNull
    @Column(name = "PROPRICE", nullable = false)
    private Integer prdPrice;

    @NotNull
    @Column(name = "PROSAFETYSTOCK", nullable = false)
    private Integer prdSafetyStock;

    @NotNull
    @Column(name = "TOTALCOUNT", nullable = false)
    private Integer totalC;

    @Column(name = "COMMENTUSERS")
    private Integer cmtUsers;

    @Column(name = "COMMENTSTARS")
    private Integer cmtStars;

    @Column(name = "PROPIC")
    private byte[] prdPic;

    @NotNull
    @Column(name = "PROSTATUS", nullable = false)
    private Integer prdSts;

    @NotNull
    @Column(name = "PRODESC", nullable = false)
    private String prdDesc;

    @NotNull
    @Column(name = "CREATEDBY", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "DATECREATED", nullable = false)
    private Timestamp dateCreated;

    @NotNull
    @Column(name = "LASTUPDATEDBY", nullable = false)
    private String lastUpdatedBy;

    @NotNull
    @Column(name = "LASTUPDATED", nullable = false)
    private Timestamp lastUpated;

}