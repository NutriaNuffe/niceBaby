package com.nice.nicebaby.controller;

//import com.nice.nicebaby.model.User;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "使用者")
@RestController
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/getUserInfoByAccount")
    public List<User> findByAllUser() {
        return userService.findByAllUser();
    }

}
