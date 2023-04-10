package com.skye.srms_backend.service.impl;

import com.skye.srms_backend.entity.User;
import com.skye.srms_backend.mapper.UserMapper;
import com.skye.srms_backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
