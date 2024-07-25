package com.hank.springbootmall.controller;

import com.hank.springbootmall.dto.*;
import com.hank.springbootmall.model.User;
import com.hank.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Valid UserRegisterDto userDto) {
        User user = userService.register(userDto);
        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setEmail(user.getEmail());
        responseDto.setCreatedDate(user.getCreatedDate());
        responseDto.setLastModifiedDate(user.getLastModifiedDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto);
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        responseDto.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/me")
    public ResponseEntity<BasicResponseDto> getUserInfo(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        BasicResponseDto userInfo = userService.getUserFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }
}
