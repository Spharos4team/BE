package com.spharos.ssgpoint.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100, nullable = false)
    private String UUID;

    @Column(length = 45, nullable = false)
    private String loginId;

    @Column(length = 100, nullable = false)
    private String userName;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 100)
    private String homeAddress;

    @Column(length = 100)
    private String officeAddress;

    @Column(length = 45, nullable = false)
    private String email;

    //private String role;

    @Column(length = 1, nullable = false, columnDefinition = "int default 1")
    private Integer status;

    @Column(length = 100)
    private String pointPassword;

    @Column(length = 500)
    private String barCode;

}
