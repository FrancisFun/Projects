package com.aiot.web.statistics.entity;

import com.aiot.web.home.entity.Area;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Frank
 * @Date: 2021/2/19
 */
@Data
public class DataVO {
    private Area area;
    private Double value;
    private Date timestamp;
}
