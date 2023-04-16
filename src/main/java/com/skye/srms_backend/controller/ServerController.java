package com.skye.srms_backend.controller;

import com.skye.srms_backend.entity.Server;
import com.skye.srms_backend.entity.User;
import com.skye.srms_backend.service.IServerService;
import com.skye.srms_backend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-14
 */
@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private IServerService serverService;


    @GetMapping("/all")
    public Result<List<Server>> getAll() {
        List<Server> list = serverService.getServerListOrderByAscOnName();
        log.debug("#####################all serverList:"+list);
        return Result.success(list);
    }

    @GetMapping("/info")
    public Result<Server> getServerInfo(@RequestParam String serverIndex){
        log.debug("-------------serverIndex:"+serverIndex);
        Server server = serverService.getServerInfo(serverIndex);
        return Result.success(server);
    }


    @GetMapping("/failed")
    public Result<List<Server>> getFailed() {
        List<Server> list = serverService.getFailedServer();
        log.debug("#####################failed serverList:"+list);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result<?> addServer(@RequestBody Server server) {
        if(serverService.add(server)){
            return Result.success("add server successfully");
        }
        return Result.fail("add server failed");
    }


    @PutMapping("/update")
    public Result<?> updateServer(@RequestBody Server server){
        boolean updated = serverService.update(server);
        if (updated){
            return Result.success("server details updated successfully");
        }
        return Result.fail("server details updated failed");
    }


    @PutMapping("/upfixid")
    public Result<?> updateFixId(@RequestBody Map map){
//    public Result<?> updateFixId(@RequestBody String fixId, @RequestBody String serverIndex){
        Server server = new Server();
//        server.setFixId(fixId);
//        server.setServerIndex(serverIndex);

        server.setFixId(map.get("fixId").toString());
        server.setServerIndex(map.get("serverIndex").toString());

        log.debug("@@@@@@@@@@@@@@@@@@@:"+server.toString());
        boolean updated = serverService.update(server);
        if (updated){
            return Result.success("server fix info updated successfully");
        }
        return Result.fail("server fix info  updated failed");
    }








}
