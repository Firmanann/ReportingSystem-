package controller;

import model.Keluhan;
import model.Petugas;
import model.Tanggapan;
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
            // (Sesuaikan id_petugas ini dengan ID petugas yang sedang login dari UserSession lu)
            Petugas petugas = new Petugas();
            petugas.setIdPetugas(1); // Sementara di-hardcode ID 1, nanti sesuaikan

            // 4. Rakit objek Tanggapan secara utuh
            Tanggapan tanggapan = new Tanggapan();
            tanggapan.setKeluhan(keluhan);
            tanggapan.setPetugas(petugas);
            tanggapan.setIsiTanggapan(isiTanggapan);
            tanggapan.setTanggalTanggapan(new Timestamp(System.currentTimeMillis())); // Waktu saat ini

            // 5. Kirim ke Repo untuk eksekusi transaksi database
            return tanggapanRepo.save(tanggapan);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}