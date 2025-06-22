package com.kavindu.fabrikza.product.services;

import com.kavindu.fabrikza.product.dtos.Request.*;
import com.kavindu.fabrikza.product.dtos.Response.ProductResponseDTO;
import com.kavindu.fabrikza.product.mapper.ProductMapper;
import com.kavindu.fabrikza.product.models.*;
import com.kavindu.fabrikza.product.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductColorRepository productColorRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantsRepository productVariantsRepository;
    private final ProductMapper productMapper;

    public AdminProductService(ProductRepository productRepository, ProductColorRepository productColorRepository, ProductSizeRepository productSizeRepository, ProductImageRepository productImageRepository, CategoryRepository categoryRepository, ProductVariantsRepository productVariantsRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productColorRepository = productColorRepository;
        this.productSizeRepository = productSizeRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
        this.productVariantsRepository = productVariantsRepository;
        this.productMapper = productMapper;
    }

    public void addProduct(AddProductDto addProductDto) {

        Category category = categoryRepository.findById(addProductDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Optional<Product> existing = productRepository
                .findByNameAndCategory(addProductDto.getName(), category);

        if (existing.isPresent()) {
            throw new RuntimeException("Duplicate product: same name already exists in this category.");
        }

        Product product = new Product();
        product.setName(addProductDto.getName());
        product.setDescription(addProductDto.getDescription());
        product.setManufacturer(addProductDto.getManufacturer());
        product.setPrice(addProductDto.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        for (ProductColorDTO colorDTO : addProductDto.getColors()) {
            // Fetch color by ID directly
            Color color = productColorRepository.findById(colorDTO.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));

            for (ProductImageDTO imageDto : colorDTO.getImages()) {
                ProductImage productImage = new ProductImage();
                productImage.setUrl(imageDto.getUrl());
                productImage.setProduct(savedProduct);
                productImage.setColor(color);
                productImageRepository.save(productImage);
            }

            for (ProductVariantDTO variantDTO : colorDTO.getVariants()) {
                // Fetch size by ID directly
                Size size = productSizeRepository.findById(variantDTO.getSizeId())
                        .orElseThrow(() -> new RuntimeException("Size not found"));

                ProductVariants variant = new ProductVariants();
                variant.setProduct(savedProduct);
                variant.setColor(color);
                variant.setSize(size);
                variant.setQuantity(variantDTO.getQuantity());
                productVariantsRepository.save(variant);
            }
        }
    }

    public ProductResponseDTO updateProduct(UUID id, UpdateProductDto updateDto) {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));

        if (updateDto.getName() != null) {
            product.setName(updateDto.getName());
        }
        if (updateDto.getDescription() != null) {
            product.setDescription(updateDto.getDescription());
        }
        if (updateDto.getPrice() != null) {
            product.setPrice(updateDto.getPrice());
        }
        if (updateDto.getManufacturer() != null) {
            product.setManufacturer(updateDto.getManufacturer());
        }
        if (updateDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }
}
