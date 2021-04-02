package com.aiot.sensor.statistics.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Data)实体类
 *
 * @author AIoT
 * @since 2021-02-18 22:06:50
 */
public class Data implements Serializable {
    private static final long serialVersionUID = -36467243858619783L;

    private Long id;

    private Long device;

    private Long information;

    private Date timestamp;

    private Double value;


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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
