package com.hank.springbootmall.dto;

import com.hank.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    private String productName;
    private ProductCategory category;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
