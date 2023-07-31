package com.s473618.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.s473618.entities.Book;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService{

    List<Book> listAllBooks();

    Optional<Book> getBookById(Integer id);

    Book saveBook(Book book);

    void deleteBook(Integer id);

    Boolean checkIfBookExist(Integer id);

    Iterable<Book> listAllBooksPaging(Integer pageNr, Integer howManyBooksOnPage);

}
