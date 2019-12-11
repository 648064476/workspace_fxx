package com.fxx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/aaa")
public class Controller1 {

    @GetMapping("date")
    public Date getDate(){
        return new Date();
    }
}
