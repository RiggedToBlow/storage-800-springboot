package com.storage800.demo.test.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("CLIENT")
public class Client {
    @Id
    int id;


    String name, lastName,mobile;

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
