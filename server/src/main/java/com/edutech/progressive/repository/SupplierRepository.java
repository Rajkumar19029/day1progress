package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    void deleteBySupplierId(int supplierId);
    Supplier findBySupplierId(int supplierId);
    Supplier findByUsername(String username);
    Supplier findByEmail(String email);
}
