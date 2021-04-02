package com.aiot.web.home.service;

import com.aiot.web.home.entity.Area;

import java.util.List;

/**
 * (Area)表服务接口
 *
 * @author AIoT
 * @since 2021-02-04 17:12:34
 */
public interface AreaService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Area queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Area> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param area 实例对象
     * @return 实例对象
     */
    Area insert(Area area);

    /**
     * 修改数据
     *
     * @param area 实例对象
     * @return 实例对象
     */
    Area update(Area area);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
