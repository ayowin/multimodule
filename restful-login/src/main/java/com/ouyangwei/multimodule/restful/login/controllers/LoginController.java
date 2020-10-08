package com.ouyangwei.multimodule.restful.login.controllers;

import com.ouyangwei.multimodule.restful.login.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

@RestController
@RequestMapping("/restful/login")
public class LoginController {

    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "login test";
    }

    @RequestMapping("/ouyangwei")
    public String ouyangwei(){
        log.info("LoginController: ouyangwei()");
        return userService.getOuyangwei().toString();
    }
}
