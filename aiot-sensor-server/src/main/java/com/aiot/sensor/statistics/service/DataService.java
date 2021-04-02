package com.aiot.sensor.statistics.service;

import com.aiot.sensor.statistics.entity.Data;

import java.util.List;

/**
 * (Data)表服务接口
 *
 * @author AIoT
 * @since 2021-02-18 10:05:16
 */
public interface DataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Data queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Data> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param data 实例对象
     * @return 实例对象
     */
    Data insert(Data data);

    /**
     * 修改数据
     *
     * @param data 实例对象
     * @return 实例对象
     */
    Data update(Data data);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
