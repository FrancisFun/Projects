package com.aiot.web.user.entity;

import lombok.Data;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private Boolean isRemember;
}
