package com.skye.srms_backend;

import com.skye.srms_backend.utils.Jwt;
import com.skye.srms_backend.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {

    @Autowired
    private Jwt jwt;

    @Test
    public void testJwt(){
        User user = new User();
        user.setUsername("skye");
        user.setPhone("13633920505");
        String token = jwt.createToken(user);
        System.out.println("token----------->"+token);
        Claims claims = jwt.parseToken(token);
        System.out.println("claims---------->"+claims);
        User user1 = jwt.parseToken(token, User.class);
        System.out.println("user1---------->"+user1);
    }
}
