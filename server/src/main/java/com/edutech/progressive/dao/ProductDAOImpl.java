package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Product;

public class ProductDAOImpl implements ProductDAO{
    private Connection connection;
    public ProductDAOImpl(){
       try {
        this.connection=DatabaseConnectionManager.getConnection();
       } catch (SQLException e) {
        e.printStackTrace();
       }
    }
    @Override
    public int addProduct(Product product) throws SQLException {
        String sql="Insert into product(warehouse_id,product_name,product_description,quantity,price) values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, product.getWarehouseId());
        ps.setString(2, product.getProductName());
        ps.setString(3, product.getProductDescription());
        ps.setInt(4, product.getQuantity());
        ps.setLong(5, product.getPrice());
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        int generatedId=0;
        if(rs.next()){
            generatedId=rs.getInt(1);
            product.setProductId(generatedId);
        }
        return generatedId;
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        Product product=null;
        String sql="select * from product where product_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,productId);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            product=new Product(rs.getInt("product_id"), rs.getInt("warehouse_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getInt("quantity"),rs.getLong("price"));
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql="Update product set warehouse_id=?,product_name=?,product_description=?,quantity=?,price=? where product_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, product.getWarehouseId());
        ps.setString(2, product.getProductName());
        ps.setString(3, product.getProductDescription());
        ps.setInt(4, product.getQuantity());
        ps.setLong(5, product.getPrice());
        ps.setInt(6, product.getProductId());
        ps.executeUpdate();
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql="Delete from product where product_id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, productId);
        ps.executeUpdate();
    }
    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products=new ArrayList<>();
        String sql="Select * from product";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            products.add(new Product(rs.getInt("product_id"), rs.getInt("warehouse_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getInt("quantity"),rs.getLong("price")));
        }
        return products;
    }

}
