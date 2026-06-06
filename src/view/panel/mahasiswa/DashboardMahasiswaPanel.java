package view.panel.mahasiswa;

import controller.KeluhanController;
import utils.UserSession;

import javax.swing.*;
import java.awt.*;

public class DashboardMahasiswaPanel extends JPanel {

    // 1. Jadikan JLabel sebagai variabel global biar bisa di-update berkali-kali
    private JLabel lblTotal;
    private JLabel lblDiproses;
    private JLabel lblSelesai;
    
    private KeluhanController keluhanController;

    public DashboardMahasiswaPanel() {
        this.keluhanController = new KeluhanController();

        // Inisialisasi Label Angka dengan nilai awal "0"
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
        
        JLabel lblWelcome = new JLabel("Dashboard Mahasiswa");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 28));
        lblWelcome.setForeground(Color.BLACK);
        
        JLabel lblSub = new JLabel("Ringkasan status keluhan yang telah Anda laporkan.");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSub.setForeground(Color.DARK_GRAY);

        headerPanel.add(lblWelcome, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.CENTER);

        // 3. Bagian Konten (Kartu Statistik dengan GridLayout)
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Masukkan label global ke dalam kartu pembantu
        cardsPanel.add(createStatCard("Total Keluhan", lblTotal));
        cardsPanel.add(createStatCard("Sedang Diproses", lblDiproses));
        cardsPanel.add(createStatCard("Selesai", lblSelesai));

        // Memasukkan header dan kartu ke dalam Panel utama
        add(headerPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        // 4. Tarik data pertama kali saat panel dirender
        loadData();
    }

    // --- METHOD INI YANG BAKAL DIPANGGIL SAMA FRAME BUAT REFRESH ---
    public void loadData() {
        String nim = UserSession.getUser(); // Ambil session mahasiswa
        
        // NOTE: Pastikan method di controller lu (getTotalStatus dll) 
        // menerima parameter 'nim', biar yang dihitung cuma keluhan dia aja.
        // Kalo method lu sebelumnya gak pakai parameter, lu wajib tambahin di controllernya.
        int total = keluhanController.getTotalStatus(nim); 
        int diproses = keluhanController.getDiprosesStatus(nim);
        int selesai = keluhanController.getSelesaiStatus(nim);

        // Timpa text lama dengan data terbaru dari database
        lblTotal.setText(String.valueOf(total));
        lblDiproses.setText(String.valueOf(diproses));
        lblSelesai.setText(String.valueOf(selesai));
    }

    // Method pembantu untuk membuat JLabel nilai biar rapi dan seragam
    private JLabel createValueLabel() {
        JLabel lbl = new JLabel("0");
        lbl.setFont(new Font("Arial", Font.BOLD, 48));
        lbl.setForeground(Color.BLACK);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return lbl;
    }

    // Method pembantu untuk membuat kotak desain bergaris tepi hitam
    private JPanel createStatCard(String title, JLabel lblValue) {
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

        card.add(lblTitle);
        card.add(lblValue); // Label yang dimasukkan adalah label referensi global

        return card;
    }
}