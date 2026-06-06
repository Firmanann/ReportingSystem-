package view.panel.mahasiswa;

import controller.KeluhanController;
import model.Keluhan;
import model.Kategori;
import model.Mahasiswa;
import utils.UserSession;
import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class FormKeluhanPanel extends JPanel {

    private JComboBox<String> cbKategori;
    private JTextArea txtIsiKeluhan;
    private JButton btnKirim;
    private KeluhanController keluhanController;

    //UI Design
    public FormKeluhanPanel() {
        keluhanController = new KeluhanController();

        // Setup Dasar Panel (Minimalis Monochrome)
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // 1. Bagian Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Buat Keluhan Baru");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLACK);
        headerPanel.add(lblTitle, BorderLayout.WEST);

        // 2. Bagian Form Input
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);

        // -- Label & Dropdown Kategori --
        JLabel lblKategori = new JLabel("Kategori Keluhan:");
        lblKategori.setFont(new Font("Arial", Font.BOLD, 14));
        lblKategori.setAlignmentX(Component.LEFT_ALIGNMENT);

        // PERBAIKAN: Teks array di UI sini dibuat bersih tanpa angka
        String[] listKategori = {"Fasilitas Kampus", "Akademik", "Administrasi"};
        cbKategori = new JComboBox<>(listKategori);
        cbKategori.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cbKategori.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbKategori.setBackground(Color.WHITE);
        cbKategori.setForeground(Color.BLACK);

        // -- Label & Text Area Isi Keluhan --
        JLabel lblIsi = new JLabel("Detail Keluhan:");
        lblIsi.setFont(new Font("Arial", Font.BOLD, 14));
        lblIsi.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtIsiKeluhan = new JTextArea(10, 20);
        txtIsiKeluhan.setLineWrap(true);
        txtIsiKeluhan.setWrapStyleWord(true);
        txtIsiKeluhan.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(txtIsiKeluhan);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Border tipis hitam

        // Memasukkan komponen ke form
        formPanel.add(lblKategori);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi
        formPanel.add(cbKategori);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spasi
        formPanel.add(lblIsi);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi
        formPanel.add(scrollPane);

        // 3. Bagian Footer (Tombol Kirim)
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.WHITE);

        btnKirim = new JButton("Kirim Keluhan");
        btnKirim.setFont(new Font("Arial", Font.BOLD, 14));
        btnKirim.setBackground(Color.BLACK); // Tombol hitam
        btnKirim.setForeground(Color.WHITE); // Teks putih
        btnKirim.setFocusPainted(false);
        btnKirim.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Menambahkan Aksi pada Tombol Kirim
        btnKirim.addActionListener(e -> handleKirimKeluhan());

        footerPanel.add(btnKirim);

        // Menempelkan semua bagian ke kanvas utama (Wajib di bawah)
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    //Main Logic Create keluhan
    private void handleKirimKeluhan() {
        
        //1. Terima input 
        String isi = txtIsiKeluhan.getText().trim();

        // 1. Validasi Input Kosong
        if (isi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi keluhan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }


        //2. Take input Kategori number from ComboBox 
        long idKategori = cbKategori.getSelectedIndex() + 1; //index start from 0

        //3. Mapping to Model : Mahasiswa - Kategori - Keluhan 
        Mahasiswa mahasiswa = new Mahasiswa();
        Kategori kategori = new Kategori();
        Keluhan keluhanBaru = new Keluhan();

        mahasiswa.setNim(UserSession.getUser());
        
        kategori.setIdKategori(idKategori);
        
        keluhanBaru.setMahasiswa(mahasiswa);
        keluhanBaru.setKategori(kategori);
        keluhanBaru.setIsiKeluhan(isi);
        keluhanBaru.setTanggalPengaduan(new Timestamp(System.currentTimeMillis()));
        keluhanBaru.setStatusKeluhan("Menunggu"); //default

        // 4. save to db
        boolean sukses = keluhanController.saveKeluhan(keluhanBaru);

        // 5. Response 
        if (sukses) {
            JOptionPane.showMessageDialog(this, "Keluhan berhasil dikirim!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
            //Reset box
            txtIsiKeluhan.setText("");  
            cbKategori.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengirim keluhan. Silakan coba lagi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}