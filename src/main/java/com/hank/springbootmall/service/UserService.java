package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.UserDto;
import com.hank.springbootmall.model.User;

public interface UserService {
    User register(UserDto userDto);
}
