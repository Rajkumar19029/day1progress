package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;

@Service
public class SupplierServiceImplJpa implements SupplierService{

    @Autowired
    SupplierRepository supplierRepository;
    

    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        Supplier saved=supplierRepository.save(supplier);
        return saved.getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> suppliers=supplierRepository.findAll();
        Collections.sort(suppliers,Comparator.comparing(Supplier::getSupplierName));
        return suppliers;
    }

    public void updateSupplier(Integer id,Supplier supplier) throws SQLException{
        Optional<Supplier> supplierOptional=supplierRepository.findById(id);
        if(supplierOptional.isPresent()){
            Supplier dbSupplier=supplierOptional.get();
            dbSupplier.setAddress(supplier.getAddress());
            dbSupplier.setEmail(supplier.getEmail());
            dbSupplier.setPassword(supplier.getPassword());
            dbSupplier.setPhone(supplier.getPhone());
            dbSupplier.setRole(supplier.getRole());
            dbSupplier.setSupplierName(supplier.getSupplierName());
            dbSupplier.setUsername(supplier.getUsername());
            supplierRepository.save(dbSupplier);
        }
        
    }
    @Transactional
    public void deleteSupplier(int supplierId)throws SQLException{
        if(supplierRepository.existsById(supplierId)){
            supplierRepository.deleteById(supplierId);
        }
    }
    public Supplier getSupplierById(int supplierId)throws SQLException{
        return supplierRepository.findById(supplierId).get();
    }
    
}
