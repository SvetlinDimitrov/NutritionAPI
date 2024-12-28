package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.users.dto.EditUserDto;
import com.example.nutrition_api.domain.users.dto.RegisterUserDto;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.open_ai.UserControllerDocs;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

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

        return userServiceImp.getUserViewById(userId);
    }

}
