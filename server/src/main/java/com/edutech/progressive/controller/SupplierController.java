package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.impl.SupplierServiceImplJdbc;
import com.edutech.progressive.service.impl.SupplierServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierServiceImplJpa supplierServiceImplJpa;
    @Autowired
    SupplierServiceImplJdbc supplierServiceImplJdbc;
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJpa.getAllSuppliers(),HttpStatus.OK);
    }
    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJpa.getSupplierById(supplierId),HttpStatus.OK);
    }
     @PostMapping
    public ResponseEntity<Integer> addSupplier(@RequestBody Supplier supplier) throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJdbc.addSupplier(supplier),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSupplier(@PathVariable Integer id,@RequestBody Supplier supplier) throws SQLException {
        supplierServiceImplJpa.updateSupplier(id,supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSupplier(int supplierId) throws SQLException {
        supplierServiceImplJpa.deleteSupplier(supplierId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList() throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJdbc.getAllSuppliersSortedByName(),HttpStatus.OK);
    }

    public ResponseEntity<Integer> addSupplierToArrayList(@RequestBody Supplier supplier) throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJdbc.addSupplier(supplier),HttpStatus.CREATED);
    }

    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() {
        return null;
    }
}