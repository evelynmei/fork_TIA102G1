package com.tia102g1.productinfo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g1.productinfo.entity.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{

}
