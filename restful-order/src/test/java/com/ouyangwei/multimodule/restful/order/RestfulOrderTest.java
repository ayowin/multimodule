package com.ouyangwei.multimodule.restful.order;

import com.alibaba.druid.pool.DruidDataSource;
import com.ouyangwei.multimodule.dao.entities.Order;
import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.OrderMapper;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class RestfulOrderTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void testDruidDataSource() throws SQLException {
        System.out.println("========Test: testDruidDataSource() begin===========");

        System.out.println("datasource: " + dataSource.getClass().getName());
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource initialSize: " + druidDataSource.getInitialSize());
        System.out.println("druidDataSource maxActive: " + druidDataSource.getMaxActive());
        System.out.println("druidDataSource filters: " + druidDataSource.getFilterClassNames());
        System.out.println("druidDataSource useGlobalDataSourceStat: " + druidDataSource.isUseGlobalDataSourceStat());

        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println("connection: " + connection);
        //关闭连接
        connection.close();
        System.out.println("========Test: testDruidDataSource() end===========");
    }

    @Test
    public void orderMapperTest(){
        System.out.println("========Test: orderMapperTest() begin===========");
        List<Order> orderList = orderMapper.getOrdersByUserId(2);
        System.out.println(orderList);
        System.out.println("========Test: orderMapperTest() end===========");
    }
}
