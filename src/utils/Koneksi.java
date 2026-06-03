package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    
        private static Connection Koneksi;
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/ReportingSystem";
            String user = "root";
            String pass = "manman";
            
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi Berhasil");
            return conn;
            
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
            
        }
    }
}
