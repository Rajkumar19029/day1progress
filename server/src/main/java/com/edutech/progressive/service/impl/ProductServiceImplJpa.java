package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.exception.InsufficientCapacityException;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.ProductService;
@Service
public class ProductServiceImplJpa implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    // used by unit tests
    public ProductServiceImplJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        return productRepository.findByProductId(productId);
    }

    @Override
    public int addProduct(Product product) {
        int warehouseId = product.getWarehouse().getWarehouseId();

        Warehouse warehouse = (warehouseRepository != null)
                ? warehouseRepository.findByWarehouseId(warehouseId)
                : product.getWarehouse();

        int productCount = productRepository.countByWarehouse_WarehouseId(warehouseId);

        if (warehouse.getCapacity() <= productCount) {
            throw new InsufficientCapacityException("Warehouse full");
        }

        return productRepository.save(product).getProductId();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProductByWarehouse(int warehouseId) throws SQLException {
        return productRepository.findAllByWarehouse_WarehouseId(warehouseId);
    }
}