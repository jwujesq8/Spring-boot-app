package com.s473618.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.s473618.entities.ReadBooks;
import com.s473618.repositories.ReadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReadServiceImpl implements ReadService{

    @Autowired
    private ReadRepository readRepository;

    @Override
    public List<ReadBooks> listAllRead(){
        return (List<ReadBooks>) readRepository.findAll();
    }

    @Override
    public Optional<ReadBooks> getReadById(Integer id){
        return readRepository.findById(id);
    }

    @Override
    public ReadBooks saveRead(ReadBooks read){
        return readRepository.save(read);
    }

    @Override
    public void deleteRead(Integer id){
        readRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfReadExist(Integer id){
        if(readRepository.checkIfReadExist(id) > 0)
            return true;
        else return false;
    }


}
