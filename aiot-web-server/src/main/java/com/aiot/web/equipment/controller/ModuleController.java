package com.aiot.web.equipment.controller;

import com.aiot.web.equipment.entity.Device;
import com.aiot.web.equipment.entity.Module;
import com.aiot.web.equipment.entity.StatusDTO;
import com.aiot.web.equipment.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Module)表控制层
 *
 * @author AIoT
 * @since 2021-02-04 17:16:30
 */
@RestController
@RequestMapping("module")
public class ModuleController {
    /**
     * 服务对象
     */
    @Resource
    private ModuleService moduleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Module selectOne(Long id) {
        return this.moduleService.queryById(id);
    }

    @PostMapping("modify")
    @PreAuthorize("hasAuthority('ROLE_SU')")
    public ResponseEntity<String> modifyDevice(@RequestBody StatusDTO statusDTO) {
        if(statusDTO.getStatus()==2){
            return new ResponseEntity("无法将模块状态改为故障", HttpStatus.BAD_REQUEST);
        }
        Module existed = this.moduleService.queryById(statusDTO.getId());
        existed.setStatus(statusDTO.getStatus());
        this.moduleService.update(existed);
        return new ResponseEntity("模块状态更新成功", HttpStatus.OK);
    }

}
