package com.nice.nicebaby.controller;

//import com.nice.nicebaby.model.User;

import com.nice.nicebaby.dto.user.ReqUserLogin;
import com.nice.nicebaby.dto.user.ReqUserRegister;
import com.nice.nicebaby.dto.user.RespUserLogin;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.service.UserService;
import com.nice.nicebaby.util.HttpResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "使用者")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpResult<User> register(@RequestBody @Valid ReqUserRegister reqUserRegister) {
        return userService.register(reqUserRegister);
    }

    @PostMapping("/login")
    public HttpResult<RespUserLogin> login(@RequestBody @Valid ReqUserLogin reqUserLogin) {
        return userService.login(reqUserLogin);
    }

    @GetMapping("/{userId}")
    public HttpResult<User> findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/getUserInfoByAccount")
    public List<User> findByAllUser() {
        return userService.findByAllUser();
    }

}
