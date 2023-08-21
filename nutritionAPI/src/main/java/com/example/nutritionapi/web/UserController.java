package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.JwtUtil;
import com.example.nutritionapi.config.security.UserDetailsImp;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.exceptions.WrongUserCredentialsException;
import com.example.nutritionapi.service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<String> createUserAccount(@Valid @RequestBody RegisterUserDto userDto,
                                                        BindingResult result) throws WrongUserCredentialsException {
        if(result.hasErrors()){
            throw new WrongUserCredentialsException(result.getFieldErrors());
        }

        userServiceImp.register(userDto);

        return new ResponseEntity<>("Successfuly created account" , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginUserDto userDto,
                                         BindingResult result) throws WrongUserCredentialsException {

        if(!userServiceImp.login(userDto)){
            result.addError(new FieldError("username_password", "password" , "wrong username or password"));
        }

        if(result.hasErrors()){
            throw new WrongUserCredentialsException(result.getFieldErrors());
        }
        Long userID = userServiceImp.findByEmail(userDto.getEmail()).getId();

        return new ResponseEntity<>(jwtUtil.createJwtToken(userID) , HttpStatus.OK);
    }


    @GetMapping("/details")
    public ResponseEntity<UserView> getUserDetails(Principal principal){
        String email = principal.getName();
        UserView userView = new UserView(userServiceImp.findByEmail(email));
        return new ResponseEntity<>(userView, HttpStatus.OK);
    }

    @PatchMapping("/details/{userId}")
    public ResponseEntity<UserView> editUserProfile(@RequestBody EditUserDto userDto,
                                                    @PathVariable Long userId){


        userServiceImp.editUserEntity(userDto , userId);
        UserView userView = userServiceImp.getUserViewById(userId);

        UserDetailsImp.updateAuthorities();

        return new ResponseEntity<>(userView , HttpStatus.ACCEPTED);

    }

}
