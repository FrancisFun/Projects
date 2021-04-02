package com.aiot.sensor.statistics.entity;

import java.io.Serializable;

/**
 * (DataInformation)实体类
 *
 * @author AIoT
 * @since 2021-02-18 10:48:31
 */
public class DataInformation implements Serializable {
    private static final long serialVersionUID = -14967610611033115L;

    private Long id;

    private String name;

    private String unit;

    private String icon;


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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
