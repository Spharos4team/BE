package com.spharos.ssgpoint.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Random;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String uuid;
    @Column(length = 45,nullable = false)
    private String loginId;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private String password;
    @Column(length =20,nullable = false)
    private String phone;
    @Column(length = 100)
    private String address;

    @Column(length = 45 ,nullable = false)
    private String email;

    //private String role;

    @Column(length = 1 ,nullable = false,columnDefinition = "int default 1")
    private Integer status;
    @Column(length = 100 )
    private String pointPassword;
    @Column(length = 500 )
    private String barCode;

    /**
     * 회원정보 수정
     */
    public void updateUserInfo(String address, String email){
        this.address =address;
        this.email = email;
    }
    /**
     * 비밀번호 암호화
     */
    public void hashPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 포인트 비밀번호 수정
     */
    public void updatePointPassword(String pointPassword) {
        this.pointPassword = pointPassword;
    }

   /* *//**
     * 전체 바코드 생성
     *//*
    @PostPersist
    public void createPointBardCode() {
        String generateBarcode = generateBarcode();
        String formattedId;

        if (id > 99999999) {
            formattedId = "99999999";  // 아니면 id - 1억해서 ?
        } else {
            formattedId = String.format("%08d", id);
        }

        String checkBarcode= generateBarcode+formattedId;
        validateBarcode(checkBarcode);
        this.barCode = checkBarcode;
    }

    private String generateBarcode() {

        Random random = new Random();
        String randomDigits = String.format("%04d", random.nextInt(10000));
        return "9350" + randomDigits;
    }*/

    public void generateBarcode(String barCode){
        this.barCode = barCode;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
