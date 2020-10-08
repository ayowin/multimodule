package com.ouyangwei.multimodule.restful.order.controllers;

import com.ouyangwei.multimodule.restful.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful/order")
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping("/test")
    public String test(){
        return "order test";
    }

    @RequestMapping("/ouyangwei")
    public String ouyangwei(){
        log.info("OrderController: ouyangwei()");
        return orderService.getOrdersByUserId(2).toString();
    }
}
