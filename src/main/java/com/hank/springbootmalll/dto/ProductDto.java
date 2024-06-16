package com.hank.springbootmalll.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private String category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
}
