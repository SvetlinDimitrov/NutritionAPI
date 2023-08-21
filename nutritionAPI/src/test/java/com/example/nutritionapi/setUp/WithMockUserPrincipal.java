package com.example.nutritionapi.setUp;


import com.example.nutritionapi.config.security.UserPrincipal;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.entity.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


// Custom annotation to simulate @AuthenticationPrincipal
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithMockUserPrincipal {
    String username() default "user";
    String id() default "1";


}

class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUserPrincipal> {
    @Override
    public SecurityContext createSecurityContext(WithMockUserPrincipal annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserEntity user = new UserEntity();
        user.setUsername(annotation.username());
        user.setPassword("easy");
        user.setId(Long.parseLong(annotation.id()));
        user.setUserDetails(UserDetails.NOT_COMPLETED);


        UserPrincipal userPrincipal = new UserPrincipal(user);


        Authentication auth = new UsernamePasswordAuthenticationToken(

                userPrincipal
                , userPrincipal.getPassword()
                , userPrincipal.getAuthorities());

        context.setAuthentication(auth);

        return context;
    }
}