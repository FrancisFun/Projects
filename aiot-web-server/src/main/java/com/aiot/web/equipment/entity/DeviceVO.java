package com.aiot.web.equipment.entity;

import com.aiot.web.home.entity.Area;
import lombok.Data;

import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/19
 */
@Data
public class DeviceVO {
    private Long id;
    private Area area;
    private String deviceId;
    private String firmwareVersion;
    private String hardwareVersion;
    private String softwareVersion;
    private String mac;
    private Short status;
    private List<Node> nodes;
}
