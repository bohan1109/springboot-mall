package com.hank.springbootmall.controller;

import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;
import com.hank.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId, @RequestBody @Valid CreateOrderDto createOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(userId,createOrderDto));
    }
}
