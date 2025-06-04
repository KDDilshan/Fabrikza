package com.kavindu.fabrikza.product.services;

import com.kavindu.fabrikza.product.dtos.Response.ProductListResponseDTO;
import com.kavindu.fabrikza.product.dtos.Response.ProductResponseDTO;
import com.kavindu.fabrikza.product.mapper.ProductMapper;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.repositories.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProductService {
    private final ProductRepository productRepository;
    private final ProductColorRepository productColorRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantsRepository productVariantsRepository;
    private final ProductMapper productMapper;

    public UserProductService(ProductRepository productRepository, ProductColorRepository productColorRepository, ProductSizeRepository productSizeRepository, ProductImageRepository productImageRepository, CategoryRepository categoryRepository, ProductVariantsRepository productVariantsRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productColorRepository = productColorRepository;
        this.productSizeRepository = productSizeRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
        this.productVariantsRepository = productVariantsRepository;
        this.productMapper = productMapper;
    }

    public List<ProductListResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toNewDto)
                .collect(Collectors.toList());
    }


    public List<ProductListResponseDTO> searchProduct(String query, String color, String size) {
        try{
            if (query != null || color != null || size != null) {
                List<Product> products =productRepository.searchByName(query);
                return products.stream()
                        .map(productMapper::toNewDto)
                        .collect(Collectors.toList());
            } else {
                return getAllProducts();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<ProductListResponseDTO> orderByPrice(String order) {
        try {
            Sort sort = order.equalsIgnoreCase("asc")
                    ? Sort.by(Sort.Order.asc("price"))
                    : Sort.by(Sort.Order.desc("price"));
            List<Product> products = productRepository.findAll(sort);
            return products.stream()
                    .map(productMapper::toNewDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<ProductListResponseDTO> OrderByName(String order) {
        try {
            Sort sort = order.equalsIgnoreCase("asc")
                    ? Sort.by(Sort.Order.asc("name"))
                    : Sort.by(Sort.Order.desc("name"));
            List<Product> products = productRepository.findAll(sort);
            return products.stream()
                    .map(productMapper::toNewDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
