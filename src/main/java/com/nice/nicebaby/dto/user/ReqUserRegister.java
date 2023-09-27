package com.nice.nicebaby.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqUserRegister {

    @Schema(description = "帳號")
    private String account;

    @Schema(description = "密碼")
    private String password;

    @Schema(description = "名字")
    private String firstName;

    @Schema(description = "姓氏")
    private String lastName;

    @Schema(description = "性別（0: 男生, 1: 女生, 2: 其他）")
    private Integer sex;

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
