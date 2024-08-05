package com.tia102g1.orderlist.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g1.dist.model.DistVO;

public interface OrderListRepository extends JpaRepository<OrderListVO, Integer> {

}
