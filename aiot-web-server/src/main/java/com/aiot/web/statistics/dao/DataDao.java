package com.aiot.web.statistics.dao;

import com.aiot.web.statistics.entity.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (Data)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-04 17:15:44
 */
@Mapper
public interface DataDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Data queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Data> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指定行数据
     * @param information data information id
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Data> queryAllByInformation(@Param("information")long information,@Param("offset") int offset, @Param("limit") int limit);

    List<Data> queryByDeviceAndDateRange(@Param("device")long device, @Param("start")Date start, @Param("end")Date end, @Param("offset")int offset, @Param("limit")int limit);

    List<Data> queryByInformationAndDateRange(@Param("information")long information, @Param("start")Date start, @Param("end")Date end, @Param("offset")int offset, @Param("limit")int limit);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param data 实例对象
     * @return 对象列表
     */
    List<Data> queryAll(Data data);

    /**
     * 新增数据
     *
     * @param data 实例对象
     * @return 影响行数
     */
    int insert(Data data);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Data> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Data> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Data> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Data> entities);

    /**
     * 修改数据
     *
     * @param data 实例对象
     * @return 影响行数
     */
    int update(Data data);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

