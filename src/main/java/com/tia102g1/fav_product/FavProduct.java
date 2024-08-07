package com.tia102g1.fav_product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;


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
    private Integer favProductId;


//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "MEMBERID", nullable = false)
//    private Member memberId;
//    @NotNull
    @Column(name = "memberId")
    private Integer memberId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "PRODUCTID", nullable = false)
//    private ProductInfo productId;
    @NotNull
    private Integer productId;

    @CreatedDate
    @Column(name = "JOINDT", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date joinDt;

}