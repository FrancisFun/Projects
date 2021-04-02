package com.aiot.web.util;

import com.aiot.web.home.entity.Area;
import com.aiot.web.statistics.entity.Data;
import com.aiot.web.statistics.entity.DataInformation;
import com.aiot.web.statistics.entity.DataVO;

/**
 * @Author: Frank
 * @Date: 2021/2/19
 */
public class Convertor {
    public static DataVO convert(Data data, Area area){
        DataVO result=new DataVO();
        result.setArea(area);
        result.setTimestamp(data.getTimestamp());
        result.setValue(data.getValue());
        return result;
    }
}
