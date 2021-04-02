package com.aiot.web.statistics.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Frank
 * @Date: 2021/3/1
 */
@Data
public class DataExportVO {
    @ExcelProperty(value = "区域", index = 0)
    private String name;

    @ExcelProperty(value = "采样时间", index = 1)
    private Date timestamp;

    @ExcelProperty(value = "空气温度（℃）", index = 2)
    private Double temperature;

    @ExcelProperty(value = "空气湿度（%）", index = 3)
    private Double humidity;

    @ExcelProperty(value = "光照强度（lx）", index = 4)
    private Double light;
}
