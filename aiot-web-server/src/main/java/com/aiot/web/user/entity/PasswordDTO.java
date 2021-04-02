package com.aiot.web.user.entity;

import lombok.Data;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */
@Data
public class PasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
