package com.ouyangwei.multimodule.restful.login;

import com.alibaba.druid.pool.DruidDataSource;
import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class RestfulLoginTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

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
    public void userMapperTest(){
        System.out.println("========Test: userMapperTest() begin===========");
        User user = userMapper.getOuyangwei();
        System.out.println(user);
        System.out.println("========Test: userMapperTest() end===========");
    }
}
