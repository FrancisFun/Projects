package com.aiot.web.statistics.entity;

import com.aiot.web.home.entity.Area;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/22
 */
@Data
public class ComplexDataVO {
    private Area area;
    private Date timestamp;
    private List<SimpleData> dataList;
}
