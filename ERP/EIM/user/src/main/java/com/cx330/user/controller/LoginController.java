package com.cx330.user.controller;


import com.cx330.entity.User;
import com.cx330.user.service.LoginService;
import com.cx330.utility.JWTUtil;
import com.cx330.utility.Result;
import com.cx330.utility.ThreadLocalBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User login = loginService.login(username, password);
        if (login == null) {
            return new Result<String>().error().message("用户名或密码错误");
        }
        return new Result<String>().ok().data(JWTUtil.setToken((User) ThreadLocalBuffer.get()));

    }

    @GetMapping("/logout")
    public Result<String> logout() {
        loginService.logout();
        return new Result<String>().ok().message("退出成功");
    }

}
