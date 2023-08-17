package com.spharos.ssgpoint.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,length = 100,nullable = false)
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

    public void updateUserInfo(String address, String email){
        this.address =address;
        this.email = email;
    }

    public void hashPassword(String password){

        this.password = new BCryptPasswordEncoder().encode(password);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
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
