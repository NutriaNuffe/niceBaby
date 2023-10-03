package com.nice.nicebaby.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "使用者編號")
    private Long user_id;

    @Column(name = "account", length = 50)
    @Schema(description = "帳號")
    private String account;

    @Column(name = "password", length = 100)
    @Schema(description = "密碼")
    @JsonIgnore
    private String password;

    @Column(name = "first_name", length = 50)
    @Schema(description = "名字")
    private String first_name;

    @Column(name = "last_name", length = 50)
    @Schema(description = "姓氏")
    private String last_name;

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

    @Column(name = "avatar", length = 255)
    @Schema(description = "使用者圖片")
    private String avatar;

    @CreatedDate
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    @Schema(description = "創建日期")
    private Timestamp create_date;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    @Schema(description = "修改日期")
    private Timestamp last_modified_date;

    public Long getId() {
        return user_id;
    }
}
