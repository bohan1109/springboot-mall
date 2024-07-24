package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Order CreateOrder(Integer userId,CreateOrderDto createOrderDto);
    List<Order> getOrdersByUserId(Integer userId);
}
