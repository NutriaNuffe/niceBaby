package com.nice.nicebaby.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqUserRegister {

    @Email
    @NotBlank
    @Schema(description = "帳號")
    private String account;

    @NotBlank
    @Schema(description = "密碼")
    private String password;

    @NotBlank
    @Schema(description = "名字")
    private String first_name;

    @NotBlank
    @Schema(description = "姓氏")
    private String last_name;

    @Schema(description = "性別（0: 男生, 1: 女生, 2: 其他）")
    private Integer sex;

    @NotBlank
    @Schema(description = "電話")
    private String phone;

    @Schema(description = "職稱")
    private Integer title;

    @Schema(description = "部門")
    private Integer department;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "使用者圖片")
    private String avatar;

}
