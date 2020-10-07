package com.ouyangwei.multimodule.restful.order.services.impls;

import com.ouyangwei.multimodule.dao.entities.Order;
import com.ouyangwei.multimodule.dao.mappers.OrderMapper;
import com.ouyangwei.multimodule.restful.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderMapper.getOrdersByUserId(userId);
    }
}
