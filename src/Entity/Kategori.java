package Entity;

public class Kategori {

    private long idKategori;    
    private String namaKategori;
    private String keterangan;

    public Kategori() {
    }

    public Kategori(long idKategori, String namaKategori, String keterangan) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
        this.keterangan = keterangan;
    }

    public long getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(long idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return namaKategori;
    }
}