package view.panel.petugas;

import controller.KeluhanController;
import Entity.Keluhan;
import view.frame.MainPetugasFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataKeluhanPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private KeluhanController keluhanController;
    private MainPetugasFrame mainFrame;

    public DataKeluhanPanel(MainPetugasFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.keluhanController = new KeluhanController();
        setupUI();
        loadData();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Bagian Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitle = new JLabel("Semua Data Keluhan");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(Color.BLACK);
        
        JLabel lblSub = new JLabel("Daftar seluruh keluhan yang masuk ke dalam sistem pelaporan.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(Color.DARK_GRAY);

        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.CENTER);

        // Setup Tabel
        String[] columns = {"ID Keluhan", "Kategori", "Isi Keluhan", "Tanggal", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(tableModel);

        // logic click table 
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                // Klik 2 kali untuk ngebuka panel tanggapan
                if (evt.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String idKeluhan = table.getValueAt(row, 0).toString(); 
                        mainFrame.bukaPanelTanggapan(idKeluhan); 
                    }
                }
            }
        });
        // ===================================

        // Styling Tabel Monokrom
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setRowHeight(35);
        
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setPreferredSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadData() {
        tableModel.setRowCount(0);
        List<Keluhan> listKeluhan = keluhanController.getAllKeluhan();

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