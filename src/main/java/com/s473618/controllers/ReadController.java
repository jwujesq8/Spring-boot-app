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
import com.s473618.entities.ReadBooks;
import com.s473618.services.ReadService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")

public class ReadController {

    @Autowired
    private ReadService readService;
    private Logger logger = Logger.getLogger("controller");

    @GetMapping(value = "/reads", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReadBooks> list(Model model){
        return readService.listAllRead();
    }

    @GetMapping(value = "/read/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadBooks getReadById(@PathVariable("id") Integer id){
        return readService.getReadById(id).orElseGet(null);
    }

    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadBooks getReadByParamId(@RequestParam("id") Integer id){
        return readService.getReadById(id).orElseGet(null);
    }

    @PostMapping(value = "/read")
    public ResponseEntity<ReadBooks> createRead(@RequestBody @Valid ReadBooks read){
        readService.saveRead(read);
        return ResponseEntity.ok().body(read);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleExceptionNotValid(MethodArgumentNotValidException exception){
        return exception.getFieldError().getField();
    }

    @PutMapping(value = "/read")
    public ResponseEntity<Void> editRead(@RequestBody ReadBooks read){
        if(!readService.checkIfReadExist(read.getId())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            readService.saveRead(read);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/read/{id}")
    public List<ReadBooks> deleteRead(@PathVariable("id") Integer id) {
        readService.deleteRead(id);
        return readService.listAllRead();
    }

}
