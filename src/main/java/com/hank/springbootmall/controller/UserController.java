package com.hank.springbootmall.controller;

import com.hank.springbootmall.dto.UserLoginDto;
import com.hank.springbootmall.dto.UserLoginResponseDto;
import com.hank.springbootmall.dto.UserRegisterDto;
import com.hank.springbootmall.dto.UserRegisterResponseDto;
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
}
