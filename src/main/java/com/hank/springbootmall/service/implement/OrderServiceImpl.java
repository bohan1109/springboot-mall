package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.repository.OrderRepository;
import com.hank.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
