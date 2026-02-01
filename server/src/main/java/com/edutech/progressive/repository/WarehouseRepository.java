package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer>{
    Warehouse findByWarehouseId(int warehouseId);
    List<Warehouse> findAllBySupplier_SupplierId(int supplierId);
    void deleteBySupplierId(int supplierId);
    
}
