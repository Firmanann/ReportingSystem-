package view.panel.petugas;

import controller.KeluhanController;
import javax.swing.*;
import java.awt.*;

public class DashboardPetugasPanel extends JPanel {

    // 1. Variabel Global untuk Label Angka biar bisa diubah-ubah nilainya
    private JLabel lblTotal;
    private JLabel lblDiproses;
    private JLabel lblSelesai;
    
    private KeluhanController keluhanController;

    public DashboardPetugasPanel() {
        this.keluhanController = new KeluhanController();

        // Inisialisasi Label Angka awal
        lblTotal = createValueLabel();
        lblDiproses = createValueLabel();
        lblSelesai = createValueLabel();

        // Setup dasar Panel (Putih bersih)
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // 2. Bagian Header (Judul Halaman)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel lblWelcome = new JLabel("Dashboard Petugas");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 28));
        lblWelcome.setForeground(Color.BLACK);
        
        JLabel lblSub = new JLabel("Ringkasan seluruh keluhan yang masuk ke dalam sistem.");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSub.setForeground(Color.DARK_GRAY);

        headerPanel.add(lblWelcome, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.CENTER);

        // 3. Bagian Konten (Kartu Statistik)
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Masukkan label angka ke dalam kotak desain
        cardsPanel.add(createStatCard("Total Keluhan", lblTotal));
        cardsPanel.add(createStatCard("Sedang Diproses", lblDiproses));
        cardsPanel.add(createStatCard("Selesai", lblSelesai));

        add(headerPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        // 4. Panggil loadData() pertama kali saat panel dibuka
        loadData();
    }

    // --- INI FUNGSI YANG DICARI SAMA MainPetugasFrame ---
    public void loadData() {
        // Ambil data dari database lewat controller (tanpa parameter karena ini petugas)
        int total = keluhanController.getTotalStatus(); 
        int diproses = keluhanController.getDiprosesStatus();
        int selesai = keluhanController.getSelesaiStatus();

        // Timpa angka di layar UI dengan data dari database
        lblTotal.setText(String.valueOf(total));
        lblDiproses.setText(String.valueOf(diproses));
        lblSelesai.setText(String.valueOf(selesai));
    }

    // Method pembantu untuk membuat JLabel nilai
    private JLabel createValueLabel() {
        JLabel lbl = new JLabel("0");
        lbl.setFont(new Font("Arial", Font.BOLD, 48));
        lbl.setForeground(Color.BLACK);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return lbl;
    }

    // Method pembantu untuk membuat kotak desain monokrom bergaris tepi
    private JPanel createStatCard(String title, JLabel lblValue) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitle);
        card.add(lblValue); 

        return card;
    }
}