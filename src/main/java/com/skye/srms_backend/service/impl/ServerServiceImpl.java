package com.skye.srms_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skye.srms_backend.entity.Server;
import com.skye.srms_backend.mapper.ServerMapper;
import com.skye.srms_backend.service.IServerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skye.srms_backend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        QueryWrapper<Server> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("server_index");
        List<Server> serverList = this.baseMapper.selectList(wrapper);
        return serverList;
    }

    @Override
    public List<Server> getFailedServer() {
        QueryWrapper<Server> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("server_index");
        wrapper.eq("is_working",false);
        List<Server> serverList = this.baseMapper.selectList(wrapper);
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
}
