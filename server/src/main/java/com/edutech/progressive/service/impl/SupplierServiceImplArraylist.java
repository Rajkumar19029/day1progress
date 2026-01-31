package com.edutech.progressive.service.impl;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;


import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;
@Component
public class SupplierServiceImplArraylist implements SupplierService {
    private static List<Supplier> supplierList=new ArrayList<>();
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierList;
    }

    @Override
    public int addSupplier(Supplier supplier) {
        supplierList.add(supplier);
        return supplierList.size();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        List<Supplier> sortedSupplier=supplierList;
        sortedSupplier.sort(Comparator.comparing(Supplier::getSupplierName));
        return sortedSupplier;
    }

    public void emptyArrayList(){
        supplierList.clear();
    }

}