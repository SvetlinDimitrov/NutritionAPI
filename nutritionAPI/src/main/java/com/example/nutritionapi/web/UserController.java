package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.JwtUtil;
import com.example.nutritionapi.config.security.UserDetailsImp;
import com.example.nutritionapi.domain.dtos.viewDtos.JWT;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.exceptions.WrongUserCredentialsException;
import com.example.nutritionapi.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/nutritionApi/user")
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserServiceImp userServiceImp;

    public UserController(JwtUtil jwtUtil, UserServiceImp userServiceImp) {
        this.jwtUtil = jwtUtil;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserAccount(@Valid @RequestBody RegisterUserDto userDto,
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
    public UserView getUserDetails(Principal principal) {
        String email = principal.getName();
        return userServiceImp.findByEmailView(email);
    }

    @PatchMapping("/details")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserView editUserProfile(Principal principal, @RequestBody EditUserDto userDto) {

        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);
        Long userId = user.getId();

        userServiceImp.editUserEntity(userDto, userId);
        UserView userView = userServiceImp.getUserViewById(userId);

        UserDetailsImp.updateAuthorities();

        return userView;

    }

}
