package com.nice.nicebaby.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @Schema(description = "使用者編號")
    private Long userId;

    @Column(name = "account")
    @Schema(description = "帳號")
    private String account;

    @Column(name = "password")
    @Schema(description = "密碼")
    private String password;

    @Column(name = "firstName")
    @Schema(description = "名字")
    private String firstName;

    @Column(name = "lastName")
    @Schema(description = "姓氏")
    private String lastName;

    @Column(name = "sex")
    @Schema(description = "性別")
    private String sex;

    @Column(name = "phone")
    @Schema(description = "電話")
    private String phone;

    @Column(name = "title")
    @Schema(description = "職稱")
    private String title;

    @Column(name = "department")
    @Schema(description = "部門")
    private String department;

    @Column(name = "address")
    @Schema(description = "地址")
    private String address;

    @Column(name = "avatar")
    @Schema(description = "使用者圖片")
    private String avatar;

    public Long getId() {
        return userId;
    }
}
