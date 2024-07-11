package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.dto.UserDto;
import com.hank.springbootmall.model.User;
import com.hank.springbootmall.repository.UserRepository;
import com.hank.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User register(UserDto userDto) {
        List<User> userExist = userRepository.findByEmail(userDto.getEmail());
        if(!userExist.isEmpty()) {
            log.warn("Email {} 已經被註冊", userDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
