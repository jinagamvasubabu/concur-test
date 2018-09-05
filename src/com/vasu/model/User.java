package com.vasu.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name", unique = true, nullable=false)
    private String userName;

    @Column(name="created_at", nullable = false)
    private String createdAt;

    @Column(name="password", nullable = false)
    private String password;


}
