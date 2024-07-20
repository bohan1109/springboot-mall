package com.hank.springbootmall.controller;

import com.hank.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class OrderController {
    @Autowired
    private OrderService orderService;
}
