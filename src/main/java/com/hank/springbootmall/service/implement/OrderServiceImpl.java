package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.dto.BuyItem;
import com.hank.springbootmall.dto.CreateOrderDto;
import com.hank.springbootmall.model.Order;
import com.hank.springbootmall.model.OrderItem;
import com.hank.springbootmall.model.Product;
import com.hank.springbootmall.repository.OrderItemRepository;
import com.hank.springbootmall.repository.OrderRepository;
import com.hank.springbootmall.repository.ProductRepository;
import com.hank.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transient
    @Override
    public Order CreateOrder(Integer userId, CreateOrderDto createOrderDto) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem: createOrderDto.getBuyItemList()){
            Optional<Product> product = productRepository.findByProductId(buyItem.getProductId());
            if(product.isPresent()){
                //計算總價
                int amount = buyItem.getQuantity()*product.get().getPrice();
                totalAmount += amount;
                //轉換buyItem to OrderItem
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(buyItem.getProductId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setAmount(amount);
                orderItemList.add(orderItem);
            }


        }
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        Date now = new Date();
        order.setCreatedDate(now);
        order.setLastModifiedDate(now);
        order = orderRepository.save(order);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getOrderId());
        }
        orderItemRepository.saveAll(orderItemList);
        return order;
    }
}