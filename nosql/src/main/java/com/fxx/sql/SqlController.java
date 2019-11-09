package com.fxx.sql;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * nosql  测试
 */
@RestController
@RequestMapping("/nosql")
public class SqlController {


    @GetMapping("/testCreateTable")
    public  void testCreateTable() {
//        hbaseService.getValue();
    }

}
