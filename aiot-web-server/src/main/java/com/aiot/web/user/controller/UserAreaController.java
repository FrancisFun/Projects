package com.aiot.web.user.controller;

import com.aiot.web.user.entity.UserArea;
import com.aiot.web.user.service.UserAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserArea)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:13:45
 */
@RestController
@RequestMapping("userArea")
public class UserAreaController {
    /**
     * 服务对象
     */
    @Resource
    private UserAreaService userAreaService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserArea selectOne(Long id) {
        return this.userAreaService.queryById(id);
    }

}
