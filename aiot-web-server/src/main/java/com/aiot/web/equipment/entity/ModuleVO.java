package com.aiot.web.equipment.entity;

import lombok.Data;

/**
 * @Author: Frank
 * @Date: 2021/2/19
 */
@Data
public class ModuleVO {
    private Long id;
    private Short status;
    private String name;
    private String icon;
    private String setting;
}
