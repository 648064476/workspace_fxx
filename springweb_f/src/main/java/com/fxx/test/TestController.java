package com.fxx.test;

import com.fxx.annotation.*;

import java.sql.SQLException;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/27 15:44
 * @Version: 1.0
 */
@RestController
@RequestMapping("/fex")
public class TestController {

    @Autowired
    private MyService myService;

    @RequestMapping("/hello")
    public Student hello(@RequestBody Student student,String city,@RequestParam("color123") String blue){
        System.out.println(city+blue);
        return student;
    }

    @PostMapping("/hello1")
    public String hello1(@RequestBody Student student,String bbb) throws SQLException {

        return myService.getStudent();
    }

    public static void main(String[] args) {

    }
}
