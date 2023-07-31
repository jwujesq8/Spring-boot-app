package com.s473618.repositories;


//import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.s473618.entities.Book;
import com.s473618.entities.ReadBooks;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadRepository extends CrudRepository<ReadBooks, Integer> {

    Optional<ReadBooks> findById(Integer id);

    @Query(value = "select count(*) from Read_Books r where r.book_id = ?1", nativeQuery = true)
    Integer checkIfReadExist(Integer id);



//    @Query("select from Read r join r.book rb ")
//    List<Read> findByBookName(String b_name);
}
