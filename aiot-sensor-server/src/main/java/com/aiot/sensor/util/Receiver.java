package com.aiot.sensor.util;

import com.aiot.sensor.equipment.entity.Device;
import com.aiot.sensor.equipment.entity.Module;
import com.aiot.sensor.equipment.entity.ModuleInformation;
import com.aiot.sensor.equipment.service.DeviceService;
import com.aiot.sensor.equipment.service.ModuleInformationService;
import com.aiot.sensor.equipment.service.ModuleService;
import com.aiot.sensor.home.service.AreaService;
import com.aiot.sensor.message.entity.DataMessage;
import com.aiot.sensor.message.entity.LoginMessage;
import com.aiot.sensor.statistics.entity.Data;
import com.aiot.sensor.statistics.entity.DataInformation;
import com.aiot.sensor.statistics.service.DataInformationService;
import com.aiot.sensor.statistics.service.DataService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/18
 */
@Component
public class Receiver {
    private static final int INTERVAL=60;
    private static int count=0;

    @Resource
    private DataService dataService;

    @Resource
    private DataInformationService dataInformationService;

    @Resource
    private AreaService areaService;

    @Resource
    private DeviceService deviceService;

    @Resource
    private ModuleInformationService moduleInformationService;

    @Resource
    private ModuleService moduleService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processData(String message){
        try{
            // 设备上传数据点消息
             List<DataMessage> array= JSON.parseArray(message,DataMessage.class);
            for(DataMessage data:array){
                processDataMessage(data);
            }
        }catch(JSONException e){
            JSONObject object=JSON.parseObject(message);
            int type=object.getInteger("type");
            switch (type){
                case 1:
                    // 设备数据点消息
                    processDataMessage(object.toJavaObject(DataMessage.class));
                    break;
                case 2:
                    // 设备上下线消息
                    processLoginMessage(object.toJavaObject(LoginMessage.class));
                    break;
                default:
            }
        }
    }

    /**
     * 处理上下线消息
     * @param message 上下线消息
     */
    private void processLoginMessage(LoginMessage message){
        String deviceId=String.valueOf(message.getDevId());

        // 判断是否为新设备
        Device device=deviceService.queryByDeviceId(deviceId);

        // 新设备则插入相关信息
        if(device==null){
            device=new Device();
            device.setArea(1L);
            device.setType((short)0);
            device.setDeviceId(deviceId);
            device.setStatus(message.getStatus().shortValue());
            deviceService.insert(device);
        }else{
            // 更新状态信息
            device.setStatus(message.getStatus().shortValue());
            deviceService.update(device);
        }
    }

    /**
     * 处理数据流信息
     * @param message 数据流信息
     */
    private void processDataMessage(DataMessage message){
        // 获取模块信息
        String[] dsId=message.getDsId().split("_");
        String parentId=String.valueOf(message.getDevId());
        String moduleName=dsId[0];
        String deviceId="";
        if(dsId.length>1){
            // 节点设备
            deviceId=dsId[1];
        }else{
            // 网关设备
            deviceId=String.valueOf(message.getDevId());
        }

        // 判断是否为新设备
        Device device=deviceService.queryByDeviceId(deviceId);

        // 新设备则插入相关信息
        if(device==null){
            Device parent=deviceService.queryByDeviceId(parentId);
            device=new Device();
            device.setArea(parent.getArea());
            device.setParent(parent.getId());
            device.setType((short)1);
            device.setDeviceId(deviceId);
            device.setStatus((short)1);
            device=deviceService.insert(device);
        }

        if(moduleName.equals("location")){
            // 位置
            processArea(message,device);
        }else if(moduleName.equals("firmwareVersion")){
            // 固件版本
            processDeviceInformation(message,device,0);
        }else if(moduleName.equals("hardwareVersion")){
            // 硬件版本
            processDeviceInformation(message,device,1);
        }else if(moduleName.equals("softwareVersion")){
            // 软件版本
            processDeviceInformation(message,device,2);
        }else if(moduleName.equals("mac")){
            // MAC地址
            processDeviceInformation(message,device,3);
        }else{
            // 数据
            processData(message,device,moduleName);
        }


    }

    /**
     * 处理数据信息
     * @param message 信息
     * @param device 设备
     * @param moduleName 模块名
     */
    private void processData(DataMessage message,Device device,String moduleName){
        ModuleInformation moduleInformation=moduleInformationService.queryByName(moduleName);
        DataInformation dataInformation=dataInformationService.queryById(moduleInformation.getData());
        // 判断是否为新模块
        Module module=moduleService.queryByDeviceAndInformation(device.getId(),moduleInformation.getId());

        if(module==null){
            // 新的模块
            module=new Module();
            module.setDevice(device.getId());
            module.setInformation(moduleInformation.getId());
            module.setStatus((short)1);
            moduleService.insert(module);
        }

        // 填充数据
        Data data=new Data();
        data.setDevice(device.getId());
        data.setInformation(dataInformation.getId());
        data.setTimestamp(new Date(message.getAt()));
        try{
            data.setValue(Double.parseDouble((String)message.getValue()));
            // 插入数据
            dataService.insert(data);
        }catch(Exception e) {
            System.err.println("Parse Error");
        }
    }

    /**
     * 处理位置信息
     * @param message 信息
     * @param device 设备
     */
    private void processArea(DataMessage message,Device device){

    }

    /**
     * 处理设备信息
     * @param message 信息
     * @param device 设备
     * @param type 信息类型
     */
    private void processDeviceInformation(DataMessage message,Device device,int type){
        switch (type){
            case 0:
                // 固件版本
                device.setFirmwareVersion((String)message.getValue());
                break;
            case 1:
                // 硬件版本
                device.setHardwareVersion((String)message.getValue());
                break;
            case 2:
                // 软件版本
                device.setSoftwareVersion((String)message.getValue());
                break;
            case 3:
                // MAC地址
                device.setMac((String)message.getValue());
                break;
            default:
        }
        deviceService.update(device);
    }
}
