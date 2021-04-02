package com.aiot.web.user.entity;

import java.io.Serializable;

/**
 * (UserArea)实体类
 *
 * @author AIoT
 * @since 2021-02-04 17:13:44
 */
public class UserArea implements Serializable {
    private static final long serialVersionUID = -81305904125630486L;

    private Long id;

    private Long user;

    private Long area;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

}
