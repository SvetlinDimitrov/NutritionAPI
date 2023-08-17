package com.example.nutritionapi.config.security;

import com.example.nutritionapi.domain.dtos.user.UserView;
import org.springframework.security.authentication.AbstractAuthenticationToken;




public class UserViewAuthenticationToken extends AbstractAuthenticationToken {

    private UserView userView;

    public UserViewAuthenticationToken(UserView userView) {
        super(userView.getAuthorities());
        this.userView = userView;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserView getPrincipal() {
        return userView;
    }
}
