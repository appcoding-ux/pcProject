package com.project.pc.controller;

import com.project.pc.dto.OrderFoodDTO;
import com.project.pc.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(value = "/orderFood", method = RequestMethod.POST)
    public String orderFood(OrderFoodDTO orderFoodDTO){
        log.info("회원이 시킨 음식 : " + orderFoodDTO);

        orderService.orderFood(orderFoodDTO);

        return "success";
    }
}
