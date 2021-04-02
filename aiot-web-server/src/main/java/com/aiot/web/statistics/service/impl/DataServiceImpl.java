package com.aiot.web.statistics.service.impl;

import com.aiot.web.statistics.entity.Data;
import com.aiot.web.statistics.dao.DataDao;
import com.aiot.web.statistics.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * (Data)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-04 17:15:45
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
     * 通过类型信息ID查询
     * @param information 类型信息ID
     * @param offset 偏移
     * @param limit 限定
     * @return 对象列表
     */
    @Override
    public List<Data> queryByInformation(Long information,Integer offset,Integer limit){
        return this.dataDao.queryAllByInformation(information,offset,limit);
    }

    @Override
    public List<Data> queryByDeviceAndTimestamp(Long device, Date timestamp, Integer offset, Integer limit) {
        Instant current=Instant.ofEpochMilli(timestamp.getTime());
        Date start=new Date(current.minus(Duration.ofMillis(500)).toEpochMilli());
        Date end=new Date(current.plus(Duration.ofMillis(500)).toEpochMilli());
        return this.dataDao.queryByDeviceAndDateRange(device, start, end, offset, limit);
    }

    @Override
    public List<Data> queryByInformationAndDateRange(Long information, Date start, Date end, Integer offset, Integer limit) {
        return this.dataDao.queryByInformationAndDateRange(information, start, end, offset, limit);
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
