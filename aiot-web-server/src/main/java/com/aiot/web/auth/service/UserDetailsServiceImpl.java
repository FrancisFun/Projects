package com.aiot.web.auth.service;

import com.aiot.web.user.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */

/**
 * 用户详情服务
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    /**
     * 加载用户详情
     * @param s 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.aiot.web.user.entity.User user=userService.queryUserByName(s);
        if(user == null){
            throw new UsernameNotFoundException("username: "+s+" not found.");
        }
        Collection<GrantedAuthority> authList = this.getAuthorities(user.getRole());
        return new User(s,user.getPassword().toLowerCase(),authList);
    }

    /**
     * 获取权限列表
     * @param role 角色
     * @return 权限列表
     */
    private Collection<GrantedAuthority> getAuthorities(String role){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }
}
