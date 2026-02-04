package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.exception.NoWarehouseFoundForSupplierException;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.WarehouseService;
@Service
public class WarehouseServiceImplJpa implements WarehouseService {
    @Autowired
    ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImplJpa(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        return warehouseRepository.findAll();
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        return warehouseRepository.save(warehouse).getWarehouseId();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        List<Warehouse> sortedWarehouses = warehouseRepository.findAll();
        Collections.sort(sortedWarehouses);
        return sortedWarehouses;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException {
        productRepository.deleteByWarehouseId(warehouseId);
        warehouseRepository.deleteById(warehouseId);
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        return warehouseRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Warehouse> getWarehouseBySupplier(int supplierId) throws NoWarehouseFoundForSupplierException {
        List<Warehouse> warehouseList = warehouseRepository.findAllBySupplier_SupplierId(supplierId);
        if(warehouseList.isEmpty()){
            throw new NoWarehouseFoundForSupplierException("Warehouse not found");
        }
        return warehouseList;
    }
}