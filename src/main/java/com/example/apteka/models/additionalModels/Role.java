package com.example.apteka.models.additionalModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles_dic")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_def")
    private String roleDef;
}
//TODO создать вспомогательный класс только для Permissions