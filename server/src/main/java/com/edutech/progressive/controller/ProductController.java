package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.service.impl.ProductServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImplJpa productServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws SQLException {
        return new ResponseEntity<>(productServiceImplJpa.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        return null;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable int productId,@RequestBody Product product) {
        return null;
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        return null;
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Product>> getAllProductByWarehouse(int warehouseId) {
        return null;
    }
}
