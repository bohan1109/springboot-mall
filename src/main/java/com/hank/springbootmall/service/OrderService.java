package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order CreateOrder(Integer userId,CreateOrderDto createOrderDto);
    Page<Order> getOrdersByUserId(Integer userId, Pageable pageable);
}
