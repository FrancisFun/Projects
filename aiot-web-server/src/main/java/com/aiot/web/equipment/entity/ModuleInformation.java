package com.aiot.web.equipment.entity;

import java.io.Serializable;

/**
 * (ModuleInformation)实体类
 *
 * @author AIoT
 * @since 2021-02-19 14:08:41
 */
public class ModuleInformation implements Serializable {
    private static final long serialVersionUID = -38295305132522906L;

    private Long id;

    private Long data;

    private String name;

    private String icon;

    private String chinese;


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

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

}
