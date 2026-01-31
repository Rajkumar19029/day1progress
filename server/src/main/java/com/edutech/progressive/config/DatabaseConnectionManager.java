package com.edutech.progressive.config;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static Properties properties=new Properties();
    static{
        loadProperties();
    }
    public static void loadProperties(){
        try(InputStream input=DatabaseConnectionManager.class .getClassLoader().getResourceAsStream("application.properties")) {
            if(input==null){
                throw new RuntimeException("applications.properties file not found");
            }
            properties.load(input);
         
        }catch(Exception e){
            throw new RuntimeException("Failed to load database",e);
        }
    }
    public static Connection getConnection() throws SQLException{
        try{
        String url=properties.getProperty("spring.datasource.url");
        String username=properties.getProperty("spring.datasource.username");
        String password=properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, username, password);
    }catch(Exception e){
        throw new RuntimeException("Failed to establish database connection",e);
    }
}
}
