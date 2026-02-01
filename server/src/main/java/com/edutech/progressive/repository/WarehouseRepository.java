package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findByWarehouseId(@Param("warehouseId") int warehouseId);

    List<Warehouse> findAllBySupplier_SupplierId(@Param("supplierId") int supplierId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Warehouse w WHERE w.supplier.supplierId = :supplierId")
    void deleteBySupplierId(@Param("supplierId") int supplierId);
}
