package com.skye.srms_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skye.srms_backend.utils.Result;

import com.skye.srms_backend.utils.Jwt;
import com.skye.srms_backend.service.IUserService;
import com.skye.srms_backend.entity.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-10
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private Jwt jwt;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/all")
    public Result<List<User>> getAll() {
        List<User> list = userService.list();
        return Result.success(list);
    }


    @PostMapping("/signup")
    public Result<?> adduser(@RequestBody User user){
//    public Result<?> adduser(@RequestBody User user){
        log.debug(user.toString());

//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUsername, user.getUsername());
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        User signupUser = new User();
//        try {
//            userService.save(user);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return Result.success("sign up successfully");
    }


    @PostMapping("/signin")
    public Result<Map<String, Object>> signin(@RequestBody User user) {
        Map<String, Object> data = userService.signin(user);
        log.debug("-------in controller-------------"+user);
        if (data != null){
            return Result.success(data,"login successful!");
        }
        return Result.fail("login failed!");
    }


    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam(name = "token") String token){
        log.debug("in serviceImpl----------------"+token);
        Map<String, Object> userInfo = userService.getUserInfo(token);
        if (userInfo != null){
            return Result.success(userInfo,"get user info successful!");
        }
        return Result.fail("fail to get user info ");
    }

}
