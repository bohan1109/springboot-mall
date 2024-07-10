package com.hank.springbootmall.repository.specification;


import com.hank.springbootmall.dto.ProductQueryParams;
import com.hank.springbootmall.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecifications {
    public static Specification<Product> withDynamicQuery(ProductQueryParams productQueryParams) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productQueryParams.getProductName() != null && !productQueryParams.getProductName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + productQueryParams.getProductName() + "%"));
            }

            if (productQueryParams.getCategory() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), productQueryParams.getCategory()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
