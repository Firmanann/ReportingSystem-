package view.frame;

import javax.swing.*;
import java.awt.*;

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

        // ==========================================
        // 1. SIDEBAR KIRI (Menu Navigasi Petugas)
        // ==========================================
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

        // Tombol Menu
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

        // ==========================================
        // 2. MAIN CONTENT TENGAH (Wadah Panel)
        // ==========================================
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);

        // Dummy Panel sementara sebelum class Panel asli dibuat
        mainContentPanel.add(createDummyPanel("Dashboard Petugas"), "Dashboard");
        mainContentPanel.add(createDummyPanel("Manajemen Data Keluhan"), "DataKeluhan");
        mainContentPanel.add(createDummyPanel("Manajemen Kategori"), "Kategori");

        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        // ==========================================
        // 3. LOGIKA PINDAH HALAMAN
        // ==========================================
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

    // Method pembantu dummy panel
    private JPanel createDummyPanel(String text) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPetugasFrame().setVisible(true);
        });
    }
}