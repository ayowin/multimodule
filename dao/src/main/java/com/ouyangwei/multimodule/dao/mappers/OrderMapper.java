package com.ouyangwei.multimodule.dao.mappers;

import com.ouyangwei.multimodule.dao.entities.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ouyangwei
 * @since 2020-10-07
 */
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> getOrdersByUserId(int userId);
}
