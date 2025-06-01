package com.kavindu.fabrikza.product.controllers;

import com.kavindu.fabrikza.product.dtos.Request.AddProductDto;
import com.kavindu.fabrikza.product.dtos.Request.UpdateProductDto;
import com.kavindu.fabrikza.product.dtos.Response.ProductResponseDTO;
import com.kavindu.fabrikza.product.services.AdminProductService;
import com.kavindu.fabrikza.product.services.UserProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    private final AdminProductService adminProductService;
    private final UserProductService userProductService;

    public ProductController(AdminProductService adminProductService, UserProductService userProductService) {
        this.adminProductService = adminProductService;
        this.userProductService = userProductService;
    }

    //Admin Controllers

    @PostMapping
    private ResponseEntity<String> addProduct(@RequestBody AddProductDto addProductDto){
        adminProductService.addProduct(addProductDto);
        return ResponseEntity.ok("Product added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable UUID id,
            @RequestBody UpdateProductDto updateProductDto){
        try{
            ProductResponseDTO productResponseDTO=adminProductService.updateProduct(id,updateProductDto);
            return ResponseEntity.ok(productResponseDTO);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        try {
            adminProductService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Users Controllers

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = userProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }


}
