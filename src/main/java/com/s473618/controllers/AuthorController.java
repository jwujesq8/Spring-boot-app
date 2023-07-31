package com.s473618.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.s473618.entities.Author;
import com.s473618.entities.Book;
import com.s473618.services.AuthorService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")

public class AuthorController {

    @Autowired
    private AuthorService authorService;
    private Logger logger = Logger.getLogger("Controller");

    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Author> list(Model model) {
        return authorService.listAllAuthors();
    }

    @GetMapping(value = "/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author getAuthorById(@PathVariable("id") Integer id) {
        return authorService.getAuthorById(id).orElseGet(null);
    }

    @GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author getAuthorByParamId(@RequestParam("id") Integer id) {
        return authorService.getAuthorById(id).orElseGet(null);
    }

    @PostMapping(value = "/author")
    public ResponseEntity<Author> createAuthor(@RequestBody @Valid Author author) {
        authorService.saveAuthor(author);
        return ResponseEntity.ok().body(author);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleExceptionNotValid(MethodArgumentNotValidException exception) {
        return exception.getFieldError().getField();
        //return exception.getFieldError().toString();
    }

    @PutMapping(value = "/author")
    public ResponseEntity<Void> editAuthor(@RequestBody Author author) {
        if(!authorService.checkIfAuthorExist(author.getId_author())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            authorService.saveAuthor(author);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/author/{id}")
    public List<Author> deleteAuthor(@PathVariable("id") Integer id) {
        authorService.deleteAuthor(id);
        return (List<Author>) authorService.listAllAuthors();
    }

}