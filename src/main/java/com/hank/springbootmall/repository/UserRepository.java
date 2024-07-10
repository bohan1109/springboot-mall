package com.hank.springbootmall.repository;

import com.hank.springbootmall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
