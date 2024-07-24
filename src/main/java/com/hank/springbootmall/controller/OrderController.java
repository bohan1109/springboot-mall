package com.hank.springbootmall.controller;

import com.hank.springbootmall.dto.BasicResponseDto;
import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;
import com.hank.springbootmall.service.OrderService;
import com.hank.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestHeader("Authorization") String token,
                                             @RequestBody @Valid CreateOrderDto createOrderDto) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        BasicResponseDto userInfo = userService.getUserFromToken(token);
        Integer userId = userInfo.getUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(userId,createOrderDto));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        BasicResponseDto userInfo = userService.getUserFromToken(token);
        Integer userId = userInfo.getUserId();
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
