package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.dao.SupplierDAO;
import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

public class SupplierServiceImplJdbc implements SupplierService {
    SupplierDAO supplierDAO;
    
    public SupplierServiceImplJdbc(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierDAO.getAllSuppliers();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        return supplierDAO.addSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> sortedSuppliers=supplierDAO.getAllSuppliers();
        if(sortedSuppliers!=null){
            sortedSuppliers.sort(Comparator.comparing(Supplier::getSupplierName));
        }
        return sortedSuppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException{
        supplierDAO.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(int supplier_id) throws SQLException{
        supplierDAO.deleteSupplier(supplier_id);
    }

    @Override
    public Supplier getSupplierById(int supplier_id) throws SQLException{
        return supplierDAO.getSupplierById(supplier_id);
    }

}