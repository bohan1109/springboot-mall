package com.hank.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonBackReference
    private Order order;
}
