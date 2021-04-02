package com.aiot.web.equipment.controller;

import com.aiot.web.equipment.entity.ModuleInformation;
import com.aiot.web.equipment.service.ModuleInformationService;
import com.aiot.web.statistics.entity.DataInformation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.rmi.MarshalledObject;
import java.util.List;

/**
 * (ModuleInformation)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:16:48
 */
@RestController
@RequestMapping("moduleInformation")
public class ModuleInformationController {
    /**
     * 服务对象
     */
    @Resource
    private ModuleInformationService moduleInformationService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ModuleInformation selectOne(Long id) {
        return this.moduleInformationService.queryById(id);
    }

    /**
     * 查询所有数据
     * @return 数据列表
     */
    @GetMapping("selectAll")
    public List<ModuleInformation> selectAll(){
        return this.moduleInformationService.queryAll();
    }

}
