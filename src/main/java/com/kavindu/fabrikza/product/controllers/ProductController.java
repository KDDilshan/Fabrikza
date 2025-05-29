package com.kavindu.fabrikza.product.controllers;

import com.kavindu.fabrikza.product.dtos.Request.AddProductDto;
import com.kavindu.fabrikza.product.services.AddProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    private final AddProductService addProductService;

    public ProductController(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    @PostMapping
    private ResponseEntity<String> addProduct(@RequestBody AddProductDto addProductDto){
        addProductService.addProduct(addProductDto);
        return ResponseEntity.ok("Product added");
    }
}
