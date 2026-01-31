package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.WarehouseService;

@Service
public class WarehouseServiceImplJpa implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        return null;
        // return warehouseRepository.findAll();
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        return -1;
        // warehouseRepository.save(warehouse);
        // return warehouse.getWarehouseId();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        return null;
        // List<Warehouse> warehouseList=warehouseRepository.findAll();
        // Collections.sort(warehouseList);
        // return warehouseList;
    }

    public void updateWarehouse(Warehouse warehouse){
        // Warehouse oldWarehouse=getWarehouseById(warehouse.getWarehouseId());
        // oldWarehouse.setCapacity(warehouse.getCapacity());
        // oldWarehouse.setLocation(warehouse.getLocation());
        // oldWarehouse.setSupplierId(warehouse.getSupplierId());
        // oldWarehouse.setWarehouseName(warehouse.getWarehouseName());
        // warehouseRepository.save(oldWarehouse);
    }

    public void deleteWarehouse(int warehouseId){
        // warehouseRepository.deleteById(warehouseId);
    }

    public Warehouse getWarehouseById(int warehouseId){
        return null;
        // return warehouseRepository.findById(warehouseId).get();
    }

    public List<Warehouse> getWarehouseBySupplier(int supplierId){
        return null;
    }

}