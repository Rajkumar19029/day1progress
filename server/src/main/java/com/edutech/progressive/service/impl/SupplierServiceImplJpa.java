package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.SupplierService;

@Service
public class SupplierServiceImplJpa implements SupplierService{

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;

    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier)throws SupplierAlreadyExistsException {
        Supplier supplierUserName=supplierRepository.findByUserName(supplier.getUsername());
        Supplier supplierEmail=supplierRepository.findByEmail(supplier.getEmail());
        if(supplierUserName!=null||supplierEmail.getEmail()!=null){
            throw new SupplierAlreadyExistsException("Supplier already exists");
        }
        return supplierRepository.save(supplier).getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> suppliers=supplierRepository.findAll();
        Collections.sort(suppliers,Comparator.comparing(Supplier::getSupplierName));
        return suppliers;
    }

    public void updateSupplier(Supplier supplier) throws SQLException{
        Supplier supplierByUserName=supplierRepository.findByUserName(supplier.getUsername());
        if(supplierByUserName!=null && supplier.getSupplierId()!=supplierByUserName.getSupplierId()){
            throw new SupplierAlreadyExistsException("Supplier username should be unique");
        }
        supplierRepository.save(supplier);
        
    }
    @Transactional
    public void deleteSupplier(int supplierId)throws SQLException{
        if(supplierRepository.existsById(supplierId)){
            productRepository.deleteBySupplierId(supplierId);
            warehouseRepository.deleteBySupplierId(supplierId);
            supplierRepository.deleteById(supplierId);
        }
    }
    public Supplier getSupplierById(int supplierId)throws SupplierDoesNotExistException{
        Supplier supplier=supplierRepository.findBySupplierId(supplierId);
        if(supplier==null){
            throw new SupplierDoesNotExistException("Supplier not found");
        }
        return supplier;
    }
    
}
