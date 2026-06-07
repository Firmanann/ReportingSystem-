package Entity;

import java.sql.Timestamp;

public class Tanggapan {

    private long idTanggapan;
    private Keluhan keluhan;
    private Petugas petugas;
    private String isiTanggapan;
    private Timestamp tanggalTanggapan;

    public Tanggapan() {
    }

    public Tanggapan(long idTanggapan,
                     Keluhan keluhan,
                     Petugas petugas,
                     String isiTanggapan,
                     Timestamp tanggalTanggapan) {

        this.idTanggapan = idTanggapan;
        this.keluhan = keluhan;
        this.petugas = petugas;
        this.isiTanggapan = isiTanggapan;
        this.tanggalTanggapan = tanggalTanggapan;
    }

    public long getIdTanggapan() {
        return idTanggapan;
    }

    public void setIdTanggapan(long idTanggapan) {
        this.idTanggapan = idTanggapan;
    }

    public Keluhan getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(Keluhan keluhan) {
        this.keluhan = keluhan;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public String getIsiTanggapan() {
        return isiTanggapan;
    }

    public void setIsiTanggapan(String isiTanggapan) {
        this.isiTanggapan = isiTanggapan;
    }

    public Timestamp getTanggalTanggapan() {
        return tanggalTanggapan;
    }

    public void setTanggalTanggapan(Timestamp tanggalTanggapan) {
        this.tanggalTanggapan = tanggalTanggapan;
    }
}