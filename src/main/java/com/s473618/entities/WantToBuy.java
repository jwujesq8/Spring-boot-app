package com.s473618.entities;

import java.io.Serializable;

import javax.persistence.*;
//import javax.validation.constraints.Max;


@Entity
@Table
public class WantToBuy implements Serializable {

    @Id
    @GeneratedValue(generator = "gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen", sequenceName = "buy_seq")
    int id_buy;

    @Column(nullable = false)
    String book_name;

    @Column(nullable = true)
    String author_surname;

    @Column(nullable = true)
    String form;

    public WantToBuy() {}

    public WantToBuy(String b_name){
        this.book_name = b_name;
    }

    public WantToBuy(String b_name, String a_surname){
        this.book_name = b_name;
        this.author_surname = a_surname;
    }

    public WantToBuy(String b_name, String a_surname, String f){
        this.book_name = b_name;
        this.author_surname = a_surname;
        this.form = f;
    }

    public int getId_buy() {
        return id_buy;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getAuthor_surname() {
        return author_surname;
    }

    public String getForm() {
        return form;
    }

    public void setId_buy(int id_buy) {
        this.id_buy = id_buy;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor_surname(String author_surname) {
        this.author_surname = author_surname;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
