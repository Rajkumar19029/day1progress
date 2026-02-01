package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(int productId);
    List<Product> findAllByWarehouse_WarehouseId(int warehouseId);
    void deleteByWarehouseId(int warehouseId);
    void deleteBySupplierId(int supplierId);
    int countByWarehouse_WarehouseId(Integer warehouseId);
}
