package controller;

import model.Kategori;
import repository.KategoriRepository;
import java.util.List;
import javax.swing.JOptionPane;

public class KategoriController {
    
    private KategoriRepository kategoriRepo;

    public KategoriController() {
        this.kategoriRepo = new KategoriRepository();
    }

    // Mengambil semua data untuk tabel
    public List<Kategori> getAll() {
        return kategoriRepo.findAll();
    }

    // Logic Simpan
    public boolean tambah(String nama, String keterangan) {
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama kategori tidak boleh kosong!");
            return false;
        }
        
        Kategori kat = new Kategori();
        kat.setNamaKategori(nama);
        kat.setKeterangan(keterangan);
        
        return kategoriRepo.save(kat);
    }

    // Logic Update
    public boolean update(int id, String nama, String keterangan) {
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama kategori tidak boleh kosong!");
            return false;
        }

        Kategori kat = new Kategori();
        kat.setIdKategori(id);
        kat.setNamaKategori(nama);
        kat.setKeterangan(keterangan);
        
        return kategoriRepo.update(kat);
    }

    // Logic Delete
    public boolean delete(int id) {
        return kategoriRepo.delete(id);
    }
}