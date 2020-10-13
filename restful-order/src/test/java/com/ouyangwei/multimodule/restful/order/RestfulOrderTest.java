package com.ouyangwei.multimodule.restful.order;

import com.ouyangwei.multimodule.dao.entities.Order;
import com.ouyangwei.multimodule.dao.mappers.OrderMapper;
import com.zaxxer.hikari.HikariDataSource;
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
    public void testDataSource() throws SQLException {
        System.out.println("========Test: testDataSource() begin===========");

        System.out.println("datasource: " + dataSource.getClass().getName());
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        System.out.println("hikariDataSource MinimumIdle: " + hikariDataSource.getMinimumIdle());
        System.out.println("hikariDataSource MaximumPoolSize: " + hikariDataSource.getMaximumPoolSize());
        System.out.println("hikariDataSource IdleTimeout: " + hikariDataSource.getIdleTimeout());
        System.out.println("hikariDataSource MaxLifetime: " + hikariDataSource.getMaxLifetime());
        System.out.println("hikariDataSource ConnectionTimeout: " + hikariDataSource.getConnectionTimeout());
        System.out.println("hikariDataSource ConnectionTestQuery: " + hikariDataSource.getConnectionTestQuery());

        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println("connection: " + connection);
        //关闭连接
        connection.close();
        System.out.println("========Test: testDataSource() end===========");
    }

    @Test
    public void orderMapperTest(){
        System.out.println("========Test: orderMapperTest() begin===========");
        List<Order> orderList = orderMapper.getOrdersByUserId(2);
        System.out.println(orderList);
        System.out.println("========Test: orderMapperTest() end===========");
    }
}
