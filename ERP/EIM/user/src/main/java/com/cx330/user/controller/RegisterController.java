package com.cx330.user.controller;


import com.cx330.entity.User;
import com.cx330.user.service.RegisterService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Result<String> register(User user) {
        int addUser = registerService.register(user);
        if (addUser > 0)
            return new Result<String>().ok().message("注册成功");
        return new Result<String>().error().message("注册失败");
    }
}
