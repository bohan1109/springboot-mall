package com.hank.springbootmalll.service.implement;

import com.hank.springbootmalll.constant.ProductCategory;
import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.model.Product;
import com.hank.springbootmalll.repository.ProductRepository;
import com.hank.springbootmalll.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
//        String categoryStr = productDto.getCategory();
//        ProductCategory productCategory = ProductCategory.valueOf(categoryStr);
//        product.setCategory(productCategory);
        product.setCategory(ProductCategory.valueOf(productDto.getCategory()));
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
        product.setCreatedDate(new Date());
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }
}
