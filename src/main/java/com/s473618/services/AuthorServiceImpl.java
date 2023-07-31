package com.s473618.services;


import com.s473618.entities.Author;
import com.s473618.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Iterable<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById (Integer id){
        return authorRepository.findById(id);
    }

    @Override
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer id){
        authorRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfAuthorExist(Integer id){
        if(authorRepository.checkIfAuthorExist(id) > 0)
            return true;
        else return false;
    }

}
