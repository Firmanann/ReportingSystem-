package view.panel.mahasiswa;

import controller.KeluhanController;
import model.Keluhan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RiwayatKeluhanPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private KeluhanController keluhanController; 
    private String nimMahasiswa;

    public RiwayatKeluhanPanel(String nim) {
        this.nimMahasiswa = nim;
        this.keluhanController = new KeluhanController(); 
        
        setupUI();
        loadData();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Setup Tabel
        String[] columns = {"ID Keluhan", "Kategori", "Isi Keluhan", "Tanggal", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Styling Minimalis Monochrome
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setRowHeight(30);
        
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Judul Panel
        JLabel lblTitle = new JLabel("Riwayat Keluhan Anda");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        // Kosongkan tabel sebelum diisi ulang
        tableModel.setRowCount(0);

        //Proses query 
        List<Keluhan> listKeluhan = keluhanController.historyKeluhan(nimMahasiswa);

        for (Keluhan k : listKeluhan) {
            Object[] row = {
                k.getIdKeluhan(),
                k.getKategori().getNamaKategori(),
                k.getIsiKeluhan(),
                k.getTanggalPengaduan(),
                k.getStatusKeluhan()
            };
            tableModel.addRow(row);
        }
    }
}