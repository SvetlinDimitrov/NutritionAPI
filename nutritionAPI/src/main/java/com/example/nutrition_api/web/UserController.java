package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.users.dto.EditUserDto;
import com.example.nutrition_api.domain.users.dto.LoginUserDto;
import com.example.nutrition_api.domain.users.dto.RegisterUserDto;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.security.dto.JWT;
import com.example.nutrition_api.infrastructure.security.service.JwtServiceImp;
import com.example.nutrition_api.infrastructure.security.service.UserDetailsImp;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutritionApi/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtServiceImp jwtUtil;
    private final UserServiceImp userServiceImp;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody RegisterUserDto userDto,
                                  BindingResult result) throws WrongUserCredentialsException {
        if (result.hasErrors()) {
            throw new WrongUserCredentialsException(result.getFieldErrors());
        }
        userServiceImp.register(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JWT login(@Valid @RequestBody LoginUserDto userDto,
                     BindingResult result) throws WrongUserCredentialsException {

        if (!userServiceImp.login(userDto)) {
            result.addError(new FieldError("username_password", "password", "wrong username or password"));
        }

        if (result.hasErrors()) {
            throw new WrongUserCredentialsException(result.getFieldErrors());
        }
        Long userID = userServiceImp.findByEmail(userDto.email()).getId();

        return new JWT(jwtUtil.createJwtToken(userID));
    }


    @GetMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    public UserView get(Principal principal) {
        String email = principal.getName();
        return userServiceImp.findByEmailView(email);
    }

    @PatchMapping("/details")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserView edit(Principal principal, @RequestBody EditUserDto userDto) {
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);
        Long userId = user.getId();

        userServiceImp.editUserEntity(userDto, userId);
        UserView userView = userServiceImp.getUserViewById(userId);

        UserDetailsImp.updateAuthorities();

        return userView;

    }

}
