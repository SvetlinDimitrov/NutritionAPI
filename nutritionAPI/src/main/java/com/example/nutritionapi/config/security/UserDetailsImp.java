package com.example.nutritionapi.config.security;

import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.service.UserServiceImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsImp implements UserDetailsService {

    private final UserServiceImp userService;

    public UserDetailsImp(UserServiceImp userService) {
        this.userService = userService;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userService.findByEmail(email);
        return new User(user.getEmail() ,
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+user.getUserDetails().name())));
    }

    public static void updateAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();

            List<GrantedAuthority> newAuthorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + UserDetails.COMPLETED.name())
            );
            List<GrantedAuthority> mutableAuthorities = new ArrayList<>(newAuthorities);

            // if you are planning to use the user authorities throw the application then you should find the way to do the commend code
            // if not then its oke to skip it , if i leave it uncomment i will get an exception;

            // user.getAuthorities().clear();
            // user.getAuthorities().addAll(newAuthorities);

            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    user, authentication.getCredentials(), mutableAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
    }
}
