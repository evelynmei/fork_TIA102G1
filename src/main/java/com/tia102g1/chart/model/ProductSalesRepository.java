package com.tia102g1.chart.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tia102g1.orderlistinfo.model.OrderListInfoVO;

public interface ProductSalesRepository extends JpaRepository<OrderListInfoVO, Integer> {
	@Query("SELECT new com.tia102g1.chart.model.ProductSalesVO(p.proName, SUM(o.proQuantity)) " +
	           "FROM OrderListInfoVO o JOIN o.productInfo p " +
	           "GROUP BY p.proName")
	  List<ProductSalesVO> findProductSalesData();
}
