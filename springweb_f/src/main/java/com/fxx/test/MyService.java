package com.fxx.test;

import com.fxx.annotation.Service;
import com.fxx.annotation.Transaction;
import com.fxx.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/28 15:30
 * @Version: 1.0
 */
@Service
public class MyService {

    @Transaction
    public String getStudent() throws SQLException {
        String sql = "insert into TB_CUSTOMER(customer_no) VALUE('123123')";

        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        boolean execute = preparedStatement.execute();
        int q = 9 / 0;
        if (execute){
            System.out.println("插入成功");
        }
        return "插入成功";
    }
}
