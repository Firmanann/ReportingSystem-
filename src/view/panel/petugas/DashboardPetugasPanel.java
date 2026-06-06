package view.panel.petugas;

import controller.KeluhanController;
import utils.UserSession;

import javax.swing.*;
import java.awt.*;

//Connected to Controller - Model 
public class DashboardPetugasPanel extends JPanel {

    public DashboardPetugasPanel() {
        // Setup dasar Panel (Putih bersih)
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // 1. Bagian Header (Judul Halaman)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel lblWelcome = new JLabel("Dashboard Petugas");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 28));
        lblWelcome.setForeground(Color.BLACK);
        
        JLabel lblSub = new JLabel("Ringkasan status keluhan yang telah Anda laporkan.");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSub.setForeground(Color.DARK_GRAY);

        headerPanel.add(lblWelcome, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.CENTER);

        // 2. Bagian Konten (Kartu Statistik dengan GridLayout)
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        //Logic Dashboard
        KeluhanController keluhan = new KeluhanController();
        UserSession user = new UserSession();
        
        int total = keluhan.getTotalStatus();
        int diproses = keluhan.getDiprosesStatus();
        int selesai = keluhan.getSelesaiStatus();

        // Show data results
        cardsPanel.add(createStatCard("Total Keluhan", String.valueOf(total)));
        cardsPanel.add(createStatCard("Sedang Diproses", String.valueOf(diproses)));
        cardsPanel.add(createStatCard("Selesai", String.valueOf(selesai)));

        // Memasukkan header dan kartu ke dalam Panel utama
        add(headerPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
    }

    // Method pembantu untuk membuat kotak desain bergaris tepi hitam
    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        
        // Membuat border hitam tebal (2px)
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 48));
        lblValue.setForeground(Color.BLACK);
        lblValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValue.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        card.add(lblTitle);
        card.add(lblValue);

        return card;
    }
}