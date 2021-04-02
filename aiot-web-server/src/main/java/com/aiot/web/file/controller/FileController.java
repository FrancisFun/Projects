package com.aiot.web.file.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.aiot.web.file.entity.FileUploadResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */
/**
 * 文件控制器
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    /**
     * 文件上传路径
     */
    @Value("${file.upload-url}")
    private String uploadFilePath;

    /**
     * 上传文件
     * @param files 文件列表
     * @return 上传结果
     */
    @PostMapping("upload")
    public ResponseEntity<List<FileUploadResponse>> httpUpload(@RequestParam("files") MultipartFile files[]){
        List<FileUploadResponse> responseList = new LinkedList<>();
        boolean isError=false;
        JSONObject object=new JSONObject();

        // 编译文件列表
        for(int i=0;i<files.length;i++) {
            FileUploadResponse response = new FileUploadResponse();
            if (files[i].isEmpty()) {
                response.setCode(401);
                response.setText("文件为空");
                responseList.add(response);
                return new ResponseEntity<>(responseList, HttpStatus.BAD_REQUEST);
            }

            // 文件名
            String fileName = files[i].getOriginalFilename();
            response.setFileName(fileName);
            String type = FileUtil.extName(fileName);

            // 文件别名
            String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            String path = '/' + s + '.' + type;

            // 父级不存在就创建父级
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }

            try {
                // 上传至文件服务器
                byte[] bytes = files[i].getBytes();
                HttpResponse httpResponse = HttpRequest.post(uploadFilePath)
                        .form("file", bytes, s + '.' + type)
                        .timeout(20000)
                        .execute();
                // 判断服务器返回
                if (httpResponse.body().equals("ok")) {
                    response.setPath(path);
                    response.setCode(200);
                    response.setText("文件上传成功");
                } else {
                    response.setCode(500);
                    response.setText("程序错误，请重新上传");
                    isError=true;
                }
                responseList.add(response);
            } catch (Exception e) {
                log.error("文件上传错误：{}", e);
                response.setCode(500);
                response.setText("程序错误，请重新上传");
                isError=true;
                responseList.add(response);
            }
        }

        return isError?new ResponseEntity<>(responseList, HttpStatus.INTERNAL_SERVER_ERROR):new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
