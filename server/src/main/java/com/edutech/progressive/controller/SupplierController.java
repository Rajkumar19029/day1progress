package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.impl.SupplierServiceImplArraylist;
import com.edutech.progressive.service.impl.SupplierServiceImplJpa;

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
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServiceImplArraylist supplierServiceImplArraylist;

    @Autowired
    SupplierServiceImplJpa supplierServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJpa.getAllSuppliers(),HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) throws SQLException  {
        return new ResponseEntity<>(supplierServiceImplJpa.getSupplierById(supplierId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addSupplier(Supplier supplier) throws SQLException {
        return new ResponseEntity<>(supplierServiceImplJpa.addSupplier(supplier),HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<String> updateSupplier(@PathVariable int supplierId,@RequestBody Supplier supplier) {
        return new ResponseEntity<>("Supplier updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{supplier_id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable int supplierId) {
        return new ResponseEntity<>("Supplier deleted successfully",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList()  {
        return new ResponseEntity<>(supplierServiceImplArraylist.getAllSuppliers(),HttpStatus.OK);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addSupplierToArrayList(Supplier supplier)  {
        return new ResponseEntity<>(supplierServiceImplArraylist.addSupplier(supplier),HttpStatus.CREATED);
    }

    @GetMapping("/fromArrayList/all")
    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() {
        return new ResponseEntity<>(supplierServiceImplArraylist.getAllSuppliersSortedByName(),HttpStatus.OK);
    }
}