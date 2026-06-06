package controller;

import model.Keluhan;
import repository.KeluhanRepository;
import java.util.List;

//Connected to Repository - Model 
public class KeluhanController {
    
    KeluhanRepository repoKeluhan = new KeluhanRepository();
    
    public int getTotalStatus(String nim){
        return repoKeluhan.countKeluhanByStatus(nim, "TOTAL");
    }
    
    public int getDiprosesStatus(String nim){
        return repoKeluhan.countKeluhanByStatus(nim, "DIPROSES");
    }
    
    public int getSelesaiStatus(String nim){
        return repoKeluhan.countKeluhanByStatus(nim, "SELESAI");
    }

    public boolean saveKeluhan(Keluhan keluhan){
       
        //Save by repository 
        repoKeluhan.save(keluhan);
      
        return true;
    }
    
    public List<Keluhan> historyKeluhan(String nim) {
            return repoKeluhan.findByMahasiswa(nim);
        }
    
}
