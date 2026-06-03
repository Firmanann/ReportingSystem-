package model;

import java.sql.Timestamp;

public class Keluhan {

    private long idKeluhan;
    private Mahasiswa mahasiswa;
    private Kategori kategori;
    private String isiKeluhan;
    private Timestamp tanggalPengaduan;
    private String statusKeluhan;

    public Keluhan() {
    }

    public Keluhan(long idKeluhan,
                   Mahasiswa mahasiswa,
                   Kategori kategori,
                   String isiKeluhan,
                   Timestamp tanggalPengaduan,
                   String statusKeluhan) {

        this.idKeluhan = idKeluhan;
        this.mahasiswa = mahasiswa;
        this.kategori = kategori;
        this.isiKeluhan = isiKeluhan;
        this.tanggalPengaduan = tanggalPengaduan;
        this.statusKeluhan = statusKeluhan;
    }

    public long getIdKeluhan() {
        return idKeluhan;
    }

    public void setIdKeluhan(long idKeluhan) {
        this.idKeluhan = idKeluhan;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getIsiKeluhan() {
        return isiKeluhan;
    }

    public void setIsiKeluhan(String isiKeluhan) {
        this.isiKeluhan = isiKeluhan;
    }

    public Timestamp getTanggalPengaduan() {
        return tanggalPengaduan;
    }

    public void setTanggalPengaduan(Timestamp tanggalPengaduan) {
        this.tanggalPengaduan = tanggalPengaduan;
    }

    public String getStatusKeluhan() {
        return statusKeluhan;
    }

    public void setStatusKeluhan(String statusKeluhan) {
        this.statusKeluhan = statusKeluhan;
    }
}