package com.example.demo;

import java.util.Date;

/**
 * @Description: 实体类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/8 10:56
 * @Version: 1.0
 */
public class Dto {

    private Date date;

    private String fei;

    private Double aDouble;

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFei() {
        return fei;
    }

    public void setFei(String fei) {
        this.fei = fei;
    }
}
