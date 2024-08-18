package com.tia102g1.orderlist.controller;

import com.tia102g1.orderlist.dto.OrderListQueryParams;
import com.tia102g1.orderlist.model.OrderListVO;
import com.tia102g1.orderlist.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThymeleafOrderListController {

    @Autowired
    private OrderListService orderListService;

    @GetMapping("/member/orders")
    public String showOrderList(Model model) {
        // 初始化查詢參數
        OrderListQueryParams orderListQueryParams = new OrderListQueryParams();

        // 直接從 Model 中獲取 memberId (有全局控制器，所以不需要使用authentication取登入中的帳號)
        //直接注入memberId到查詢參數中即可
        Integer memberId = (Integer) model.getAttribute("memberId");
        if (memberId != null) {
            // 設置查詢參數的 memberId
            orderListQueryParams.setMemberId(memberId);
        }

        // 查詢該會員訂單列表
        List<OrderListVO> orderList = orderListService.getOrders(orderListQueryParams);
        model.addAttribute("orders", orderList);

        return "frontendapp/orderList";
    }
}
