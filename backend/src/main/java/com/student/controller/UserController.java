package com.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.entity.User;
import com.student.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    // 登录请求体（只接收用户名和密码）
    public static class LoginRequest {
        public String username;
        public String password;
    }

    // 返回给前端的用户信息（不带密码）
    public static class LoginUserVO {
        public Integer id;
        public String username;
        public String role;
    }

    @PostMapping("/login")
    public LoginUserVO login(@RequestBody LoginRequest req) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, req.username)
                .eq(User::getPassword, req.password);

        User user = userService.getOne(qw);

        if (user == null) {
            // 简单点：登录失败直接抛异常，前端看到 500/错误信息
            throw new RuntimeException("用户名或密码错误");
        }

        // 只返回必要信息，不返回密码
        LoginUserVO vo = new LoginUserVO();
        vo.id = user.getId();
        vo.username = user.getUsername();
        vo.role = user.getRole();
        return vo;
    }
}
