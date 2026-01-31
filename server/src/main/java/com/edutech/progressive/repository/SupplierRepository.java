package com.edutech.progressive.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer>{
    @Transactional
    @Query("Delete from Supplier s where s.supplier_id=:supplierId")
    void deleteBySupplierId(@Param("supplierId") int supplierId);

    @Query("Select s from Supplier s where s.supplier_id=:supplierId")
    Supplier findBySupplierId(@Param("supplierId") int supplierId);
}
