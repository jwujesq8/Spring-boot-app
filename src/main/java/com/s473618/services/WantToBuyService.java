package com.s473618.services;


import org.springframework.stereotype.Service;
import com.s473618.entities.WantToBuy;

import java.util.Optional;

@Service
public interface WantToBuyService {

    Iterable<WantToBuy> listAllToBuy();

    Optional<WantToBuy> getToBuyById(Integer id);

    Optional<WantToBuy> getToBuyByBookName(String name);


    WantToBuy saveToBuy(WantToBuy wantToBuy);

    void deleteToBuy(Integer id);

    Boolean checkIfToBuyExist(Integer id);

    public Optional<WantToBuy> checkInWhatForm(String book_name);
}
