package com.fxx.helper;

import com.fxx.util.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Description: 数据库操作工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/6/3 10:13
 * @Version: 1.0
 */
public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 开始事务
     */
    public static void beginTransaction(){
        Connection connection = DBUtils.getConnection();
        if (Objects.nonNull(connection)){
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.error("开始事务失败"+e);
                throw new RuntimeException(e);
            }finally {

            }

        }
    }


    /**
     * 提交事务
     */
    public static void commitTransaction(){
        Connection connection = DBUtils.getConnection();
        if (Objects.nonNull(connection)){
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.error("提交事务失败" + e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction(){
        Connection connection = DBUtils.getConnection();
        if (Objects.nonNull(connection)){
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("回滚事务失败" + e);
                throw new RuntimeException(e);
            }
        }
    }

}
