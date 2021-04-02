package com.aiot.sensor.home.dao;

import com.aiot.sensor.home.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Area)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-18 10:43:11
 */
@Mapper
public interface AreaDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Area queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Area> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param area 实例对象
     * @return 对象列表
     */
    List<Area> queryAll(Area area);

    /**
     * 新增数据
     *
     * @param area 实例对象
     * @return 影响行数
     */
    int insert(Area area);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Area> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Area> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Area> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Area> entities);

    /**
     * 修改数据
     *
     * @param area 实例对象
     * @return 影响行数
     */
    int update(Area area);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

