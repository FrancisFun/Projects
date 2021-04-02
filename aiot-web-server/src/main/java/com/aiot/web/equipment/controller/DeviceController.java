package com.aiot.web.equipment.controller;

import com.aiot.web.equipment.entity.*;
import com.aiot.web.equipment.service.DeviceService;
import com.aiot.web.equipment.service.ModuleInformationService;
import com.aiot.web.equipment.service.ModuleService;
import com.aiot.web.home.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * (Device)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:14:28
 */
@RestController
@RequestMapping("device")
public class DeviceController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceService deviceService;

    /**
     * 服务对象
     */
    @Resource
    private AreaService areaService;

    /**
     * 服务对象
     */
    @Resource
    private ModuleService moduleService;

    /**
     * 服务对象
     */
    @Resource
    private ModuleInformationService moduleInformationService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Device selectOne(Long id) {
        return this.deviceService.queryById(id);
    }

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @GetMapping("selectAllByLimit")
    public List<DeviceVO> selectAllByLimit(Integer offset, Integer limit){
        if(limit==null){
            limit=20;
        }
        // 查询所有网关
        List<Device> gateways = this.deviceService.queryAllByParent(-1L,offset,limit);
        List<DeviceVO> result = new LinkedList<DeviceVO>();

        // 遍历网关查询各自子节点
        for(Device gateway:gateways){
            // 网关对象转换
            DeviceVO deviceVO=new DeviceVO();
            deviceVO.setId(gateway.getId());
            deviceVO.setArea(areaService.queryById(gateway.getArea()));
            deviceVO.setDeviceId(gateway.getDeviceId());
            deviceVO.setStatus(gateway.getStatus());
            deviceVO.setFirmwareVersion(gateway.getFirmwareVersion());
            deviceVO.setHardwareVersion(gateway.getHardwareVersion());
            deviceVO.setSoftwareVersion(gateway.getSoftwareVersion());
            deviceVO.setMac(gateway.getMac());
            List<Device> devices = this.deviceService.queryAllByParent(gateway.getId(),offset,limit);
            List<Node> nodes=new LinkedList<Node>();

            // 遍历设备查询各自模块
            for(Device device:devices){
                // 模块对象转换
                Node node=new Node();
                node.setId(device.getId());
                node.setDeviceId(device.getDeviceId());
                node.setStatus(device.getStatus());
                List<Module> modules=this.moduleService.queryAllByDevice(device.getId(),offset,limit);
                List<ModuleVO> sensors=new LinkedList<ModuleVO>();

                // 遍历模块进行模块对象转换
                for(Module module:modules){
                    ModuleVO sensor=new ModuleVO();
                    ModuleInformation moduleInformation=moduleInformationService.queryById(module.getInformation());
                    sensor.setId(module.getId());
                    sensor.setName(moduleInformation.getChinese());
                    sensor.setIcon(moduleInformation.getIcon());
                    sensor.setStatus(module.getStatus());
                    sensor.setSetting(module.getSetting());
                    sensors.add(sensor);
                }
                node.setModules(sensors);
                nodes.add(node);
            }
            deviceVO.setNodes(nodes);
            result.add(deviceVO);
        }
        return result;
    }

    @PostMapping("modify")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public ResponseEntity<String> modifyDevice(@RequestBody StatusDTO statusDTO) {
        if(statusDTO.getStatus()==2){
            return new ResponseEntity("无法将设备状态改为故障", HttpStatus.BAD_REQUEST);
        }
        Device existed = this.deviceService.queryById(statusDTO.getId());
        existed.setStatus(statusDTO.getStatus());
        this.deviceService.update(existed);
        return new ResponseEntity("设备状态更新成功", HttpStatus.OK);
    }

}
