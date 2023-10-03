package com.nice.nicebaby.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RespUserLogin {
    @Schema(description = "使用者編號")
    private Long user_id;

    @Schema(description = "帳號")
    private String account;

    @Schema(description = "密碼")
    @JsonIgnore
    private String password;

    @Schema(description = "名字")
    private String first_name;

    @Schema(description = "姓氏")
    private String last_name;

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
    private Timestamp create_date;

    @Schema(description = "修改日期")
    private Timestamp last_modified_date;
    private String token;

}
