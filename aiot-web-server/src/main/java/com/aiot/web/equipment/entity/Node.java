package com.aiot.web.equipment.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/19
 */
@Data
public class Node {
    private Long id;
    private String deviceId;
    private Short status;
    private List<ModuleVO> modules;
}
