package com.aiot.sensor.equipment.service.impl;

import com.aiot.sensor.equipment.entity.ModuleInformation;
import com.aiot.sensor.equipment.dao.ModuleInformationDao;
import com.aiot.sensor.equipment.service.ModuleInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ModuleInformation)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-18 10:47:49
 */
@Service("moduleInformationService")
public class ModuleInformationServiceImpl implements ModuleInformationService {
    @Resource
    private ModuleInformationDao moduleInformationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ModuleInformation queryById(Long id) {
        return this.moduleInformationDao.queryById(id);
    }

    /**
     * 通过名称查询单条数据
     *
     * @param name 名称
     * @return 实例对象
     */
    @Override
    public ModuleInformation queryByName(String name) {
        return this.moduleInformationDao.queryByName(name);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ModuleInformation> queryAllByLimit(int offset, int limit) {
        return this.moduleInformationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param moduleInformation 实例对象
     * @return 实例对象
     */
    @Override
    public ModuleInformation insert(ModuleInformation moduleInformation) {
        this.moduleInformationDao.insert(moduleInformation);
        return moduleInformation;
    }

    /**
     * 修改数据
     *
     * @param moduleInformation 实例对象
     * @return 实例对象
     */
    @Override
    public ModuleInformation update(ModuleInformation moduleInformation) {
        this.moduleInformationDao.update(moduleInformation);
        return this.queryById(moduleInformation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.moduleInformationDao.deleteById(id) > 0;
    }
}
