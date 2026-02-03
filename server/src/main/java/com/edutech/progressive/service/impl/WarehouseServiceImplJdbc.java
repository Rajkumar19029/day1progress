package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.dao.WarehouseDAO;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;
@Service
public class WarehouseServiceImplJdbc implements WarehouseService {
    private WarehouseDAO warehouseDAO;
    
    public WarehouseServiceImplJdbc(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }
    public WarehouseServiceImplJdbc(){
        
    }
    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        return warehouseDAO.getAllWarehouse();
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        return warehouseDAO.addWarehouse(warehouse);
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        List<Warehouse> sortedWarehouse=warehouseDAO.getAllWarehouse();
        if(sortedWarehouse!=null){
            sortedWarehouse.sort(Comparator.comparing(Warehouse::getCapacity));
        }
        return sortedWarehouse;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException{
        warehouseDAO.updateWarehouse(warehouse);
    }

    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException{
        warehouseDAO.deleteWarehouse(warehouseId);
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException{
        return warehouseDAO.getWarehouseById(warehouseId);
    }

}