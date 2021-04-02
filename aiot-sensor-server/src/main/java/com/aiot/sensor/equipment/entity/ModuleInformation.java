package com.aiot.sensor.equipment.entity;

import java.io.Serializable;

/**
 * (ModuleInformation)实体类
 *
 * @author AIoT
 * @since 2021-02-18 10:47:48
 */
public class ModuleInformation implements Serializable {
    private static final long serialVersionUID = 270261732245733592L;

    private Long id;

    private Long data;

    private String name;

    private String icon;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
