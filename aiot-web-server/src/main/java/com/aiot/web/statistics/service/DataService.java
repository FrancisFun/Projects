package com.aiot.web.statistics.service;

import com.aiot.web.statistics.entity.Data;

import java.util.Date;
import java.util.List;

/**
 * (Data)表服务接口
 *
 * @author AIoT
 * @since 2021-02-04 17:15:45
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
     * 通过类型信息ID查询
     * @param information 类型信息ID
     * @param offset 偏移
     * @param limit 限定
     * @return 对象列表
     */
    List<Data> queryByInformation(Long information,Integer offset,Integer limit);

    List<Data> queryByDeviceAndTimestamp(Long device, Date timestamp, Integer offset, Integer limit);

    List<Data> queryByInformationAndDateRange(Long information,Date start, Date end, Integer offset, Integer limit);

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
