package com.example.demo.controller;

import com.example.demo.exceptionhandling.DataBaseException;
import com.example.demo.exceptionhandling.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/products")

public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() throws DataBaseException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity<>("Product \"" + productDto.getTitle() + "\" has been added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>("Product with id \"" + id + "\" has been updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) throws DataBaseException, ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id \"" + id + "\" has been deleted successfully!", HttpStatus.OK);
    }
}
