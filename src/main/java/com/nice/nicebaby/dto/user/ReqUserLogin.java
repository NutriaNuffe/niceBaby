package com.nice.nicebaby.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqUserLogin {

    @NotBlank
    private String account;

    @NotBlank
    private String password;

}
