package com.fxx.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;


@Component
public class White implements Condition {

//    @Value("#{name + '哈哈哈'}")
    private String name;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Value("#{T(com.fxx.util.DateUtil).endDate}")
    private String date;

    public White () {
        System.out.println("WHITE 被加载");
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    /**
     * true则加载 false不加载
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches (ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}
