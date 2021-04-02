package com.aiot.web.home.service.impl;

import com.aiot.web.home.entity.Area;
import com.aiot.web.home.dao.AreaDao;
import com.aiot.web.home.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Area)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-04 17:12:35
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Area queryById(Long id) {
        return this.areaDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Area> queryAllByLimit(int offset, int limit) {
        return this.areaDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param area 实例对象
     * @return 实例对象
     */
    @Override
    public Area insert(Area area) {
        this.areaDao.insert(area);
        return area;
    }

    /**
     * 修改数据
     *
     * @param area 实例对象
     * @return 实例对象
     */
    @Override
    public Area update(Area area) {
        this.areaDao.update(area);
        return this.queryById(area.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.areaDao.deleteById(id) > 0;
    }
}
