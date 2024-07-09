package com.hank.springbootmalll.service;

import com.hank.springbootmalll.constant.ProductCategory;
import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(ProductCategory category,String productName);
    Product getProductById(long id);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
