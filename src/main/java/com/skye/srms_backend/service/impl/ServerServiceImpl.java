package com.skye.srms_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skye.srms_backend.entity.FixInfo;
import com.skye.srms_backend.entity.Server;
import com.skye.srms_backend.mapper.ServerMapper;
import com.skye.srms_backend.service.IServerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-14
 */
@Slf4j
@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements IServerService {


//    @Autowired
//    private SimpleMapper simpleMapper;

    @Autowired
    private FixInfoServiceImpl fixInfoService;

    @Override
    public boolean add(Server server) {


        server.setCreatedDate(LocalDateTime.now());
        log.debug("----------------add - server:"+server.toString());

        if (this.baseMapper.insert(server) != 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Server> getServerListOrderByAscOnName() {
        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<Server>();
        lambdaQueryWrapper.orderByAsc(Server::getServerIndex);
        List<Server> serverList = this.baseMapper.selectList(lambdaQueryWrapper);
        return serverList;
    }

    @Override
    public List<Server> getFailedServer() {
//        QueryWrapper<Server> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("server_index");
//        wrapper.eq("is_working",false);
//        List<Server> serverList = this.baseMapper.selectList(wrapper);

        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<Server>();
        lambdaQueryWrapper.orderByAsc(Server::getServerIndex);
        lambdaQueryWrapper.eq(Server::getIsWorking,false);
        List<Server> serverList = this.baseMapper.selectList(lambdaQueryWrapper);
        return serverList;
    }

    @Override
    public List<Server> getIssuedServer() {
        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<Server>();
        lambdaQueryWrapper.orderByAsc(Server::getServerIndex);
        lambdaQueryWrapper.isNotNull(Server::getFixId);
        List<Server> serverList = this.baseMapper.selectList(lambdaQueryWrapper);


        return serverList;
    }




    @Override
    public Server getServerInfo(String serverIndex) {
        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Server::getServerIndex,serverIndex);
        Server server = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (server != null){
            return server;
        }
        return null;
    }

    @Override
    public boolean update(Server server, String username) {
        log.debug("$$$$$$$$$$$updated server1:"+server.toString());


//        FixInfo fixInfo = new FixInfo();
//        fixInfo.setId(server.getFixId());
//
//
//        // true -> false   should not set fixinfo to null
//        if (!server.getIsWorking()){
//
//        }else{
//            // false -> true   should  set fixinfo to null
//            server.setFixId(null);
//            fixInfo.setFixer(username);
//            fixInfo.setFixDate(LocalDateTime.now());
//            LambdaQueryWrapper<FixInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(FixInfo::getId, fixInfo.getId());
//            fixInfoService.getBaseMapper().update(fixInfo, lambdaQueryWrapper);
//        }



        //true -> false , fixinfo need no change


        // false -> true
        // should
        // 1. set fixId filed in server to null
        // 2. set fixer filed in fixInfo to username
        // 3. set fixDate filed in fixInfo to now
        if (server.getIsWorking()){
            FixInfo fixInfo = new FixInfo();
            fixInfo.setId(server.getFixId());
            server.setFixId(null);
            fixInfo.setFixer(username);
            fixInfo.setFixDate(LocalDateTime.now());
            LambdaQueryWrapper<FixInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(FixInfo::getId, fixInfo.getId());
            fixInfoService.getBaseMapper().update(fixInfo, lambdaQueryWrapper);
        }


        log.debug("$$$$$$$$$$$updated server2:"+server.toString());

        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Server::getServerIndex, server.getServerIndex());
        int update = this.baseMapper.update(server, lambdaQueryWrapper);

        log.debug("$$$$$$$$$$$updated server3:"+server.toString());

        if (update != 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFix(String fixId, String serverIndex) {
        LambdaQueryWrapper<Server> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Server::getServerIndex, serverIndex);
        Server server = this.baseMapper.selectOne(lambdaQueryWrapper);
        server.setFixId(fixId);
        int update = this.baseMapper.update(server, lambdaQueryWrapper);
        if (update != 0){
            return true;
        }
        return false;
    }




}
