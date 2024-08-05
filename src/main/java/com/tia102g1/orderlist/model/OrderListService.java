package com.tia102g1.orderlist.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderListService")
public class OrderListService {
	
	@Autowired
	OrderListRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	// 單筆查詢
	public OrderListVO getOneOrderList(Integer orderListId) {
		Optional<OrderListVO> optional = repository.findById(orderListId);
		return optional.orElse(null); // 如果有查到資料就回傳VO物件,否則就回傳null
	}

	// 全部查詢
	public List<OrderListVO> getAll() {
		return repository.findAll();
	}

	// 複合查詢
//	public List<OrderListVO> getAll(Map<String, String[]> map){
//		return CompositeQuery_OrderList.getAllC(map, sessionFactory.openSession());
//	}
}
