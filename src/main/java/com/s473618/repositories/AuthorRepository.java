package com.s473618.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.s473618.entities.Author;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findById(int id);

//    List<Author> findByAuthor_surname(String surname);

    @Query(value = "select count(*) from Author a where a.id = ?1", nativeQuery = true)
    Integer checkIfAuthorExist(Integer id);

//    @Query("select s from Author a join a.surname s where a.surname = ?1")
//    List<Author> getAuthorByAuthor_surname(String surname);

}
