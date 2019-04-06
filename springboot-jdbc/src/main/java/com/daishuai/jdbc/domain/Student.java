package com.daishuai.jdbc.domain;

import com.daishuai.jdbc.annotation.TableConfig;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:33
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@TableConfig("student")
public class Student {
    private String name;

    private Date birthday;

    private String address;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
