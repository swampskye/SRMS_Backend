package com.skye.srms_backend.service;

import com.skye.srms_backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-10
 */
public interface IUserService extends IService<User> {

    Map<String, Object> signin(User user);
    Map<String, Object> getUserInfo(String token);
}
