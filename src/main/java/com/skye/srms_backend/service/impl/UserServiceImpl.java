package com.skye.srms_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skye.srms_backend.utils.Jwt;
import com.skye.srms_backend.entity.User;
import com.skye.srms_backend.mapper.UserMapper;
import com.skye.srms_backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Jwt jwt;


    //    @Override
    public Map<String, Object> signin(User user) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);


        log.debug("-------in serviceImpl------");
        log.debug("-------loginUser------" + loginUser.toString());
        log.debug("---------user---------" + user.toString());
        log.debug("-----matches or not -----" + passwordEncoder.matches(user.getPassword(), loginUser.getPassword()));
        log.debug("---user.getPassword():" + user.getPassword() + "   ------loginUser.getPassword()" + loginUser.getPassword());

        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
//            loginUser.setPassword(null);
            String token = jwt.createToken(loginUser);

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return data;
        }
        return null;
    }


    //    @Override
    public Map<String, Object> getUserInfo(String token) {

        User user = null;
        try {
            user = jwt.parseToken(token, User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("phone", user.getPhone());
        data.put("is_admin", user.getIsAdmin());
        data.put("is_active", user.getIsActive());
        data.put("short_intro", user.getShortIntro());
        data.put("img", user.getImg());

        return data;
    }

    @Override
    public boolean addUser(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.exists("select * from user where username = '"+user.getUsername()+"' or email = '"+user.getEmail()+"' or phone = '"+user.getPhone()+"'");
        List<User> userList = this.baseMapper.selectList(queryWrapper);

        if (userList.size() != 0){
            log.debug("-------------addUser != null");
            log.debug(userList.toString());
            log.debug("-------------return false1");
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        int insert = 0;
        try {
            insert = this.baseMapper.insert(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if ( insert != 0  ){
            log.debug("-------------return true");
            return true;
        }
        log.debug("-------------return false2");
        return false;
    }

    @Override
    public Map<String, Object> updateUserInfo(User user) {

        log.debug("-----------user which need to be updated:"+user.toString());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        int update = this.baseMapper.update(user,wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        return data;

    }


}
