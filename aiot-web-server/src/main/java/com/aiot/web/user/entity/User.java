package com.aiot.web.user.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author AIoT
 * @since 2021-02-04 17:13:17
 */
public class User implements Serializable {
    private static final long serialVersionUID = 490086566172588381L;

    private Long id;

    private String username;

    @JSONField(serialize = false)
    private String password;

    private String role;

    private Short status;

    private String avatar;

    private String email;

    private String phone;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
