package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Supplier;

import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;

@Service
public class SupplierServiceImplJpa implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        supplierRepository.save(supplier);
        return supplier.getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> suppliers = supplierRepository.findAll();
        Collections.sort(suppliers);
        return suppliers;
    }

    public void updateSupplier(Supplier supplier) throws SQLException {
        Supplier s = getSupplierById(supplier.getSupplierId());
        s.setAddress(supplier.getAddress());
        s.setEmail(supplier.getEmail());
        s.setPassword(supplier.getPassword());
        s.setPhone(supplier.getPhone());
        s.setRole(supplier.getRole());
        s.setSupplierName(supplier.getSupplierName());
        s.setUsername(supplier.getUsername());
        supplierRepository.save(s);
    }

    public void deleteSupplier(int supplierId) {
        supplierRepository.deleteBySupplierId(supplierId);
    }

    public Supplier getSupplierById(int supplierId) throws SQLException {
        Supplier supplier=supplierRepository.findBySupplierId(supplierId);
        return supplier;
    }

}