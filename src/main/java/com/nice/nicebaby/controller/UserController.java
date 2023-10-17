package com.nice.nicebaby.controller;

import com.nice.nicebaby.dto.user.ReqUserLogin;
import com.nice.nicebaby.dto.user.ReqUserRegister;
import com.nice.nicebaby.dto.user.RespUserLogin;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.service.UserService;
import com.nice.nicebaby.util.HttpResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "使用者")
@RestController
@RequestMapping("/v1/user")
@SecurityScheme(name = "Authorization", in = SecuritySchemeIn.HEADER, type = SecuritySchemeType.APIKEY, description = "JWT")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "使用者註冊", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HttpResult.class))),
            @ApiResponse(responseCode = "503", content = @Content(schema = @Schema(implementation = HttpResult.class)))}, security = @SecurityRequirement(name = "Authorization"))
    @PostMapping("/register")
    public HttpResult<User> register(@RequestBody @Valid ReqUserRegister reqUserRegister) {
        return userService.register(reqUserRegister);
    }

    @Operation(summary = "使用者登入", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HttpResult.class))),
            @ApiResponse(responseCode = "503", content = @Content(schema = @Schema(implementation = HttpResult.class)))}, security = @SecurityRequirement(name = "Authorization"))
    @PostMapping("/login")
    public HttpResult<RespUserLogin> login(@RequestBody @Valid ReqUserLogin reqUserLogin) {
        return userService.login(reqUserLogin);
    }

    @Operation(summary = "根據使用者編號取得使用者", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HttpResult.class))),
            @ApiResponse(responseCode = "503", content = @Content(schema = @Schema(implementation = HttpResult.class)))}, security = @SecurityRequirement(name = "Authorization"))
    @GetMapping
    public HttpResult<User> findById(HttpServletRequest request) {
        User user = (User) request.getAttribute("LoginInfo");
        return userService.findById(user.getUser_id());
    }

    @Operation(summary = "根據帳號取得使用者資訊", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HttpResult.class))),
            @ApiResponse(responseCode = "503", content = @Content(schema = @Schema(implementation = HttpResult.class)))}, security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/getUserInfoByAccount")
    public List<User> findByAllUser() {
        return userService.findByAllUser();
    }

}
