package com.kavindu.fabrikza.product.services;

import com.kavindu.fabrikza.product.dtos.Request.AddProductDto;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class AddProductService {

    private final ProductRepository productRepository;
    private final ProductColorRepository productColorRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    public AddProductService(ProductRepository productRepository, ProductColorRepository productColorRepository, ProductSizeRepository productSizeRepository, ProductImageRepository productImageRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productColorRepository = productColorRepository;
        this.productSizeRepository = productSizeRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addProduct(AddProductDto addProductDto) {


    }
}
