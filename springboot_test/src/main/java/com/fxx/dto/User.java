package com.fxx.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Document("user")
public class User {

    @Id
    private Long id;

    private String name;

    private String sex;

    private String age;

    private String color;

    private String number;

    private UUID uuid;

    private Boolean flag;

    public Boolean getFlag () {
        return flag;
    }

    public void setFlag (Boolean flag) {
        this.flag = flag;
    }

    public UUID getUuid () {
        return uuid;
    }

    public void setUuid (UUID uuid) {
        this.uuid = uuid;
    }

    public String getColor () {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }

    public String getNumber () {
        return number;
    }

    public void setNumber (String number) {
        this.number = number;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
    }

    public String getAge () {
        return age;
    }

    public void setAge (String age) {
        this.age = age;
    }
}
