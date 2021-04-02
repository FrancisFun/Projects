package com.aiot.web.equipment.service.impl;

import com.aiot.web.equipment.entity.Module;
import com.aiot.web.equipment.dao.ModuleDao;
import com.aiot.web.equipment.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Module)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-04 17:16:30
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleDao moduleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Module queryById(Long id) {
        return this.moduleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Module> queryAllByLimit(int offset, int limit) {
        return this.moduleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param device 设备主键
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Module> queryAllByDevice(Long device, int offset, int limit){
        return this.moduleDao.queryAllByDevice(device,offset,limit);
    }

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    @Override
    public Module insert(Module module) {
        this.moduleDao.insert(module);
        return module;
    }

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    @Override
    public Module update(Module module) {
        this.moduleDao.update(module);
        return this.queryById(module.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.moduleDao.deleteById(id) > 0;
    }
}
