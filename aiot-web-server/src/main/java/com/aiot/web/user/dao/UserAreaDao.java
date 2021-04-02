package com.aiot.web.user.dao;

import com.aiot.web.user.entity.UserArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserArea)表数据库访问层
 *
 * @author AIoT
 * @since 2021-02-04 17:13:44
 */
@Mapper
public interface UserAreaDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserArea queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserArea> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userArea 实例对象
     * @return 对象列表
     */
    List<UserArea> queryAll(UserArea userArea);

    /**
     * 新增数据
     *
     * @param userArea 实例对象
     * @return 影响行数
     */
    int insert(UserArea userArea);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserArea> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserArea> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserArea> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserArea> entities);

    /**
     * 修改数据
     *
     * @param userArea 实例对象
     * @return 影响行数
     */
    int update(UserArea userArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

