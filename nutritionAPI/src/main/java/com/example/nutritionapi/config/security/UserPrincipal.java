package com.example.nutritionapi.config.security;

import com.example.nutritionapi.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;


public class UserPrincipal extends User {

    private Long id;
    private String username;
    private String email;

    private List<GrantedAuthority> authorities;

    public UserPrincipal(UserEntity entity) {
        super(entity.getUsername(), entity.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + entity.getUserDetails().name())));
        this.username = entity.getUsername();
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + entity.getUserDetails().name()));
    }


    public Long getId() {
        return id;
    }

    public UserPrincipal setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserPrincipal setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserPrincipal setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserPrincipal setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
}
