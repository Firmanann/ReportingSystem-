package controller;

import Entity.Keluhan;
import repository.KeluhanRepository;
import java.util.List;

//Connected to Repository - Model 
public class KeluhanController {
    
    KeluhanRepository repoKeluhan = new KeluhanRepository();
    
    //Get total keluhan mahasiswa
    public int getTotalStatus(String nim) {
        return repoKeluhan.countKeluhanByNim("total", nim);
    }
    public int getDiprosesStatus(String nim) {
        return repoKeluhan.countKeluhanByNim("menunggu", nim);
    }
    public int getSelesaiStatus(String nim) {
        return repoKeluhan.countKeluhanByNim("selesai", nim);
    }
    
    //Get total keluhan Petugas
    public int getTotalStatus() {
        return repoKeluhan.countSemuaKeluhan("total");
    }
    
    public int getDiprosesStatus() {
        // Gabungin hitungan 'menunggu' dan 'diproses' biar gak ada data yang ilang dari dashboard
        int menunggu = repoKeluhan.countSemuaKeluhan("menunggu");
        int diproses = repoKeluhan.countSemuaKeluhan("diproses");
        return menunggu + diproses;
    }
    
    public int getSelesaiStatus() {
        return repoKeluhan.countSemuaKeluhan("selesai");
    }
    

    public boolean saveKeluhan(Keluhan keluhan){
       
        //Save by repository 
        repoKeluhan.save(keluhan);
      
        return true;
    }
    
    //Get riwayat keluhan 
    public List<Keluhan> historyKeluhan(String nim) {
            return repoKeluhan.findByMahasiswa(nim);
    }
    
    //Data keluhan 
    public List<Keluhan> getAllKeluhan() {
        return repoKeluhan.findAll();
    }
    
}
