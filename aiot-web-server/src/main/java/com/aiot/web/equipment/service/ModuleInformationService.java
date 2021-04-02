package com.aiot.web.equipment.service;

import com.aiot.web.equipment.entity.ModuleInformation;
import com.aiot.web.statistics.entity.DataInformation;

import java.util.List;

/**
 * (ModuleInformation)表服务接口
 *
 * @author AIoT
 * @since 2021-02-04 17:16:48
 */
public interface ModuleInformationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ModuleInformation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<ModuleInformation> queryAll();

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ModuleInformation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param moduleInformation 实例对象
     * @return 实例对象
     */
    ModuleInformation insert(ModuleInformation moduleInformation);

    /**
     * 修改数据
     *
     * @param moduleInformation 实例对象
     * @return 实例对象
     */
    ModuleInformation update(ModuleInformation moduleInformation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
