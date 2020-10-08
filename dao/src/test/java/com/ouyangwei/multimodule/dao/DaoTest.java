package com.ouyangwei.multimodule.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.ouyangwei.multimodule.dao.entities.Order;
import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.OrderMapper;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import com.ouyangwei.multimodule.dao.utils.RedisUtil;
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
