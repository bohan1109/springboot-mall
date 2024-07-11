package com.hank.springbootmall.controller;

import com.hank.springbootmall.dto.UserRegisterDto;
import com.hank.springbootmall.model.User;
import com.hank.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDto));
    }
}
