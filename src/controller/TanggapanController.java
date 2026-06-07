package controller;

import Entity.Keluhan;
import Entity.Petugas;
import Entity.Tanggapan;
import repository.TanggapanRepository;
import java.sql.Timestamp;

public class TanggapanController {

    private TanggapanRepository tanggapanRepo;

    public TanggapanController() {
        this.tanggapanRepo = new TanggapanRepository();
    }

    public boolean kirimTanggapan(String idKeluhanStr, String isiTanggapan) {
        try {
            // 1. Parsing ID Keluhan dari String ke Long
            long idKeluhan = Long.parseLong(idKeluhanStr);

            // 2. Bungkus objek Keluhan
            Keluhan keluhan = new Keluhan();
            keluhan.setIdKeluhan(idKeluhan);

            // 3. Bungkus objek Petugas 
            // (Nanti jangan lupa id_petugas ini dinamis ngambil dari UserSession ya)
            Petugas petugas = new Petugas();
            petugas.setIdPetugas(1); 

            // 4. Rakit objek Tanggapan secara utuh
            Tanggapan tanggapan = new Tanggapan();
            tanggapan.setKeluhan(keluhan);
            tanggapan.setPetugas(petugas);
            tanggapan.setIsiTanggapan(isiTanggapan);
            tanggapan.setTanggalTanggapan(new Timestamp(System.currentTimeMillis())); // Waktu saat ini
            
            // 5. Eksekusi simpan tanggapan
            boolean isTanggapanSaved = tanggapanRepo.save(tanggapan);

            // --- 6. FIX NYA DI SINI: UPDATE STATUS KELUHAN JADI "selesai" ---
            if (isTanggapanSaved) {
                // Panggil repo keluhan buat ubah statusnya
                repository.KeluhanRepository keluhanRepo = new repository.KeluhanRepository();
                
                // Return true jika update status berhasil (pakai huruf kecil sesuai constraint DB)
                return keluhanRepo.updateStatus(idKeluhan, "selesai"); 
            }

            return false;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}