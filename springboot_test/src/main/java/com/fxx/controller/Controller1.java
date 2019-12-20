package com.fxx.controller;

import com.fxx.dto.White;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/aaa")
public class Controller1 implements ApplicationContextAware {


    ApplicationContext applicationContext;

    @Autowired
    private White white;

    @GetMapping("date")
    public White getDate(White white){
        return white;
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
