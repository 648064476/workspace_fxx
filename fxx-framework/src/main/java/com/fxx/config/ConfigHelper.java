package com.fxx.config;

import com.fxx.util.PropsUtils;

import java.util.Properties;

/**
 * @Description: 获取配置文件信息
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/14 16:53
 * @Version: 1.0
 */
public class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtils.loadProps(ConfigConstan.CONFIG_FILE);

    /**
     * 获取driver
     * @return
     */
    public static Object getJdbcDrivre(){
        return CONFIG_PROPS.get(ConfigConstan.JDBC_DRIVER);
    }

    /**
     * 获取userName
     * @return
     */
    public static Object getJdbcUserName(){
        return CONFIG_PROPS.get(ConfigConstan.JDBC_USERNAME);
    }

    /**
     * 获取password
     * @return
     */
    public static Object getJdbcPassword(){
        return CONFIG_PROPS.get(ConfigConstan.JDBC_PASSWORD);
    }

    /**
     * 获取Url
     * @return
     */
    public static Object getJdbcUrl(){
        return CONFIG_PROPS.get(ConfigConstan.JDBC_URL);
    }

    /**
     * 获取base_package
     * @return
     */
    public static Object getBasePackage(){
        return CONFIG_PROPS.get(ConfigConstan.JDBC_BASE_PACKAGE);
    }

    /**
     * key
     * @param key
     * @return
     */
    public static Object get(Object key){
        return CONFIG_PROPS.get(key);
    }
}
