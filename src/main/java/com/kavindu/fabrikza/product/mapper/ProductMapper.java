package com.kavindu.fabrikza.product.mapper;

import com.kavindu.fabrikza.product.dtos.Request.ProductImageDTO;
import com.kavindu.fabrikza.product.dtos.Response.ProductColorResponseDTO;
import com.kavindu.fabrikza.product.dtos.Response.ProductListResponseDTO;
import com.kavindu.fabrikza.product.dtos.Response.ProductResponseDTO;
import com.kavindu.fabrikza.product.dtos.Response.ProductVariantResponseDTO;
import com.kavindu.fabrikza.product.models.Color;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.models.ProductImage;
import com.kavindu.fabrikza.product.models.ProductVariants;
import com.kavindu.fabrikza.product.repositories.ProductImageRepository;
import com.kavindu.fabrikza.product.repositories.ProductRepository;
import com.kavindu.fabrikza.product.repositories.ProductVariantsRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final ProductImageRepository productImageRepository;
    private final ProductVariantsRepository productVariantsRepository;
    private final ProductRepository productRepository;

    public ProductMapper(ProductImageRepository productImageRepository, ProductVariantsRepository productVariantsRepository, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productVariantsRepository = productVariantsRepository;
        this.productRepository = productRepository;
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setManufacturer(product.getManufacturer());
        dto.setCategoryName(product.getCategory().getName());
        dto.setCreatedAt(product.getCreated_at());
        dto.setUpdatedAt(product.getUpdated_at());

        // Group by colors
        Map<Color, List<ProductImage>> imagesByColor = productImageRepository.findByProduct(product)
                .stream()
                .collect(Collectors.groupingBy(ProductImage::getColor));

        Map<Color, List<ProductVariants>> variantsByColor = productVariantsRepository.findByProduct(product)
                .stream()
                .collect(Collectors.groupingBy(ProductVariants::getColor));

        List<ProductColorResponseDTO> colors = new ArrayList<>();
        Set<Color> allColors = new HashSet<>();
        allColors.addAll(imagesByColor.keySet());
        allColors.addAll(variantsByColor.keySet());

        for (Color color : allColors) {
            ProductColorResponseDTO colorDto = new ProductColorResponseDTO();
            colorDto.setColorId(color.getId());
            colorDto.setColorName(color.getName());
            colorDto.setHexCode(color.getHexCode());

            List<ProductImageDTO> images = imagesByColor.getOrDefault(color, new ArrayList<>())
                    .stream()
                    .map(img -> new ProductImageDTO(img.getUrl()))
                    .collect(Collectors.toList());
            colorDto.setImages(images);

            List<ProductVariantResponseDTO> variants = variantsByColor.getOrDefault(color, new ArrayList<>())
                    .stream()
                    .map(variant -> new ProductVariantResponseDTO(
                            variant.getId(),
                            variant.getSize().getLabel(),
                            variant.getQuantity()
                    ))
                    .collect(Collectors.toList());
            colorDto.setVariants(variants);

            colors.add(colorDto);
        }

        dto.setColors(colors);
        return dto;
    }


    public ProductListResponseDTO toNewDto(Product product){
        ProductListResponseDTO dto = new ProductListResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory().getName());

        List<ProductImage> images=productImageRepository.findByProduct(product);
        if(!images.isEmpty()){
            dto.setThumbnail(images.get(0).getUrl());
        }

        return dto;
    }
}
