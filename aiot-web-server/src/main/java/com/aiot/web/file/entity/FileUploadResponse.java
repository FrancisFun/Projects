package com.aiot.web.file.entity;

import lombok.Data;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */
/**
 * 文件上传回应
 */
@Data
public class FileUploadResponse {
    /** 回应码 */
    Integer code;

    /** 提示 */
    String text;

    /** 文件名 */
    String fileName;

    /** 路径 */
    String path;
}
