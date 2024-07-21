package com.hank.springbootmall.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    @NotEmpty
    private List<BuyItem> buyItemList;
}
