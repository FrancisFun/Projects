package com.aiot.sensor.home.entity;

import java.io.Serializable;

/**
 * (Area)实体类
 *
 * @author AIoT
 * @since 2021-02-18 10:43:10
 */
public class Area implements Serializable {
    private static final long serialVersionUID = -85487894870831892L;

    private Long id;

    private String name;

    private Short type;

    private String location;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
