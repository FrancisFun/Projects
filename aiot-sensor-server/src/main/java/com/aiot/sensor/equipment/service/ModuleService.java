package com.aiot.sensor.equipment.service;

import com.aiot.sensor.equipment.entity.Module;

import java.util.List;

/**
 * (Module)表服务接口
 *
 * @author AIoT
 * @since 2021-02-18 10:42:48
 */
public interface ModuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Module queryById(Long id);

    /**
     * 通过设备ID与数据类型查询单条数据
     * @param deviceId 设备ID
     * @param informationId 数据类型
     * @return
     */
    Module queryByDeviceAndInformation(Long deviceId,Long informationId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Module> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    Module insert(Module module);

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    Module update(Module module);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
