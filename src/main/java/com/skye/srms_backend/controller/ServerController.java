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
 * 前端控制器
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
        log.debug("#####################all serverList:" + list);
        return Result.success(list);
    }

    @GetMapping("/info")
    public Result<Server> getServerInfo(@RequestParam String serverIndex) {
        log.debug("-------------serverIndex:" + serverIndex);
        Server server = serverService.getServerInfo(serverIndex);
        return Result.success(server);
    }


    @GetMapping("/failed")
    public Result<List<Server>> getFailed() {
        List<Server> list = serverService.getFailedServer();
        log.debug("#####################failed serverList:" + list);
        return Result.success(list);
    }


    @GetMapping("/issued")
    public Result<List<Server>> getIssued() {
        List<Server> list = serverService.getIssuedServer();
        log.debug("#####################issued serverList:" + list);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result<?> addServer(@RequestBody Server server) {
        if (serverService.add(server)) {
            return Result.success("add server successfully");
        }
        return Result.fail("add server failed");
    }


    //    @PutMapping("/update")
//    public Result<?> updateServer(@RequestBody Server server){
//    @PutMapping("/update")
//    public Result<?> updateServer(@RequestBody Server server, @RequestBody String username) {

//    @PutMapping("/update")
//    public Result<?> updateServer(@RequestBody Server server, @RequestParam String username) {
//
//        log.debug("$$$$$$$$$$$before update server0:" + server.toString());
//        boolean updated = serverService.update(server,username);
//        if (updated) {
//            return Result.success("server details updated successfully");
//        }
//        return Result.fail("server details updated failed");
//    }

    @PutMapping("/update")
    public Result<?> updateServer(@RequestBody Map map) {

//        Server server = new Server();
        log.debug(map.toString());
        Server server = serverService.getServerInfo(map.get("serverIndex").toString());
        log.debug("$$$$$$$$$$$before update server0:" + server.toString());

        server.setIsWorking(map.get("isWorking").toString() == "true"?true:false);
        server.setDescriptions(map.get("descriptions").toString());

        String username = map.get("username").toString();
        log.debug("$$$$$$$$$$$before update server0:" + server.toString());


        boolean updated = serverService.update(server, username);
        if (updated) {
            return Result.success("server details updated successfully");
        }
        return Result.fail("server details updated failed");
    }

    @PutMapping("/upfixid")
    public Result<?> updateFixId(@RequestBody Map map) {
//        Server server = new Server();
//        server.setFixId(map.get("fixId").toString());
//        server.setServerIndex(map.get("serverIndex").toString());
//        log.debug("@@@@@@@@@@@@@@@@@@@:"+server.toString());
//        boolean updated = serverService.update(server);
        boolean updated = serverService.updateFix(map.get("fixId").toString(), map.get("serverIndex").toString());
        if (updated) {
            return Result.success("server fix info updated successfully");
        }
        return Result.fail("server fix info  updated failed");
    }


}
