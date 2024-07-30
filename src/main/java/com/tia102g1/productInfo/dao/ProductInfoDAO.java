package com.tia102g1.productInfo.dao;

import java.util.List;
import java.util.Map;

import com.tia102g1.productInfo.entity.ProductInfo;

public interface ProductInfoDAO {
	
	int insert(ProductInfo productInfo);
	int update(ProductInfo productInfo);
	int updateProStatus(Integer productId, Integer proStatus);
	
	ProductInfo getByProductId(Integer productId); // 單筆查詢
	
	List<ProductInfo> getAll(); // 多筆查詢 //動態陣列=> 集合(不用煩惱長度大小)，且有順序性
	List<ProductInfo> getAll(int currentPage);
	List<ProductInfo> getByCompositeQuery(Map<String, String> map);
	
	long getTotal();
}
