package com.aiot.sensor.statistics.service.impl;

import com.aiot.sensor.statistics.entity.Data;
import com.aiot.sensor.statistics.dao.DataDao;
import com.aiot.sensor.statistics.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Data)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-18 10:05:17
 */
@Service("dataService")
public class DataServiceImpl implements DataService {
    @Resource
    private DataDao dataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Data queryById(Long id) {
        return this.dataDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Data> queryAllByLimit(int offset, int limit) {
        return this.dataDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param data 实例对象
     * @return 实例对象
     */
    @Override
    public Data insert(Data data) {
        this.dataDao.insert(data);
        return data;
    }

    /**
     * 修改数据
     *
     * @param data 实例对象
     * @return 实例对象
     */
    @Override
    public Data update(Data data) {
        this.dataDao.update(data);
        return this.queryById(data.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dataDao.deleteById(id) > 0;
    }
}
