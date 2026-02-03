package com.edutech.progressive.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.edutech.progressive.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer>{
    void deleteBySupplierId(@Param("supplierId") int supplierId);
    Supplier findBySupplierId(@Param("supplierId") int supplierId);
}
