package com.daishuai.thymeleaf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/5/13 23:15
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Person implements Serializable{

    private String firstName;
    private String country;
    private Integer age;
    private Integer gender;
    private Date birthday;

    public Person(String firstName, String country, Integer age, Integer gender, Date birthday) {
        this.firstName = firstName;
        this.country = country;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
