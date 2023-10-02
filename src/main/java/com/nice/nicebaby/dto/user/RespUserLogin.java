package com.nice.nicebaby.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RespUserLogin {
    @Schema(description = "使用者編號")
    private Long userId;

    @Schema(description = "帳號")
    private String account;

    @Schema(description = "密碼")
    @JsonIgnore
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

    @Schema(description = "創建日期")
    private Timestamp createDate;

    @Schema(description = "修改日期")
    private Timestamp lastModifiedDate;
    private String token;

}
