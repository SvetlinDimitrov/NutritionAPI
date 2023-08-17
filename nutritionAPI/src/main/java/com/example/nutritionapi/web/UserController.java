package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.JwtUtil;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.user.UserView;
import com.example.nutritionapi.service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
                                                        BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + " : "+ error.getDefaultMessage())
                    .collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }

        userServiceImp.register(userDto);

        return new ResponseEntity<>("Successfuly created account" , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (){
        return new ResponseEntity<>(jwtUtil.createJwtToken(1L) , HttpStatus.OK);
    }


    @GetMapping("/details")
    public ResponseEntity<UserView> getUserDetails(@AuthenticationPrincipal UserView userView){
        //TODO:check if the user will be changed after time
        return new ResponseEntity<>(userView , HttpStatus.OK);
    }
}
