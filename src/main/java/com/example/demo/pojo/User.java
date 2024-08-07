package com.example.demo.pojo;


import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private String username;

    private String iphone;

    private Integer sex;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User(Long id, String username, String iphone, Integer sex, String remark) {
        this.id = id;
        this.username = username;
        this.iphone = iphone;
        this.sex = sex;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", iphone='" + iphone + '\'' +
                ", sex=" + sex +
                ", remark='" + remark + '\'' +
                '}';
    }
}
