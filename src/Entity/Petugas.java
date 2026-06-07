package Entity;

public class Petugas {

    private long idPetugas;
    private String namaPetugas;
    private String email;
    private String jabatan;
    private String phone;
    private String password;

    public Petugas() {
    }

    public Petugas(long idPetugas, String namaPetugas, String email, String jabatan, String phone, String password) {
        this.idPetugas = idPetugas;
        this.namaPetugas = namaPetugas;
        this.email = email;
        this.jabatan = jabatan;
        this.phone = phone;
        this.password = password;
    }

    public long getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(long idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return namaPetugas;
    }
}