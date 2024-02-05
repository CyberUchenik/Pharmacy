package com.example.apteka.models;

import com.example.apteka.models.additionalModels.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author Kapaev Taspolat
 * */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //TODO вот тут нужно будет уточнить правильно ли ставит Hibernate
    private int id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(255)")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "login")
    private String login;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_verified")
    private Boolean is_verified;
    @Column(name = "hashed_password")
    private String hashed_password;
    @Column(name = "def")
    private String def;

    //TODO Надо будет создать сущность(класс) user_types_dic
    @Column(name = "user_type_id")
    private int user_type_id;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

}
