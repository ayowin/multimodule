package com.ouyangwei.multimodule.restful.order.controllers;

import com.ouyangwei.multimodule.restful.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/test")
    public String test(){
        return "order test";
    }

    @RequestMapping("/ouyangwei")
    public String ouyangwei(){
        return orderService.getOrdersByUserId(2).toString();
    }
}
