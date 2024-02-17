package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.user.EditUserDto;
import com.api.reactive_nutritionapi.domain.dtos.user.LoginUserDto;
import com.api.reactive_nutritionapi.domain.dtos.user.RegisterUserDto;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.JwtResponse;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.UserView;
import com.api.reactive_nutritionapi.service.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/nutritionApi/user")
public class UserController {

  private final UserServiceImp userServiceImp;

  public UserController(UserServiceImp userServiceImp) {
    this.userServiceImp = userServiceImp;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createUserAccount(@RequestBody Mono<RegisterUserDto> userDto) {
    return userDto.flatMap(userServiceImp::register);
  }

  @PostMapping("/login")
  public Mono<JwtResponse> login(@RequestBody Mono<LoginUserDto> authRequest) {
    return authRequest.flatMap(userServiceImp::login);
  }


  @GetMapping("/details")
  @ResponseStatus(HttpStatus.OK)
  public Mono<UserView> getUserDetails(Mono<Principal> principal) {
    return principal
        .flatMap(user -> userServiceImp.getUserViewByEmail(user.getName()));
  }

  @PatchMapping("/details")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<JwtResponse> editUserProfile(Mono<Principal> principal, @RequestBody Mono<EditUserDto> userDto) {
    return principal
        .zipWith(userDto)
        .flatMap(tuple2 -> userServiceImp.editUserEntity(tuple2.getT2(), tuple2.getT1().getName()));
  }

  @PostMapping("/records")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createNewRecord(Mono<Principal> principal) {
    return principal.flatMap(user -> userServiceImp.addNewRecordByUserId(user.getName()));
  }


}
