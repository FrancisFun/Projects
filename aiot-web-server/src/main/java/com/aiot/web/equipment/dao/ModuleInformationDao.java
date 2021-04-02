package com.aiot.web.equipment.dao;

import com.aiot.web.equipment.entity.ModuleInformation;
import com.aiot.web.statistics.entity.DataInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ModuleInformation)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-04 17:16:47
 */
@Mapper
public interface ModuleInformationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ModuleInformation queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<ModuleInformation> queryAllData();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ModuleInformation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param moduleInformation 实例对象
     * @return 对象列表
     */
    List<ModuleInformation> queryAll(ModuleInformation moduleInformation);

    /**
     * 新增数据
     *
     * @param moduleInformation 实例对象
     * @return 影响行数
     */
    int insert(ModuleInformation moduleInformation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ModuleInformation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ModuleInformation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ModuleInformation> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ModuleInformation> entities);

    /**
     * 修改数据
     *
     * @param moduleInformation 实例对象
     * @return 影响行数
     */
    int update(ModuleInformation moduleInformation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

