package com.s473618.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.s473618.entities.WantToBuy;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface WantToBuyRepository extends CrudRepository<WantToBuy, Integer> {

    Optional<WantToBuy> findById(Integer id);

    @Query(value = "select * from WantToBuy w where w.book_name = ?1", nativeQuery = true)
    Optional<WantToBuy> checkInWhatForm(String book_name);

    @Query(value = "select count(*) form WantToBuy w where w.id_buy = ?1", nativeQuery = true)
    Integer checkIfToBuyExist(Integer id);


}
