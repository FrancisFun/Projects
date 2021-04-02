package com.aiot.sensor.statistics.dao;

import com.aiot.sensor.statistics.entity.DataInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DataInformation)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-18 10:48:31
 */
@Mapper
public interface DataInformationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataInformation queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DataInformation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dataInformation 实例对象
     * @return 对象列表
     */
    List<DataInformation> queryAll(DataInformation dataInformation);

    /**
     * 新增数据
     *
     * @param dataInformation 实例对象
     * @return 影响行数
     */
    int insert(DataInformation dataInformation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataInformation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DataInformation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataInformation> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<DataInformation> entities);

    /**
     * 修改数据
     *
     * @param dataInformation 实例对象
     * @return 影响行数
     */
    int update(DataInformation dataInformation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

