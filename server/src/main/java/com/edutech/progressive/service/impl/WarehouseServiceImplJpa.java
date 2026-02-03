package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.WarehouseService;
@Service
public class WarehouseServiceImplJpa implements WarehouseService  {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        return null;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        return -1;
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        return null;
    }

    public void updateWarehouse(Warehouse warehouse)throws SQLException {

    }

    public void deleteWarehouse(int warehouseId) throws SQLException{
    }

    public Warehouse getWarehouseById(int warehouseId)throws SQLException {
        return null;
    }

    
}
