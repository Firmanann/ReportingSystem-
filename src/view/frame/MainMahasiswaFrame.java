package view.frame;

import view.panel.mahasiswa.DashboardMahasiswaPanel;
import view.panel.mahasiswa.FormKeluhanPanel;
import view.panel.mahasiswa.RiwayatKeluhanPanel;
import javax.swing.*;
import java.awt.*;
import utils.UserSession;

public class MainMahasiswaFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    private RiwayatKeluhanPanel panelRiwayat;
    private DashboardMahasiswaPanel panelDashboard;

    public MainMahasiswaFrame() {
        
        // Setup Dasar Frame
        setTitle("Reporting System - Mahasiswa");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Design left Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.BLACK);
        sidebarPanel.setPreferredSize(new Dimension(220, 0));

        //Title Frame
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

        //komponen Sidebar
        sidebarPanel.add(lblMenu);
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnBuatKeluhan);
        sidebarPanel.add(btnRiwayat);
        sidebarPanel.add(Box.createVerticalGlue()); 
        sidebarPanel.add(btnLogout);
        sidebarPanel.add(Box.createVerticalStrut(20)); 

        //Main content 
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);
        
        // 1. Inisialisasi panel
        panelDashboard = new DashboardMahasiswaPanel();
        panelRiwayat = new RiwayatKeluhanPanel(UserSession.getUser());

        // 2. Masukkan panel pakai variabel 
        mainContentPanel.add(panelDashboard, "Dashboard");    
        mainContentPanel.add(new FormKeluhanPanel(), "BuatKeluhan"); 
        mainContentPanel.add(panelRiwayat, "Riwayat");

        // sidebar dan main content pada frame utama 
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        //Panel Dashboard
        btnDashboard.addActionListener(e -> {
            panelDashboard.loadData(); // Tarik data terbaru buat angka total
            cardLayout.show(mainContentPanel, "Dashboard");
        });

        //Panel Form keluhan
        btnBuatKeluhan.addActionListener(e -> cardLayout.show(mainContentPanel, "BuatKeluhan"));

        //Panel riwayat keluhan
        btnRiwayat.addActionListener(e -> {
            panelRiwayat.loadData(); // Tarik data terbaru buat tabel
            cardLayout.show(mainContentPanel, "Riwayat");
        });

        //Panel Logout
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
}