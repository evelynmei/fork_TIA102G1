package com.tia102g1.orderlist.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderListVO, Integer> {
	
	List<OrderListVO> findByOrderStatus(Integer orderStatus);
	List<OrderListVO> findByPaymentStatus(Integer paymentStatus);

}
