package com.hank.springbootmall.repository;

import com.hank.springbootmall.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(Integer userId);
}
