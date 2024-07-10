package com.hank.springbootmall.service;

import com.hank.springbootmall.dto.ProductDto;
import com.hank.springbootmall.dto.ProductQueryParams;
import com.hank.springbootmall.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAllProducts(ProductQueryParams productQueryParams, Pageable pageable);
    Product getProductById(long id);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
