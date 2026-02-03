package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.service.ProductService;
@Service
public class ProductServiceImplJpa implements ProductService {

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return null;
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        return null;
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        return -1;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        
    }

    public List<Product> getAllProductsByWarehouse(int productId){
        return null;
    }

    
}
