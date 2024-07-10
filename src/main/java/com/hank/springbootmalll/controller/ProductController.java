package com.hank.springbootmalll.controller;

import com.hank.springbootmalll.constant.ProductCategory;
import com.hank.springbootmalll.dto.ProductDto;
import com.hank.springbootmalll.dto.ProductQueryParams;
import com.hank.springbootmalll.model.Product;
import com.hank.springbootmalll.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required=false) String productName,
            @RequestParam(required = false, defaultValue = "createdDate") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setProductName(productName);
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Product> productList= productService.getAllProducts(productQueryParams,pageable);
            return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto productDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
