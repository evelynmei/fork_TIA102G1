package com.tia102g1.productinfo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tia102g1.productinfo.entity.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{
	
	 List<ProductInfo> findByProStatus(Integer proStatus);

}
