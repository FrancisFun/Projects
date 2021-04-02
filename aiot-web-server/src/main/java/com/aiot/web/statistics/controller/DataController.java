package com.aiot.web.statistics.controller;

import com.aiot.web.equipment.entity.Device;
import com.aiot.web.equipment.service.DeviceService;
import com.aiot.web.home.entity.Area;
import com.aiot.web.home.service.AreaService;
import com.aiot.web.statistics.entity.*;
import com.aiot.web.statistics.service.DataInformationService;
import com.aiot.web.statistics.service.DataService;
import com.aiot.web.util.Convertor;
import com.aiot.web.util.ExcelUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * (Data)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:15:46
 */
@RestController
@RequestMapping("data")
public class DataController {
    /**
     * 服务对象
     */
    @Resource
    private DataService dataService;

    @Resource
    private DataInformationService dataInformationService;

    /**
     * 服务对象
     */
    @Resource
    private AreaService areaService;

    /**
     * 服务对象
     */
    @Resource
    private DeviceService deviceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Data selectOne(Long id) {
        return this.dataService.queryById(id);
    }

    /**
     * 通过类型信息ID查询
     * @param information 类型信息ID
     * @param offset 偏移
     * @param limit 限定
     * @return 对象列表
     */
    @GetMapping("selectByInformation")
    public List<DataVO> selectByData(Long information, Integer offset, Integer limit){
        if(limit==null){
            limit=20;
        }
        List<Data> data = this.dataService.queryByInformation(information,offset,limit);
        List<DataVO> result=new LinkedList<DataVO>();
        for(Data current:data){
            Device device=deviceService.queryById(current.getDevice());
            Area area=areaService.queryById(device.getArea());
            DataVO dataVO= Convertor.convert(current,area);
            result.add(dataVO);
        }
        return result;
    }

    @GetMapping("selectAllByLimit")
    public List<ComplexDataVO> selectAll(Integer offset, Integer limit){
        if(limit==null){
            limit=10;
        }
        // 查询所有的数据类型
        List<DataInformation> dataInformation = this.dataInformationService.queryAll();
        int length = dataInformation.size();
        List<ComplexDataVO> result=new LinkedList<>();

        if(length > 0){
            List<Data> data = this.dataService.queryByInformation(dataInformation.get(0).getId(),offset,limit);
            for(Data current : data){
                ComplexDataVO complexDataVO=new ComplexDataVO();
                Device device=deviceService.queryById(current.getDevice());
                Area area=areaService.queryById(device.getArea());
                complexDataVO.setArea(area);
                complexDataVO.setTimestamp(current.getTimestamp());
                List<SimpleData> simpleData=new LinkedList<>();
                List<Data> otherData = this.dataService.queryByDeviceAndTimestamp(device.getId(),current.getTimestamp(),offset,limit);
                for(Data other:otherData){
                    SimpleData simple=new SimpleData();
                    simple.setDataInformation(other.getInformation());
                    simple.setValue(other.getValue());
                    simpleData.add(simple);
                }
                complexDataVO.setDataList(simpleData);
                result.add(complexDataVO);
            }
        }
        return result;
    }

    @GetMapping("selectByDateRange")
    public List<ComplexDataVO> selectByDateRange(Date start, Date end, Integer offset, Integer limit){
        if(limit==null){
            limit=20;
        }
        // 查询所有的数据类型
        List<DataInformation> dataInformation = this.dataInformationService.queryAll();
        int length = dataInformation.size();
        List<ComplexDataVO> result=new LinkedList<>();

        if(length > 0){
            List<Data> data = this.dataService.queryByInformationAndDateRange(dataInformation.get(0).getId(),start,end,offset,limit);
            for(Data current : data){
                ComplexDataVO complexDataVO=new ComplexDataVO();
                Device device=deviceService.queryById(current.getDevice());
                Area area=areaService.queryById(device.getArea());
                complexDataVO.setArea(area);
                complexDataVO.setTimestamp(current.getTimestamp());
                List<SimpleData> simpleData=new LinkedList<>();
                List<Data> otherData = this.dataService.queryByDeviceAndTimestamp(device.getId(),current.getTimestamp(),offset,limit);
                for(Data current1:otherData){
                    SimpleData simple=new SimpleData();
                    simple.setDataInformation(current1.getInformation());
                    simple.setValue(current.getValue());
                    simpleData.add(simple);
                }
                complexDataVO.setDataList(simpleData);
            }
        }
        return result;
    }

    @PostMapping("/export")
    public void export(@RequestBody DataExportDTO dto, HttpServletResponse response) throws IOException {
        String fileName = "数据导出表" + System.currentTimeMillis();
        ExcelUtil.download(response, fileName, DataExportVO.class, getExportVoList(DataExportDTO dto));
    }

    private List getExportVoList(DataExportDTO dto) {
        //  这里主要是获取List<ExportVo>内容，不提供实现了，需要说下注意点。
        List<DataExportVO> result = new LinkedList<>();
        if(dto == null || dto.getStart() == null || dto.getEnd() == null || dto.getStart().after(dto.getEnd())){
            return result;
        }

        // 查询所有的数据类型
        List<DataInformation> dataInformation = this.dataInformationService.queryAll();
        int length = dataInformation.size();


    }
}
