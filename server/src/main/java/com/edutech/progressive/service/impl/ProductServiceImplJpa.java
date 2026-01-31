package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.service.ProductService;

@Service
public class ProductServiceImplJpa implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        return productRepository.findById(productId).orElseThrow();
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        Product oldProduct=getProductById(product.getProductId());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setProductDescription(product.getProductDescription());
        oldProduct.setProductName(product.getProductName());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setWarehouseId(product.getWarehouseId());
        productRepository.save(oldProduct);
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        productRepository.deleteById(productId);
    }

    public List<Product> getAllProductByWarehouse(int warehouseId){
        return null;
    }

}