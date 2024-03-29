package com.skye.srms_backend.service;

import com.skye.srms_backend.entity.Server;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-14
 */
public interface IServerService extends IService<Server> {


    boolean add(Server server);

    List<Server> getServerListOrderByAscOnName();

    List<Server> getFailedServer();

    Server getServerInfo(String serverIndex);

    boolean update(Server server, String username);

    List<Server> getIssuedServer();

    boolean updateFix(String fixId, String serverIndex);
}
