package com.aiot.sensor.equipment.service.impl;

import com.aiot.sensor.equipment.entity.Device;
import com.aiot.sensor.equipment.dao.DeviceDao;
import com.aiot.sensor.equipment.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Device)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-18 10:42:31
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Resource
    private DeviceDao deviceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Device queryById(Long id) {
        return this.deviceDao.queryById(id);
    }

    /**
     * 通过模块ID查询单条数据
     *
     * @param deviceId 模块ID
     * @return 实例对象
     */
    @Override
    public Device queryByDeviceId(String deviceId) {
        return this.deviceDao.queryByDeviceId(deviceId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Device> queryAllByLimit(int offset, int limit) {
        return this.deviceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public Device insert(Device device) {
        this.deviceDao.insert(device);
        return device;
    }

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public Device update(Device device) {
        this.deviceDao.update(device);
        return this.queryById(device.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.deviceDao.deleteById(id) > 0;
    }
}
