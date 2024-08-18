package com.tia102g1.orderlist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tia102g1.orderlist.dto.BuyItem;
import com.tia102g1.orderlist.dto.CreateOrderListRequest;
import com.tia102g1.orderlist.dto.OrderListQueryParams;
import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.orderlist.service.OrderListService;

@RestController
public class OrderListControllerV2 {

	@Autowired
	private OrderListService orderListService;

	// 取得所有歷史訂單
	@GetMapping("/members/{memberId}/orderLists")
	public ResponseEntity<List<OrderListVO>> getOrderLists(@PathVariable Integer memberId) {
		OrderListQueryParams orderListQueryParams = new OrderListQueryParams();
		orderListQueryParams.setMemberId(memberId);

		// 取得訂單主檔list
		List<OrderListVO> orderListList = orderListService.getOrders(orderListQueryParams);

		return ResponseEntity.status(HttpStatus.OK).body(orderListList);
	}

	// 取得單筆訂單
	@GetMapping("/members/{memberId}/orderLists/{orderListId}")
	public ResponseEntity<OrderListVO> getOrderById(@PathVariable Integer orderListId) {
		
		OrderListVO orderList = orderListService.getOrderById(orderListId);
		
		return ResponseEntity.status(HttpStatus.OK).body(orderList);
	}
	
	
	// 新增訂單主檔
	@PostMapping("/members/{memberId}/orderLists")
	public ResponseEntity<?> createOrderList(@PathVariable Integer memberId,
			@RequestBody @Valid CreateOrderListRequest createOrderListRequest) {

		Integer orderListId = orderListService.createOrderList(memberId, createOrderListRequest);
		OrderListVO orderList = orderListService.getOrderById(orderListId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderListId);
	}

}
