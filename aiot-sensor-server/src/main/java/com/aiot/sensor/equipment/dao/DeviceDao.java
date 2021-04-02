package com.aiot.sensor.equipment.dao;

import com.aiot.sensor.equipment.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Device)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-18 10:42:30
 */
@Mapper
public interface DeviceDao {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param device 实例对象
     * @return 对象列表
     */
    List<Device> queryAll(Device device);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int insert(Device device);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Device> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Device> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Device> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Device> entities);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

