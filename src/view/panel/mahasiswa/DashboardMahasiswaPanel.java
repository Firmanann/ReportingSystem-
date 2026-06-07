package view.panel.mahasiswa;

import controller.KeluhanController;
import utils.UserSession;

import javax.swing.*;
import java.awt.*;

public class DashboardMahasiswaPanel extends JPanel {

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
        
        //header 
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

        //kotak statistik 
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        cardsPanel.add(createStatCard("Total Keluhan", lblTotal));
        cardsPanel.add(createStatCard("Menunggu", lblDiproses));
        cardsPanel.add(createStatCard("Selesai", lblSelesai));


        //header dalam panel utama 
        add(headerPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        // 4. Tarik data pertama kali saat panel dirender
        loadData();
    }


    //load data untuk refresh data 
    public void loadData() {
        String nim = UserSession.getUser(); 
        
        int total = keluhanController.getTotalStatus(nim); 
        int diproses = keluhanController.getDiprosesStatus(nim);
        int selesai = keluhanController.getSelesaiStatus(nim);

        lblTotal.setText(String.valueOf(total));
        lblDiproses.setText(String.valueOf(diproses));
        lblSelesai.setText(String.valueOf(selesai));
    }

    //Label nilai biar rapi dan seragam
    private JLabel createValueLabel() {
        JLabel lbl = new JLabel("0");
        lbl.setFont(new Font("Arial", Font.BOLD, 48));
        lbl.setForeground(Color.BLACK);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return lbl;
    }

    //kotak desain bergaris tepi hitam
    private JPanel createStatCard(String title, JLabel lblValue) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        
        //border hitam tebal (2px)
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