package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi_mhs {
    private static Connection koneksi_mhs;
    
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_sipekan";
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
