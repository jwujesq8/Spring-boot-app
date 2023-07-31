package com.s473618.services;

import com.s473618.entities.Book;
import com.s473618.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> listAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById (Integer id){
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id){
        bookRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfBookExist(Integer id){
        if(bookRepository.checkIfExist(id) > 0)
            return true;
        else return false;
    }

    @Override
    public Iterable<Book> listAllBooksPaging(Integer pageNr, Integer howManyOnOnePage) {
        return bookRepository.findAll(PageRequest.of(pageNr,howManyOnOnePage));
    }
}