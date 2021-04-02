package com.aiot.sensor.equipment.service;

import com.aiot.sensor.equipment.entity.Device;
import com.aiot.sensor.equipment.entity.ModuleInformation;

import java.util.List;

/**
 * (Device)表服务接口
 *
 * @author AIoT
 * @since 2021-02-18 10:42:31
 */
public interface DeviceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Long id);

    /**
     * 通过模块ID查询单条数据
     *
     * @param deviceId 模块ID
     * @return 实例对象
     */
    Device queryByDeviceId(String deviceId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device insert(Device device);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
