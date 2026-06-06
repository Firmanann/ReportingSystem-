package view.panel.petugas;

import controller.KategoriController;
import model.Kategori;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class KategoriPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNama;
    private JTextArea txtKeterangan;
    private JButton btnSimpan, btnUbah, btnHapus, btnBersihkan;
    private KategoriController kategoriController;
    private int selectedId = -1; 

    public KategoriPanel() {
        this.kategoriController = new KategoriController();
        setupUI();
        loadData();
    }

    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- 1. HEADER ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Kelola Kategori Keluhan");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel lblSub = new JLabel("Tambah, ubah, atau hapus kategori untuk klasifikasi laporan.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblSub, BorderLayout.SOUTH);

        // --- 2. MAIN CONTENT (GRID 1x2) ---
        JPanel mainContent = new JPanel(new GridLayout(1, 2, 20, 0));
        mainContent.setBackground(Color.WHITE);

        // --- A. FORM PANEL (KIRI) ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Form Input Kategori"));

        // Input Nama
        JPanel pnlNama = createInputGroup("Nama Kategori:");
        txtNama = new JTextField();
        txtNama.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlNama.add(txtNama);

        // Input Keterangan
        JPanel pnlKet = createInputGroup("Keterangan:");
        txtKeterangan = new JTextArea(5, 20);
        txtKeterangan.setLineWrap(true);
        txtKeterangan.setWrapStyleWord(true);
        txtKeterangan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollKet = new JScrollPane(txtKeterangan);
        pnlKet.add(scrollKet);

        // Tombol Aksi
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        btnSimpan = createBtn("Simpan", Color.BLACK, Color.WHITE);
        btnUbah = createBtn("Ubah", Color.WHITE, Color.BLACK);
        btnHapus = createBtn("Hapus", Color.WHITE, Color.BLACK);
        btnBersihkan = createBtn("Bersihkan", Color.WHITE, Color.BLACK);

        btnPanel.add(btnSimpan);
        btnPanel.add(btnUbah);
        btnPanel.add(btnHapus);
        btnPanel.add(btnBersihkan);

        formPanel.add(pnlNama);
        formPanel.add(pnlKet);
        formPanel.add(btnPanel);

        // --- B. TABLE PANEL (KANAN) ---
        String[] columns = {"ID", "Nama Kategori", "Keterangan"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        styleTable(table);

        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.getViewport().setBackground(Color.WHITE);

        mainContent.add(formPanel);
        mainContent.add(scrollTable);

        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);

        // --- 3. EVENT LISTENERS ---
        
        // Klik Tabel -> Pindah ke Form
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(table.getValueAt(row, 0).toString());                    
                    txtNama.setText(table.getValueAt(row, 1).toString());
                    txtKeterangan.setText(table.getValueAt(row, 2).toString());
                }
            }
        });

        btnSimpan.addActionListener(e -> {
            if(kategoriController.tambah(txtNama.getText(), txtKeterangan.getText())) {
                refresh();
            }
        });

        btnUbah.addActionListener(e -> {
            if(selectedId != -1) {
                if(kategoriController.update(selectedId, txtNama.getText(), txtKeterangan.getText())) {
                    refresh();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih kategori di tabel dulu!");
            }
        });

        btnHapus.addActionListener(e -> {
            if(selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus kategori ini?", "Hapus", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION) {
                    kategoriController.delete(selectedId);
                    refresh();
                }
            }
        });

        btnBersihkan.addActionListener(e -> clearForm());
    }

    private void refresh() {
        loadData();
        clearForm();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Kategori> list = kategoriController.getAll();
        for (Kategori k : list) {
            tableModel.addRow(new Object[]{k.getIdKategori(), k.getNamaKategori(), k.getKeterangan()});
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtKeterangan.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    // --- Helper Methods untuk UI ---
    private JPanel createInputGroup(String label) {
        JPanel p = new JPanel(new BorderLayout(0, 5));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JLabel l = new JLabel(label);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        p.add(l, BorderLayout.NORTH);
        return p;
    }

    private JButton createBtn(String text, Color bg, Color fg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFocusPainted(false);
        if (bg == Color.WHITE) b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return b;
    }

    private void styleTable(JTable t) {
        t.setRowHeight(30);
        t.setGridColor(Color.LIGHT_GRAY);
        t.getTableHeader().setBackground(Color.BLACK);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }
}
