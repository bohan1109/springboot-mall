package com.hank.springbootmall.service.implement;

import com.hank.springbootmall.dto.ProductDto;
import com.hank.springbootmall.dto.ProductQueryParams;
import com.hank.springbootmall.model.Product;
import com.hank.springbootmall.repository.ProductRepository;
import com.hank.springbootmall.repository.specification.ProductSpecifications;
import com.hank.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(ProductQueryParams productQueryParams, Pageable pageable) {
        return productRepository.findAll(ProductSpecifications.withDynamicQuery(productQueryParams), pageable);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));

        updateEntityFromDto(product, productDto);
        product.setLastModifiedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private void updateEntityFromDto(Product product, ProductDto productDto) {
        product.setProductName(productDto.getProductName());
        product.setCategory(productDto.getCategory());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
    }
}
