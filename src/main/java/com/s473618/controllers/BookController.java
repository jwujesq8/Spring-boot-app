package com.s473618.controllers;


import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.s473618.entities.Book;
import com.s473618.services.BookService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api")

public class BookController {

    @Autowired
    private BookService bookService;
    private Logger logger = Logger.getLogger("Controller");

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> list() {
        return bookService.listAllBooks();
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable("id") Integer id) {
        return bookService.getBookById(id).orElseGet(null);
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookByParamId(@RequestParam("id") Integer id) {
        return bookService.getBookById(id).orElseGet(null);
    }

    @GetMapping(value = "/books/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Book> listByPage(@PathVariable("page") Integer pageNr, @RequestParam(value = "size", required = false) Optional<Integer> howManyOnOnePage) {
        return bookService.listAllBooksPaging(pageNr, howManyOnOnePage.orElse(2));
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok().body(book);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleExceptionNotValid(MethodArgumentNotValidException exception) {
        return exception.getFieldError().getField();
        //return exception.getFieldError().toString();
    }

    @PutMapping(value = "/book")
    public ResponseEntity<Void> editBook(@RequestBody Book book) {
        if(!bookService.checkIfBookExist(book.getId_book())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            bookService.saveBook(book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/book/{id}")
    public List<Book> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return bookService.listAllBooks();
    }



}