package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    
        private static Connection Koneksi;
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/Reporting";
            String user = "root";
            String pass = "";
            
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
            
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
            
        }
    }
    
}
