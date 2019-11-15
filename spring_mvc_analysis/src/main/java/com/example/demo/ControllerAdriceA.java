package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Description: 设置全局属性 aa,bb
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/8 9:45
 * @Version: 1.0
 */
@ControllerAdvice
public class ControllerAdriceA {


    @ModelAttribute(value = "aa",binding = false)
    public String aa(){
        return "aa";
    }

    @ModelAttribute(value = "bb")
    public String bb(){
        return "bb";
    }


}
