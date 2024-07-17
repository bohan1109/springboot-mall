package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.UserLoginDto;
import com.hank.springbootmall.dto.UserRegisterDto;
import com.hank.springbootmall.model.User;

public interface UserService {
    User register(UserRegisterDto userRegisterDto);
    String login(UserLoginDto userLoginDto);
    String getUserFromToken(String token);
}
