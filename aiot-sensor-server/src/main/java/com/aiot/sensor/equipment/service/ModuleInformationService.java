package com.aiot.sensor.equipment.service;

import com.aiot.sensor.equipment.entity.ModuleInformation;

import java.util.List;

/**
 * (ModuleInformation)表服务接口
 *
 * @author AIoT
 * @since 2021-02-18 10:47:49
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
     * 通过名称查询单条数据
     *
     * @param name 名称
     * @return 实例对象
     */
    ModuleInformation queryByName(String name);

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
