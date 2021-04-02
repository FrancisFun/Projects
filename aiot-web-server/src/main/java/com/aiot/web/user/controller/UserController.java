package com.aiot.web.user.controller;

import com.aiot.web.user.entity.PasswordDTO;
import com.aiot.web.user.entity.User;
import com.aiot.web.user.entity.UserDTO;
import com.aiot.web.user.service.UserService;
import com.aiot.web.util.JwtUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:13:21
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }

    /**
     * 查询当前用户的信息
     * @param request 请求
     * @return 当前用户信息
     */
    @GetMapping("selectCurrent")
    public User selectCurrent(HttpServletRequest request){
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        return this.userService.queryById(JwtUtil.getId(token));
    }

    /**
     * 分页查询用户列表
     * @param offset 偏移
     * @param limit 限制
     * @return 用户列表
     */
    @GetMapping("selectAllByLimit")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public List<User> selectAllByLimit(Integer offset, Integer limit){
        if(limit==null){
            limit = 10;
        }
        List<User> result=this.userService.queryAllByLimit(offset,limit);
        for(User user : result){
            user.setPassword(null);
        }
        return result;
    }


    /**
     * 登陆
     * @param response 回应
     * @param userDTO 登陆信息
     * @return 结果
     */
    @PostMapping("login")
    public ResponseEntity<String> login(HttpServletResponse response, @RequestBody UserDTO userDTO){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User user=this.userService.queryUserByName(userDTO.getUsername());
        // 判断用户是否存在及判断密码是否正确
        if(user==null || !bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            return new ResponseEntity<>("用户名或密码错误", HttpStatus.BAD_REQUEST);
        }

        // 生成Token
        String token = JwtUtil.generateToken(user.getUsername(),user.getRole(),user.getId());
        response.setHeader(JwtUtil.AUTHORIZATION,token);
        return new ResponseEntity<>(JSON.toJSONString(user),HttpStatus.OK);
    }

    /**
     * 注册
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("register")
    public ResponseEntity<String> register(HttpServletResponse response, @RequestBody User user){
        ResponseEntity<String> result = add(user);
        if(result.getStatusCode().isError()){
            return result;
        }

        // 生成Token
        String token = JwtUtil.generateToken(user.getUsername(),user.getRole(),user.getId());
        response.setHeader(JwtUtil.AUTHORIZATION,token);

        return new ResponseEntity<>(JSON.toJSONString(user), HttpStatus.OK);
    }

    /**
     * 更改用户信息
     * @param user 用户信息
     * @return 更改结果
     */
    @PostMapping("modify")
    public ResponseEntity<String> modifyUser(HttpServletRequest request, @RequestBody User user){
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        String role = JwtUtil.getRole(token);
        // 本用户或超级管理员才能进行资料更改操作
        if(JwtUtil.isSameUser(token,user.getId()) || role.equals("ROLE_SU")){
            User existed=this.userService.queryById(user.getId());
            user.setPassword(existed.getPassword());
            this.userService.update(user);
            return new ResponseEntity<>("个人信息更新成功",HttpStatus.OK);
        }
        return new ResponseEntity<>("无此操作权限",HttpStatus.FORBIDDEN);
    }

    /**
     * 更改密码
     * @param passwordDTO 密码信息
     * @return 更改结果
     */
    @PostMapping("changePassword")
    public ResponseEntity<String> changePassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        String role = JwtUtil.getRole(token);
        // 本用户或超级管理员才能进行密码更改操作
        if(JwtUtil.isSameUser(token,passwordDTO.getId()) || role.equals("ROLE_SU")){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            User user=this.userService.queryById(passwordDTO.getId());
            // 判断旧密码是否正确
            if (!bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
                return new ResponseEntity<>("密码错误",HttpStatus.BAD_REQUEST);
            }
            // 更新密码
            user.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));
            this.userService.update(user);
            return new ResponseEntity<>("密码更改成功",HttpStatus.OK);
        }
        return new ResponseEntity<>("无此操作权限",HttpStatus.FORBIDDEN);
    }

    /**
     * 增加用户
     * @param user 用户
     * @return 增加结果
     */
    @PostMapping("addUser")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return add(user);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("deleteUser")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public ResponseEntity<String> deleteUser(Long id){
        this.userService.deleteById(id);
        return new ResponseEntity<>("成功删除用户", HttpStatus.OK);
    }

    /**
     * 增加用户
     * @param user 用户
     * @return 增加结果
     */
    private ResponseEntity<String> add(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User existed=this.userService.queryUserByName(user.getUsername());
        // 判断用户名是否可用
        if(existed!=null){
            return new ResponseEntity<>("用户名已存在，请更换后重试", HttpStatus.FOUND);
        }

        // 填充默认信息
        if(StringUtils.isBlank(user.getRole())){
            user.setRole("ROLE_MONITOR");
        }
        if(StringUtils.isBlank(user.getAvatar())){
            user.setAvatar("/default.jpg");
        }
        if(user.getStatus()==null){
            user.setStatus((short)1);
        }

        // 加密存储密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user=this.userService.insert(user);

        return new ResponseEntity<>(JSON.toJSONString(user), HttpStatus.OK);
    }
}
