package com.ouyangwei.multimodule.restful.login.services.impls;

import com.alibaba.fastjson.JSONObject;
import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import com.ouyangwei.multimodule.restful.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public User getOuyangwei() {
        User user = null;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String userString = (String) valueOperations.get("ouyangwei");
        if(userString != null){
            user = JSONObject.parseObject(userString,User.class);
        } else {
            user = userMapper.getOuyangwei();
            valueOperations.set("ouyangwei",JSONObject.toJSON(user).toString());
        }
        return user;
    }
}
