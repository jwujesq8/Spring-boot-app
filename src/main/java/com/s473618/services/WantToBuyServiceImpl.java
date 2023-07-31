package com.s473618.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.s473618.entities.WantToBuy;
import com.s473618.repositories.WantToBuyRepository;

import java.util.Optional;

@Service
public class WantToBuyServiceImpl implements WantToBuyService{

    @Autowired
    private WantToBuyRepository wantToBuyRepository;

    @Override
    public Iterable<WantToBuy> listAllToBuy(){
        return wantToBuyRepository.findAll();
    }

    @Override
    public Optional<WantToBuy> getToBuyById(Integer id){
        return wantToBuyRepository.findById(id);
    }

    @Override
    public Optional<WantToBuy> getToBuyByBookName(String name) {
        return wantToBuyRepository.checkInWhatForm(name);
    }

    @Override
    public WantToBuy saveToBuy(WantToBuy wantToBuy) {
        return wantToBuyRepository.save(wantToBuy);
    }

    @Override
    public void deleteToBuy(Integer id){
        wantToBuyRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfToBuyExist(Integer id){
        if(wantToBuyRepository.checkIfToBuyExist(id) > 0 ){
            return true;
        }
        else return false;
    }

    @Override
    public Optional<WantToBuy> checkInWhatForm(String book_name){
        if(wantToBuyRepository.checkInWhatForm(book_name).isPresent()){
            return wantToBuyRepository.checkInWhatForm(book_name);
        }
        else return null;
    }
}
