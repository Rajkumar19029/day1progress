package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Supplier;

public class SupplierDAOImpl implements SupplierDAO {
    private Connection connection;
    
    public SupplierDAOImpl(){
       try {
        this.connection=DatabaseConnectionManager.getConnection();
       } catch (SQLException e) {
        e.printStackTrace();
       }
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        String sql="Insert into supplier(supplier_name,email,phone,username,password,address,role) values(?,?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setString(3, supplier.getPhone());
        ps.setString(4, supplier.getUsername());
        ps.setString(5, supplier.getPassword());
        ps.setString(6, supplier.getAddress());
        ps.setString(7, supplier.getRole());
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        int generatedId=-1;
        if(rs.next()){
            generatedId=rs.getInt(1);
            supplier.setSupplierId(generatedId);
        }
        return generatedId;
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        Supplier supplier=null;
        String sql="select * from supplier where supplier_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, supplierId);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            supplier=new Supplier(supplierId, rs.getString("supplier_name"), rs.getString("email"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("role"));
        }
        return supplier;
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql="Update supplier set supplier_name=?,email=?,phone=?,username=?,password=?,address=?,role=? where supplier_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setString(3, supplier.getPhone());
        ps.setString(4, supplier.getUsername());
        ps.setString(5, supplier.getPassword());
        ps.setString(6, supplier.getAddress());
        ps.setString(7, supplier.getRole());
        ps.setInt(8, supplier.getSupplierId());
        ps.executeUpdate();
    }

    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        String sql="Delete from supplier where supplier_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, supplierId);
        ps.executeUpdate();
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers=new ArrayList<>();
        String sql="select * from supplier";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            suppliers.add(new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("email"),rs.getString("phone"), rs.getString("username"), rs.getString("password"),  rs.getString("address"), rs.getString("role")));
        }
        return suppliers;
    }



}
