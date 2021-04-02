package com.aiot.web.user.service.impl;

import com.aiot.web.user.entity.UserArea;
import com.aiot.web.user.dao.UserAreaDao;
import com.aiot.web.user.service.UserAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserArea)表服务实现类
 *
 * @author AIoT
 * @since 2021-02-04 17:13:45
 */
@Service("userAreaService")
public class UserAreaServiceImpl implements UserAreaService {
    @Resource
    private UserAreaDao userAreaDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserArea queryById(Long id) {
        return this.userAreaDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserArea> queryAllByLimit(int offset, int limit) {
        return this.userAreaDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userArea 实例对象
     * @return 实例对象
     */
    @Override
    public UserArea insert(UserArea userArea) {
        this.userAreaDao.insert(userArea);
        return userArea;
    }

    /**
     * 修改数据
     *
     * @param userArea 实例对象
     * @return 实例对象
     */
    @Override
    public UserArea update(UserArea userArea) {
        this.userAreaDao.update(userArea);
        return this.queryById(userArea.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userAreaDao.deleteById(id) > 0;
    }
}
