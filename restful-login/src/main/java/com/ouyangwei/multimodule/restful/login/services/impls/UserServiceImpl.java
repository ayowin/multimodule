package com.ouyangwei.multimodule.restful.login.services.impls;

import com.ouyangwei.multimodule.dao.entities.User;
import com.ouyangwei.multimodule.dao.mappers.UserMapper;
import com.ouyangwei.multimodule.restful.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getOuyangwei() {
        return userMapper.getOuyangwei();
    }
}
