package com.hank.springbootmalll.service;

import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(long id);
    Product createProduct(ProductDto productDto);
}
