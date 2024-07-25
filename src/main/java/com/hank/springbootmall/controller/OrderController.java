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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<Order>> getOrders(@RequestHeader("Authorization") String token,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "createdDate") String sortBy,
                                                 @RequestParam(defaultValue = "desc") String sortDirection) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        BasicResponseDto userInfo = userService.getUserFromToken(token);
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Integer userId = userInfo.getUserId();
        Page<Order> orders = orderService.getOrdersByUserId(userId,pageable);
        return ResponseEntity.ok(orders);
    }
}
