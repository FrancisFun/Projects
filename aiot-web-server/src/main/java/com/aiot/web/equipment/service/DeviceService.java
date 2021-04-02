package com.aiot.web.equipment.service;

import com.aiot.web.equipment.entity.Device;

import java.util.List;

/**
 * (Device)表服务接口
 *
 * @author AIoT
 * @since 2021-02-04 17:14:27
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param parent 父级设备主键
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Device> queryAllByParent(Long parent, int offset, int limit);

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
