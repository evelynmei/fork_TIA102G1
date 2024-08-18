package com.tia102g1.orderlist.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.orderlist.service.OrderListService;
import com.tia102g1.orderlistinfo.model.OrderListInfoVO;

@Controller
public class OrderConfirmController {
	
	Timestamp now = Timestamp.valueOf(LocalDateTime.now());
	
	@Autowired
	private OrderListService orderListService;
	
	@PostMapping("/orderConfirmation")
	public String confirmOrderVO(OrderListVO orderListVO, ModelMap model) {
		
		orderListVO.setOrderDt(now); //訂單日期
		orderListVO.setLastUpdatedBy(orderListVO.getCreatedBy());
		orderListService.updateOrderList(orderListVO); //塞入新建的值
		
		Integer orderId = orderListVO.getOrderListId();
		
		//取得訂單主檔-JPA版
		OrderListVO orderListVO2 = orderListService.getOneOrderList(orderId);
		
		//取得訂單主檔(含明細)-RowMapper版
		OrderListVO orderListRM = orderListService.getOrderById(orderId);	
		List<OrderListInfoVO> orderListInfoVOList = orderListRM.getOrderListInfoVO();
		
		model.addAttribute("orderListVO", orderListVO2); //JPA
		model.addAttribute("orderListInfoVOList", orderListInfoVOList); //RM
		
		return "/frontendapp/orderConfirmation";
	}
	
}
