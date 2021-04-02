package com.aiot.web.user.service;

import com.aiot.web.user.entity.UserArea;

import java.util.List;

/**
 * (UserArea)表服务接口
 *
 * @author AIoT
 * @since 2021-02-04 17:13:45
 */
public interface UserAreaService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserArea queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserArea> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userArea 实例对象
     * @return 实例对象
     */
    UserArea insert(UserArea userArea);

    /**
     * 修改数据
     *
     * @param userArea 实例对象
     * @return 实例对象
     */
    UserArea update(UserArea userArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
