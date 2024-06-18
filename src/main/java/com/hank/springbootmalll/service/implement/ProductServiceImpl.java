package com.hank.springbootmalll.service.implement;

import com.hank.springbootmalll.constant.ProductCategory;
import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.exception.ProductNotFoundException;
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
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        updateEntityFromDto(product, productDto);
        product.setCreatedDate(new Date());
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + id));

        if (product != null) {
            updateEntityFromDto(product, productDto);
            product.setLastModifiedDate(new Date());
            return productRepository.save(product);
        }
        return null;
    }

    private void updateEntityFromDto(Product product, ProductDto productDto) {
        product.setProductName(productDto.getProductName());
//        String categoryStr = productDto.getCategory();
//        ProductCategory productCategory = ProductCategory.valueOf(categoryStr);
//        product.setCategory(productCategory);
        product.setCategory(ProductCategory.valueOf(productDto.getCategory())); // Assuming valid enum value
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
    }
}
