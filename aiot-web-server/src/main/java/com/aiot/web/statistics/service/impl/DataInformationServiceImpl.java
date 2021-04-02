package com.aiot.web.statistics.service.impl;

import com.aiot.web.statistics.entity.DataInformation;
import com.aiot.web.statistics.dao.DataInformationDao;
import com.aiot.web.statistics.service.DataInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DataInformation)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-04 17:16:05
 */
@Service("dataInformationService")
public class DataInformationServiceImpl implements DataInformationService {
    @Resource
    private DataInformationDao dataInformationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DataInformation queryById(Long id) {
        return this.dataInformationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<DataInformation> queryAll(){return this.dataInformationDao.queryAllData(); };

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DataInformation> queryAllByLimit(int offset, int limit) {
        return this.dataInformationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dataInformation 实例对象
     * @return 实例对象
     */
    @Override
    public DataInformation insert(DataInformation dataInformation) {
        this.dataInformationDao.insert(dataInformation);
        return dataInformation;
    }

    /**
     * 修改数据
     *
     * @param dataInformation 实例对象
     * @return 实例对象
     */
    @Override
    public DataInformation update(DataInformation dataInformation) {
        this.dataInformationDao.update(dataInformation);
        return this.queryById(dataInformation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dataInformationDao.deleteById(id) > 0;
    }
}
