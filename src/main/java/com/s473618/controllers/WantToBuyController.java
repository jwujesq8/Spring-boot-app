package com.s473618.controllers;

import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.s473618.entities.WantToBuy;
import com.s473618.services.WantToBuyService;
import org.springframework.http.MediaType;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")

public class WantToBuyController {

    @Autowired
    private WantToBuyService wantToBuyService;
    private Logger logger = Logger.getLogger("controller");

    @GetMapping(value = "/buys")
    public Iterable<WantToBuy> list(Model model){ return wantToBuyService.listAllToBuy(); }

    @GetMapping(value = "/buy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WantToBuy getToBuyById(@PathVariable("id") Integer id){
        return wantToBuyService.getToBuyById(id).orElseGet(null);
    }

    @GetMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public WantToBuy getToBuyByParamId(@RequestParam("id") Integer id){
        return wantToBuyService.getToBuyById(id).orElseGet(null);
    }

    @GetMapping(value = "/buy/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WantToBuy getToBuyByName(@PathVariable("name") String name){
        return wantToBuyService.getToBuyByBookName(name).orElseGet(null);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<WantToBuy> createToBuy(@RequestBody @Valid WantToBuy wantToBuy){
        wantToBuyService.saveToBuy(wantToBuy);
        return ResponseEntity.ok().body(wantToBuy);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleExceptionNotValid(MethodArgumentNotValidException exception){
        return exception.getFieldError().getField();
    }

    @PutMapping(value = "/buy")
    public ResponseEntity<Void> editToBuy(@RequestBody WantToBuy wantToBuy){
        if(!wantToBuyService.checkIfToBuyExist(wantToBuy.getId_buy())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            wantToBuyService.saveToBuy(wantToBuy);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/buy/{id}")
    public RedirectView deleteToBuy(@PathVariable("id") Integer id){
        wantToBuyService.deleteToBuy(id);
        return new RedirectView("/api/buyList", true);
    }

    @ApiIgnore
    @RequestMapping(value = "/api/buyList")
    public Iterable<WantToBuy> redirectAfterDelete(){
        return wantToBuyService.listAllToBuy();
    }



}
