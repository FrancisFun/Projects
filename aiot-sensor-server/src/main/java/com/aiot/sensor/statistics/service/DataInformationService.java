package com.aiot.sensor.statistics.service;

import com.aiot.sensor.statistics.entity.DataInformation;

import java.util.List;

/**
 * (DataInformation)表服务接口
 *
 * @author AIoT
 * @since 2021-02-18 10:48:31
 */
public interface DataInformationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataInformation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DataInformation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dataInformation 实例对象
     * @return 实例对象
     */
    DataInformation insert(DataInformation dataInformation);

    /**
     * 修改数据
     *
     * @param dataInformation 实例对象
     * @return 实例对象
     */
    DataInformation update(DataInformation dataInformation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
