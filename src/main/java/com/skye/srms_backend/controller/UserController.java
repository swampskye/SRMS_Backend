package com.skye.srms_backend.controller;

import com.skye.srms_backend.common.Result;
import com.skye.srms_backend.common.utils.Jwt;
import com.skye.srms_backend.service.IUserService;
import com.skye.srms_backend.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    private Jwt jwt;

    @GetMapping("/all")
    public Result<List<User>> getAll(){
        List<User> list = userService.list();
        return Result.success(list);
    }


}
