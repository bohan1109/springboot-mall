package com.hank.springbootmalll.controller;

import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.model.Product;
import com.hank.springbootmalll.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {

        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto);
    }


}
