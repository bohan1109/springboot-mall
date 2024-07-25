package com.hank.springbootmall.repository;

import com.hank.springbootmall.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Page<Order> findByUserId(Integer userId, Pageable pageable);
}
