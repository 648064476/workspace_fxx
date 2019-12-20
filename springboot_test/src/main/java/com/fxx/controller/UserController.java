package com.fxx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author gantingting
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserList")
    @ResponseBody
    public Object getUserList () {
        try {
            Thread.sleep(2000);
            return new Date();
        } catch (Exception e) {
            return "Errror Message";
        }
    }
}

