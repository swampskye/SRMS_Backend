package com.skye.srms_backend;

import com.skye.srms_backend.entity.User;
import com.skye.srms_backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class SrmsBackendApplicationTests {

    @Test
    void contextLoads() {
    }



    @Resource
    private UserMapper userMapper;

    @Test
    void testMapper(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }





}
