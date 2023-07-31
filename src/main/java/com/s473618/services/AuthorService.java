package com.s473618.services;

import org.springframework.stereotype.Service;
import com.s473618.entities.Author;

import java.util.Optional;

@Service
public interface AuthorService {
    Iterable<Author> listAllAuthors();

    Optional<Author> getAuthorById(Integer id);

    Author saveAuthor(Author author);

    void deleteAuthor(Integer id);

    Boolean checkIfAuthorExist(Integer id);
}