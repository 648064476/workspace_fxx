package com.fxx.util;

import com.fxx.config.ConfigHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @Description: 配置文件工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/14 17:04
 * @Version: 1.0
 */
public class PropsUtils {

    private static Properties properties ;


    /**
     * 配置文件加载
     */
    public static Properties loadProps(String configFile) {
        try {
            if (Objects.isNull(properties)){
                properties = new Properties();
                InputStream in = ConfigHelper.class.getClassLoader().getResourceAsStream(configFile);
                properties.load(in);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("配置文件读取异常");
        }
        return properties;
    }


}
