package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
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
    public ResponseEntity<?> getSupplierById(@PathVariable int supplierId) throws SQLException  {
        try{
            return new ResponseEntity<>(supplierServiceImplJpa.getSupplierById(supplierId),HttpStatus.OK);
        } catch (SupplierDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addSupplier(Supplier supplier) throws SQLException {
        try {
            return new ResponseEntity<>(supplierServiceImplJpa.addSupplier(supplier),HttpStatus.CREATED);
        } catch (SupplierAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<?> updateSupplier(@PathVariable int supplierId,@RequestBody Supplier supplier) {
        try {
            supplier.setSupplierId(supplierId);
            supplierServiceImplJpa.updateSupplier(supplier);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SupplierAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    @DeleteMapping("/{supplier_id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        try {
            supplierServiceImplJpa.deleteSupplier(supplierId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
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