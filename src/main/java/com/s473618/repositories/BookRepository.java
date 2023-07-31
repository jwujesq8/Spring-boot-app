package com.s473618.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.s473618.entities.Book;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>, PagingAndSortingRepository<Book, Integer> {

    //List<Book> findById(int id);

    @Query(value= "select count(*) from Book b where b.id = ?1", nativeQuery = true)
    Integer checkIfExist(Integer id);

}