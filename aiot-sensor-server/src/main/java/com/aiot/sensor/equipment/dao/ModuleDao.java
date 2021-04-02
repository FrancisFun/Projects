package com.aiot.sensor.equipment.dao;

import com.aiot.sensor.equipment.entity.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Module)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-18 10:42:48
 */
@Mapper
public interface ModuleDao {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Module> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param module 实例对象
     * @return 对象列表
     */
    List<Module> queryAll(Module module);

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 影响行数
     */
    int insert(Module module);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Module> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Module> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Module> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Module> entities);

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 影响行数
     */
    int update(Module module);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

