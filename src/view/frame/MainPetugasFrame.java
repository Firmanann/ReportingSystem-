package view.frame;

import javax.swing.*;
import java.awt.*;
import view.panel.petugas.DashboardPetugasPanel;
import view.panel.petugas.DataKeluhanPanel;
import view.panel.petugas.TanggapanPanel;
import view.panel.petugas.KategoriPanel;

public class MainPetugasFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContentPanel;

    public MainPetugasFrame() {
        
        // Setup Dasar Frame
        setTitle("Reporting System - Petugas / Admin");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Navigation on leftsidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.BLACK);
        sidebarPanel.setPreferredSize(new Dimension(220, 0));

        // Judul Sidebar
        JLabel lblMenu = new JLabel("MENU PETUGAS");
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 16));
        lblMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Tombol Menu Utama (Cuma 3 Sesuai Permintaan)
        JButton btnDashboard = createMenuButton("Dashboard");
        JButton btnDataKeluhan = createMenuButton("Data Keluhan");
        JButton btnKategori = createMenuButton("Kelola Kategori");
        JButton btnLogout = createMenuButton("Logout");

        // Memasukkan komponen ke Sidebar
        sidebarPanel.add(lblMenu);
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnDataKeluhan);
        sidebarPanel.add(btnKategori);
        sidebarPanel.add(Box.createVerticalGlue()); // Dorong logout ke bawah
        sidebarPanel.add(btnLogout);
        sidebarPanel.add(Box.createVerticalStrut(20)); 

        // Main content
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);

        // Masukkan Panel Utama (Perhatikan parameter 'this' pada DataKeluhanPanel)
        mainContentPanel.add(new DashboardPetugasPanel(), "Dashboard");
        mainContentPanel.add(new DataKeluhanPanel(this), "DataKeluhan"); 
        mainContentPanel.add(new KategoriPanel(), "Kategori");

        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        // Next Page logic 
        btnDashboard.addActionListener(e -> cardLayout.show(mainContentPanel, "Dashboard"));
        btnDataKeluhan.addActionListener(e -> cardLayout.show(mainContentPanel, "DataKeluhan"));
        btnKategori.addActionListener(e -> cardLayout.show(mainContentPanel, "Kategori"));
        
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true); 
                this.dispose();
            }
        });
    }

    // Method KHUSUS untuk pindah ke Panel Tanggapan (Dipanggil dari DataKeluhanPanel)
    public void bukaPanelTanggapan(String idKeluhan) {

        
        // Buat panel tanggapan baru dengan ID spesifik
        // Nanti ganti createDummyPanel dengan 'new TanggapanPanel(idKeluhan)' kalau udah lu buat class-nya
        TanggapanPanel panelTanggapan = new TanggapanPanel(this, idKeluhan);        
        panelTanggapan.setName("Tanggapan"); // Beri nama untuk identifikasi

        mainContentPanel.add(panelTanggapan, "Tanggapan");
        cardLayout.show(mainContentPanel, "Tanggapan");
    }
    
    // Method untuk balik ke halaman Data Keluhan
    public void kembaliKeDataKeluhan() {
        cardLayout.show(mainContentPanel, "DataKeluhan");
    }

    // Method pembuat tombol biar desain seragam (Hitam Putih)
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}