package com.ouyangwei.multimodule.dao;

import com.alibaba.fastjson.JSONObject;
import com.ouyangwei.multimodule.dao.entities.Order;
import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.OrderMapper;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import com.ouyangwei.multimodule.dao.utils.RedisUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class DaoTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserMapper userMapper;

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
    public void redisTest(){
        System.out.println("========Test: redisTest() begin===========");
        User user = userMapper.getOuyangwei();

        ValueOperations valueOperations = redisTemplate.opsForValue();
        /* 写 */
        /**
         * 方式一：
         * String序列化器
         **/
        valueOperations.set("ouyangwei", JSONObject.toJSON(user).toString());
        /**
         * 方式二：
         * Jackson2Json序列化器
         **/
//        redisTemplate.opsForValue().set("ouyangwei", user);

        /* 读 */
        Object object = valueOperations.get("ouyangwei");
        System.out.println(object);
        System.out.println("========Test: redisTest() end===========");
    }

    @Test
    public void redisUtilTest(){
        System.out.println("========Test: redisUtilTest() begin===========");
        User user = userMapper.getOuyangwei();
        redisUtil.set("ouyangwei",JSONObject.toJSON(user).toString());
        Object object = redisUtil.get("ouyangwei");
        System.out.println(object);
        System.out.println("========Test: redisUtilTest() end===========");
    }

    @Test
    public void userMapperTest(){
        System.out.println("========Test: userMapperTest() begin===========");
        User user = userMapper.getOuyangwei();
        System.out.println(user);
        System.out.println("========Test: userMapperTest() end===========");
    }

    @Test
    public void orderMapperTest(){
        System.out.println("========Test: orderMapperTest() begin===========");
        List<Order> orderList = orderMapper.getOrdersByUserId(userMapper.getOuyangwei().getId());
        System.out.println(orderList);
        System.out.println("========Test: orderMapperTest() end===========");
    }
}
