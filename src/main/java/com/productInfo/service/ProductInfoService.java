package com.productInfo.service;

import java.util.List;
import java.util.Map;

import com.productInfo.entity.ProductInfo;

public interface ProductInfoService {
	ProductInfo addProductInfo (ProductInfo productInfo);
	ProductInfo updateProductInfo (ProductInfo productInfo);
	void updateProStatus(Integer productId, Integer proStatus);
	ProductInfo getProductInfoByProductId (Integer productId);
	List<ProductInfo> getAllProductInfo(); 
	List<ProductInfo> getAllProductInfo(int currentPage);
	List<ProductInfo> getProductInfoByCompositeQuery(Map<String, String[]> map);
	int getPageTotal();
}
