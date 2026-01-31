package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO {
    private Connection connection;
    public WarehouseDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "Insert into warehouse(supplier_id,warehouse_name,location,capacity) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, warehouse.getSupplierId());
        ps.setString(2, warehouse.getWarehouseName());
        ps.setString(3, warehouse.getLocation());
        ps.setInt(4, warehouse.getCapacity());
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        int generatedId=-1;
        if(rs.next()){
            generatedId=rs.getInt(1);
            warehouse.setWarehouseId(generatedId);
        }
        return generatedId;
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        Warehouse warehouse = null;
        String sql = "Select * from warehouse where warehouse_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouseId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            warehouse = new Warehouse(warehouseId, rs.getInt("supplier_id"), rs.getString("warehouse_name"),
                    rs.getString("location"), rs.getInt("capacity"));
        }
        return warehouse;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "Update warehouse set supplier_id=?,warehouse_name=?,location=?,capacity=? where warehouse_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouse.getSupplierId());
        ps.setString(2, warehouse.getWarehouseName());
        ps.setString(3, warehouse.getLocation());
        ps.setInt(4, warehouse.getCapacity());
        ps.setInt(5, warehouse.getWarehouseId());
        ps.executeUpdate();
    }

    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException {
        String sql = "Delete from warehouse where warehouse_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouseId);
        ps.executeUpdate();
    }

    @Override
    public List<Warehouse> getAllWarehouse() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "Select * from warehouse order by capacity";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            warehouses.add(new Warehouse(rs.getInt("warehouse_id"), rs.getInt("supplier_id"),
                    rs.getString("warehouse_name"), rs.getString("location"), rs.getInt("capacity")));
        }
        return warehouses;
    }
    @Override
    public List<Warehouse> getAllWarehouseSortedByBalance() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "Select * from warehouse order by balance";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            warehouses.add(new Warehouse(rs.getInt("warehouse_id"), rs.getInt("supplier_id"),
                    rs.getString("warehouse_name"), rs.getString("location"), rs.getInt("capacity")));
        }
        return warehouses;
    }

}
