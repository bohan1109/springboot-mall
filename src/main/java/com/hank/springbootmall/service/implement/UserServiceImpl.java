package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.config.JwtUtil;
import com.hank.springbootmall.dto.BasicResponseDto;
import com.hank.springbootmall.dto.UserLoginDto;
import com.hank.springbootmall.dto.UserRegisterDto;
import com.hank.springbootmall.model.User;
import com.hank.springbootmall.repository.UserRepository;
import com.hank.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        Optional<User> userExist = userRepository.findByEmail(userRegisterDto.getEmail());
        if(userExist.isPresent()) {
            log.warn("Email {} 已經被註冊", userRegisterDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        User user = new User();
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        Optional<User> userExist = userRepository.findByEmail(userLoginDto.getEmail());
        if (userExist.isEmpty()) {
            log.warn("此 Email {} 尚未被註冊  ", userLoginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not registered");
        }
        User user = userExist.get();
        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            CustomUserDetailsImpl userDetails = new CustomUserDetailsImpl(
                    user.getUserId(),
                    user.getEmail(),
                    user.getPassword()
            );
            return jwtUtil.generateToken(userDetails);
        } else {
            log.warn("密碼錯誤， Email: {}", userLoginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    @Override
    public BasicResponseDto getUserFromToken(String token) {
        BasicResponseDto basicResponseDto = new BasicResponseDto();
        basicResponseDto.setUserId(jwtUtil.extractUserId(token));
        basicResponseDto.setEmail(jwtUtil.extractEmail(token));
        return basicResponseDto;
    }
}
