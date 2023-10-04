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
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
@Table(name = "user")
public class User {

    @ManyToMany(targetEntity = Department.class) // 一個 User 實體會對應到多個 Department 實體，一個 Department 實體會對應到多個 User
    @JoinTable( // 中間表的註解，維護多對多的關聯
            name = "user_department", // 中間表的名稱
            joinColumns = @JoinColumn(name = "user_id"), // 對應 User 實體的欄位名稱
            inverseJoinColumns = @JoinColumn(name = "department_id") // 對應 Department 欄位名稱
    )
    private Set<Department> departments = new HashSet<>(); // Set 確保元素不重複，HashSet 提供快速的查詢操作並確保元素是唯一的，如果沒有初始化，它可能會是 null，會導致空指針異常

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "使用者編號")
    private Long user_id;

    @Column(name = "account", unique = true, length = 50)
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
