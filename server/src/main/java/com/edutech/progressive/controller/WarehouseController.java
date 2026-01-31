package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.impl.WarehouseServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
// @RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseServiceImplJpa warehouseServiceImplJpa;

    // @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() throws SQLException {
        return null;
        //return new ResponseEntity<>(warehouseServiceImplJpa.getAllWarehouses(),HttpStatus.OK);
    }

    // @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId) {
        return null;
       // return new ResponseEntity<>(warehouseServiceImplJpa.getWarehouseById(warehouseId),HttpStatus.OK);
    }

    // @PostMapping
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) throws SQLException {
        return null;
       // return new ResponseEntity<>(warehouseServiceImplJpa.addWarehouse(warehouse),HttpStatus.CREATED);
    }

    // @PutMapping("/{warehouseId}")
    public ResponseEntity<String> updateWarehouse(@PathVariable int warehouseId,@RequestBody Warehouse warehouse) {
        return null;
       // return new ResponseEntity<>("Warehouse updated successfully",HttpStatus.OK);
    }

    // @DeleteMapping("/{warehouseId}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable int warehouseId) {
        return null;
        //return new ResponseEntity<>("Warehouse deleted successfully",HttpStatus.NO_CONTENT);
    }

    // @GetMapping("/{supplierId}")
    public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(@PathVariable int supplierId) {
        return null;
        //return new ResponseEntity<>(warehouseServiceImplJpa.getWarehouseBySupplier(supplierId),HttpStatus.OK);
    }
}
