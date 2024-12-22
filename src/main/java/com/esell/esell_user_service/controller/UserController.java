package com.esell.esell_user_service.controller;

import com.esell.esell_user_service.dto.request.RequestUserDto;
import com.esell.esell_user_service.dto.request.RequestUserLoginDeto;
import com.esell.esell_user_service.entity.User;
import com.esell.esell_user_service.exception.DuplicateEntryException;
import com.esell.esell_user_service.repo.UserRepository;
import com.esell.esell_user_service.service.impl.impl.UserServiceImpl;
import com.esell.esell_user_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createUser(@RequestBody RequestUserDto requestUserDto) {
        try {
            userService.signup(requestUserDto);
            return new ResponseEntity<>(
                    new StandardResponse(201, null, "User Created"), HttpStatus.CREATED
            );
        } catch (RuntimeException e) {
           return new ResponseEntity<>(
                   new StandardResponse(500,null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
           );
        }

    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> login(@RequestBody RequestUserLoginDeto requestUserDto) {
        try {
            userService.login(requestUserDto);
            return new ResponseEntity<>(
                    new StandardResponse(201, null, "User Login"), HttpStatus.CREATED
            );
        }catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new StandardResponse(500,null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
}
