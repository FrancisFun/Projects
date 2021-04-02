package com.aiot.web.home.controller;

import com.aiot.web.home.entity.Area;
import com.aiot.web.home.service.AreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Area)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:12:36
 */
@RestController
@RequestMapping("area")
public class AreaController {
    /**
     * 服务对象
     */
    @Resource
    private AreaService areaService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Area selectOne(Long id) {
        return this.areaService.queryById(id);
    }

}
