package com.hank.springbootmalll.service;

import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.dto.ProductQueryParams;
import com.hank.springbootmalll.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getAllProducts(ProductQueryParams productQueryParams, Pageable pageable);
    Product getProductById(long id);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
