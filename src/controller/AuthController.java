package controller;

import model.Mahasiswa;
import model.Petugas;
import repository.MahasiswaRepository;
import repository.PetugasRepository;

public class AuthController {
    
    MahasiswaRepository repoMahasiswa = new MahasiswaRepository();
    PetugasRepository repoPetugas = new PetugasRepository();

    
    public boolean register(String nim, String nama, String prodi, String phone, String email, String password){
        
        //New object 
        Mahasiswa mahasiswa = new Mahasiswa();
        
        //Mapping to entity 
        mahasiswa.setNim(nim);
        mahasiswa.setNama(nama);
        mahasiswa.setProdi(prodi);
        mahasiswa.setPhone(phone);
        mahasiswa.setEmail(email);
        mahasiswa.setPassword(password);
        
        //save entity by repository
        boolean isSave = repoMahasiswa.save(mahasiswa);
        
        return isSave;
    }
    
    public String login(String username, String password){
        
        //validate existing data mahasiswa (mahasiswa using nim to login)
        Mahasiswa mahasiswa = repoMahasiswa.login(username, password);
        if (mahasiswa != null){
            return "MAHASISWA";
        }
        
        //Validate existing data petugas (petugas using email to login)
        Petugas petugas = repoPetugas.login(username, password);
        if(petugas != null){
            return "PETUGAS";
        }
        
        //2 Conditions is Fail 
        return "GAGAL LOGIN";
    }
    
    
    
}
