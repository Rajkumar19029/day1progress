package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.exception.NoWarehouseFoundForSupplierException;
import com.edutech.progressive.service.impl.WarehouseServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseServiceImplJpa warehouseServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() throws SQLException {
        return new ResponseEntity<>(warehouseServiceImplJpa.getAllWarehouses(), HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId)  {
        try {
            return new ResponseEntity<>(warehouseServiceImplJpa.getWarehouseById(warehouseId), HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) {
        try {
            return new ResponseEntity<>(warehouseServiceImplJpa.addWarehouse(warehouse), HttpStatus.CREATED);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable int warehouseId, @RequestBody Warehouse warehouse) {
        warehouse.setWarehouseId(warehouseId);
        try {
            warehouseServiceImplJpa.updateWarehouse(warehouse);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int warehouseId) {
        
         try {
            warehouseServiceImplJpa.deleteWarehouse(warehouseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<?> getWarehousesBySupplier(@PathVariable int supplierId) {
        try {
            return new ResponseEntity<>(warehouseServiceImplJpa.getWarehouseBySupplier(supplierId), HttpStatus.OK);
        }catch(NoWarehouseFoundForSupplierException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}