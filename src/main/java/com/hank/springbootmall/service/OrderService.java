package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;

public interface OrderService {
    Order CreateOrder(Integer userId,CreateOrderDto createOrderDto);
}
