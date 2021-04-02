package com.aiot.web.statistics.controller;

import com.aiot.web.equipment.entity.ModuleInformation;
import com.aiot.web.equipment.service.ModuleInformationService;
import com.aiot.web.statistics.entity.DataInformation;
import com.aiot.web.statistics.service.DataInformationService;
import com.aiot.web.util.Convertor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * (DataInformation)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:16:05
 */
@RestController
@RequestMapping("dataInformation")
public class DataInformationController {
    /**
     * 服务对象
     */
    @Resource
    private DataInformationService dataInformationService;

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
    public DataInformation selectOne(Long id) {
        return this.dataInformationService.queryById(id);
    }

    /**
     * 查询所有数据
     * @return 数据列表
     */
    @GetMapping("selectAll")
    public List<DataInformation> selectAll(){
        return this.dataInformationService.queryAll();
    }

}
