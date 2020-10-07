package com.ouyangwei.multimodule.restful.order.services;

import com.ouyangwei.multimodule.dao.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(int userId);
}
