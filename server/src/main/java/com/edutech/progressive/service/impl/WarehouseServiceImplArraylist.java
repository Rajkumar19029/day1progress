package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;
@Service
public class WarehouseServiceImplArraylist implements WarehouseService {
    List<Warehouse> warehouseList=new ArrayList<>();
    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseList;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) {
        warehouseList.add(warehouse);
        return warehouseList.size();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() {
        List<Warehouse> sortWarehouses=warehouseList;
        sortWarehouses.sort(Comparator.comparing(Warehouse::getCapacity));
        return sortWarehouses;
    }
    @Override
    public void emptyArrayList(){
        warehouseList.clear();
    }

}