package view.panel.petugas;

import view.frame.MainPetugasFrame;
import controller.TanggapanController;
import javax.swing.*;
import java.awt.*;

public class TanggapanPanel extends JPanel {

    private MainPetugasFrame mainFrame;
    private String idKeluhan;
    private JTextArea txtTanggapan;

    public TanggapanPanel(MainPetugasFrame mainFrame, String idKeluhan) {
        this.mainFrame = mainFrame;
        this.idKeluhan = idKeluhan;
        
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // 1. Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Beri Tanggapan");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);

        JLabel lblSub = new JLabel("Membalas keluhan dengan ID: " + idKeluhan);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSub.setForeground(Color.DARK_GRAY);

        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.CENTER);

        // 2. Form Konten
        JPanel formPanel = new JPanel(new BorderLayout(0, 10));
        formPanel.setBackground(Color.WHITE);

        JLabel lblTanggapan = new JLabel("Isi Tanggapan:");
        lblTanggapan.setFont(new Font("Arial", Font.BOLD, 14));
        lblTanggapan.setForeground(Color.BLACK);

        txtTanggapan = new JTextArea(10, 40);
        txtTanggapan.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTanggapan.setLineWrap(true);
        txtTanggapan.setWrapStyleWord(true);
        txtTanggapan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(txtTanggapan);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 

        formPanel.add(lblTanggapan, BorderLayout.NORTH);
        formPanel.add(scrollPane, BorderLayout.CENTER);

        // 3. Tombol Action (Bawah)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        btnPanel.setBackground(Color.WHITE);

        JButton btnKembali = new JButton("Kembali");
        styleButton(btnKembali, Color.WHITE, Color.BLACK, true);

        JButton btnKirim = new JButton("Kirim Tanggapan");
        styleButton(btnKirim, Color.BLACK, Color.WHITE, false);

        btnPanel.add(btnKembali);
        btnPanel.add(btnKirim);

        // Tambahkan ke Panel Utama
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
 
        // Tombol Kembali: Balik ke halaman Data Keluhan
        btnKembali.addActionListener(e -> {
            mainFrame.kembaliKeDataKeluhan();
        });
        
        
        TanggapanController tanggapanController = new TanggapanController();

        // Tombol Kirim: Nanti hubungkan ke Controller
        btnKirim.addActionListener(e -> {
            String isi = txtTanggapan.getText();
            if (isi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tanggapan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Panggil controller untuk proses simpan data & update status keluhan
            boolean sukses = tanggapanController.kirimTanggapan(idKeluhan, isi);
            
            if (sukses) {
                JOptionPane.showMessageDialog(this, "Tanggapan berhasil dikirim dan status keluhan diupdate jadi SELESAI!");
                mainFrame.kembaliKeDataKeluhan(); // Balik ke tabel utama
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengirim tanggapan. Terjadi kesalahan sistem.", "Error", JOptionPane.ERROR_MESSAGE);
            }
                        
            // Balik ke halaman data keluhan setelah sukses
            mainFrame.kembaliKeDataKeluhan();
        });
    }

    // Method pembantu buat desain tombol monokrom
    private void styleButton(JButton btn, Color bg, Color fg, boolean isOutline) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 40));
        
        if (isOutline) {
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        } else {
            btn.setBorderPainted(false);
        }
    }
}