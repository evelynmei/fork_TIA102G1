package com.tia102g1.productInfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tia102g1.productInfo.dao.ProductInfoDAO;
import com.tia102g1.productInfo.dao.ProductInfoDAOImpl;
import com.tia102g1.productInfo.entity.ProductInfo;


public class ProductInfoServiceImpl implements ProductInfoService{
	
	private ProductInfoDAO dao;
	
	public ProductInfoServiceImpl() {
		dao = new ProductInfoDAOImpl();
	}

	@Override
	public ProductInfo addProductInfo(ProductInfo productInfo) {
		dao.insert(productInfo);
		return productInfo;
	}

	@Override
	public ProductInfo updateProductInfo(ProductInfo productInfo) {
		dao.update(productInfo);
		return productInfo;
	}

	@Override
	public void updateProStatus(Integer productId, Integer proStatus) {
		dao.updateProStatus(productId, proStatus);		
	}

	@Override
	public ProductInfo getProductInfoByProductId(Integer productId) {
		ProductInfo productInfo = dao.getByProductId(productId);
		return productInfo;
	}

	@Override
	public List<ProductInfo> getAllProductInfo() {
		List<ProductInfo> list = dao.getAll();
		return list;
	}

	@Override
	public List<ProductInfo> getAllProductInfo(int currentPage) {
		List<ProductInfo> list = dao.getAll(currentPage);
		return list;
	}

	@Override
	public List<ProductInfo> getProductInfoByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();

			if ("action".equals(key)) {
				continue;
			}

			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);

		return dao.getByCompositeQuery(query);		
		
	}

	@Override
	public int getPageTotal() {
		int pageQty;
		int pageMax = 5;
		long total = dao.getTotal();
		pageQty = (int) (total % pageMax == 0 ? (total / pageMax) : (total / pageMax + 1));
		return pageQty;
	}

}
