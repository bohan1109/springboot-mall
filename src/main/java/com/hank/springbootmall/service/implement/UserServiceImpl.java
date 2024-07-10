package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.dto.UserDto;
import com.hank.springbootmall.model.User;
import com.hank.springbootmall.repository.UserRepository;
import com.hank.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(UserDto userDto) {
        User user = new User();
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }
}
