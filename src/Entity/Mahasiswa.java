package Entity;


public class Mahasiswa {
    
    private String nim;
    private String nama;
    private String prodi;
    private String phone;
    private String email;
    private String password;
    
    public Mahasiswa(){
        
    }
    
    public Mahasiswa(String nim, String nama, String prodi, String phone, String email, String password){
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    
    //Getter - Setter 
    public String getNim(){
        return nim;
    }
    
    public void setNim(String nim){
        this.nim = nim;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getProdi(){
        return prodi;
    }
    
    public void setProdi(String prodi){
        this.prodi = prodi;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }   
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}

