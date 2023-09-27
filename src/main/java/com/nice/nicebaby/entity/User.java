package com.nice.nicebaby.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    @Schema(description = "使用者編號")
    private Long userId;

    @Column(name = "account", length = 50)
    @Schema(description = "帳號")
    private String account;

    @Column(name = "password", length = 100)
    @Schema(description = "密碼")
    @JsonIgnore
    private String password;

    @Column(name = "firstName", length = 50)
    @Schema(description = "名字")
    private String firstName;

    @Column(name = "lastName", length = 50)
    @Schema(description = "姓氏")
    private String lastName;

    @Column(name = "sex")
    @Schema(description = "性別（0: 男生, 1: 女生, 2: 其他）")
    private Integer sex;

    @Column(name = "phone", length = 20)
    @Schema(description = "電話")
    private String phone;

    @Column(name = "title")
    @Schema(description = "職稱")
    private Integer title;

    @Column(name = "department")
    @Schema(description = "部門")
    private Integer department;

    @Column(name = "address", length = 255)
    @Schema(description = "地址")
    private String address;

    @Column(name = "avatar")
    @Schema(description = "使用者圖片")
    private String avatar;

    @CreatedDate
    @Column(name = "createDate", columnDefinition = "TIMESTAMP")
    @Schema(description = "創建日期")
    private Timestamp createDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate", columnDefinition = "TIMESTAMP")
    @Schema(description = "修改日期")
    private Timestamp lastModifiedDate;

    public Long getId() {
        return userId;
    }
}
