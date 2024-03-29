package com.nice.nicebaby.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "department")
public class Department {

    @ManyToMany(mappedBy = "departments")
    private Set<User> users = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    @Schema(description = "部門編號")
    private Long department_id;

    @Column(name = "account", length = 100)
    @Schema(description = "部門名稱")
    private String department_name;

    @Column(name = "department_manager_id")
    @Schema(description = "部門主管編號")
    private Long department_manager_id;

}
