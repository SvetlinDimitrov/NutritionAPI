package com.example.nutritionapi.config.security;

import com.example.nutritionapi.service.UserServiceImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsImp implements UserDetailsService {

    private final UserServiceImp userService;

    public UserDetailsImp(UserServiceImp userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserPrincipal(userService.findByEmail(email));
    }

    public static void updateAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

            List<GrantedAuthority> newAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + com.example.nutritionapi.domain.constants.enums.UserDetails.COMPLETED.name()));
            userDetails.setAuthorities(newAuthorities);

            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    userDetails, authentication.getCredentials(), newAuthorities);

            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
    }
}
