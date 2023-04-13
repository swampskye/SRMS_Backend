package com.skye.srms_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skye.srms_backend.utils.Result;

import com.skye.srms_backend.utils.Jwt;
import com.skye.srms_backend.service.IUserService;
import com.skye.srms_backend.entity.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Result<?> signup(@Validated @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String failedMsg = bindingResult.getFieldError().getDefaultMessage();
            return Result.fail(failedMsg);
        }
        boolean b = userService.addUser(user);

        if (b){
            return Result.success("sign up successfully");
        }
        return Result.fail("sign up failed");
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


    @PutMapping("/update")
    public Result<Map<String, Object>> updateUserInfo(@Validated  @RequestBody User user){
        Map<String, Object> updatedUserInfo = userService.updateUserInfo(user);

        if (updatedUserInfo != null){
            return Result.success(updatedUserInfo, "update successfully");
        }
        return Result.success("update failed");
    }

}
