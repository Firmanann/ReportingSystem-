package view.frame;

import javax.swing.*;
import java.awt.*;

public class MainMahasiswaFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContentPanel;

    public MainMahasiswaFrame() {
        // Setup Dasar Frame
        setTitle("Reporting System - Mahasiswa");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ==========================================
        // 1. SIDEBAR KIRI (Menu Navigasi)
        // ==========================================
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.BLACK);
        sidebarPanel.setPreferredSize(new Dimension(220, 0));

        // Judul Sidebar
        JLabel lblMenu = new JLabel("MENU MAHASISWA");
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 16));
        lblMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Tombol Menu
        JButton btnDashboard = createMenuButton("Dashboard");
        JButton btnBuatKeluhan = createMenuButton("Buat Keluhan");
        JButton btnRiwayat = createMenuButton("Riwayat Keluhan");
        JButton btnLogout = createMenuButton("Logout");

        // Memasukkan komponen ke Sidebar
        sidebarPanel.add(lblMenu);
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnBuatKeluhan);
        sidebarPanel.add(btnRiwayat);
        sidebarPanel.add(Box.createVerticalGlue()); // Pendorong tombol logout ke paling bawah
        sidebarPanel.add(btnLogout);
        sidebarPanel.add(Box.createVerticalStrut(20)); // Jarak bawah

        // ==========================================
        // 2. MAIN CONTENT TENGAH (Wadah Panel)
        // ==========================================
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);

        // TODO: Nanti ganti createDummyPanel dengan class Panel lu yang asli
        // Contoh nanti: mainContentPanel.add(new DashboardMahasiswaPanel(), "Dashboard");
        mainContentPanel.add(createDummyPanel("Ini Halaman Dashboard"), "Dashboard");
        mainContentPanel.add(createDummyPanel("Ini Halaman Form Keluhan"), "BuatKeluhan");
        mainContentPanel.add(createDummyPanel("Ini Halaman Riwayat Keluhan"), "Riwayat");

        // Memasukkan Sidebar dan Main Content ke Frame Utama
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        // ==========================================
        // 3. LOGIKA PINDAH HALAMAN (Event Listener)
        // ==========================================
        btnDashboard.addActionListener(e -> cardLayout.show(mainContentPanel, "Dashboard"));
        btnBuatKeluhan.addActionListener(e -> cardLayout.show(mainContentPanel, "BuatKeluhan"));
        btnRiwayat.addActionListener(e -> cardLayout.show(mainContentPanel, "Riwayat"));
        
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true);
                this.dispose();
            }
        });
    }

    // Method pembantu untuk membuat desain tombol seragam
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

    // Method pembantu sementara (Dummy Panel) sebelum lu buat class Panel aslinya
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
            new MainMahasiswaFrame().setVisible(true);
        });
    }
}