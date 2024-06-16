package com.hank.springbootmalll.service.implement;

import com.hank.springbootmalll.model.Product;
import com.hank.springbootmalll.repository.ProductRepository;
import com.hank.springbootmalll.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
