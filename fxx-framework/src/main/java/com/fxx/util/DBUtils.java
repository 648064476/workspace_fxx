package com.fxx.util;

import com.fxx.config.ConfigHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Description: 数据库工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/6/3 10:50
 * @Version: 1.0
 */
public class DBUtils {
    private static Connection connection;

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            if (Objects.isNull(connection)) {
                Class.forName((String) ConfigHelper.getJdbcDrivre());
                connection = DriverManager.getConnection((String) ConfigHelper.getJdbcUrl(), (String) ConfigHelper.getJdbcUserName(), (String) ConfigHelper.getJdbcPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);

    }
}
