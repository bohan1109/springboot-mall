package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.repository.UserRepository;
import com.hank.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
}
