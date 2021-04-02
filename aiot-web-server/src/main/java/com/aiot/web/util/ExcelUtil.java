package com.aiot.web.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/3/1
 */
public class ExcelUtil {

    public static void download(HttpServletResponse response, String fileName, Class cls, List dataList) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fname = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fname + ExcelTypeEnum.XLSX.getValue());

        LongestMatchColumnWidthStyleStrategy longestMatchColumnWidthStyleStrategy =
                new LongestMatchColumnWidthStyleStrategy();
        EasyExcel.write(response.getOutputStream(), cls)
                .sheet("sheet1")
                .registerWriteHandler(longestMatchColumnWidthStyleStrategy)
                .doWrite(dataList);
        response.flushBuffer();
    }

}
