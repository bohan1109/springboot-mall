package com.hank.springbootmall.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class BuyItem {
    Integer productId;
    Integer quantity;
}
