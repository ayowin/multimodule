package com.ouyangwei.multimodule.restful.login.controllers;

import com.ouyangwei.multimodule.restful.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/restful/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "login test";
    }

    @RequestMapping("/ouyangwei")
    public String ouyangwei(){
        return userService.getOuyangwei().toString();
    }
}
