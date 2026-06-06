package controller;

import model.Keluhan;
import repository.KeluhanRepository;
import java.util.List;

//Connected to Repository - Model 
public class KeluhanController {
    
    KeluhanRepository repoKeluhan = new KeluhanRepository();
    
    //Get total keluhan 
    public int getTotalStatus() {
        return repoKeluhan.countSemuaKeluhan("TOTAL");
    }
    public int getDiprosesStatus() {
        return repoKeluhan.countSemuaKeluhan("DIPROSES");
    }
    public int getSelesaiStatus() {
        return repoKeluhan.countSemuaKeluhan("SELESAI");
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
