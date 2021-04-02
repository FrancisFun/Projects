package com.aiot.web.equipment.entity;

import java.io.Serializable;

/**
 * (Module)实体类
 *
 * @author AIoT
 * @since 2021-02-04 17:16:30
 */
public class Module implements Serializable {
    private static final long serialVersionUID = -62667734496006747L;

    private Long id;

    private Long device;

    private Long information;

    private Short status;

    private String setting;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Long getInformation() {
        return information;
    }

    public void setInformation(Long information) {
        this.information = information;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

}
