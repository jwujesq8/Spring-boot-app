package com.s473618.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "author_id")
    Author author;

    @Column(nullable = true)
    String language;

    @Column(nullable = true)
    int number_of_pages;

    @Column(nullable = true)
    String genre;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<ReadBooks> read_books = new HashSet<>();

    public Book() {}

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name, Author author, String language, int number_of_pages, String genre) {
        this.name = name;
        this.author = author;
        this.language = language;
        this.number_of_pages = number_of_pages;
        this.genre = genre;
    }

    public Integer getId_book() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setId_book(Integer id_book) {
        this.id = id_book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<ReadBooks> getRead_books() {
        return read_books;
    }

    public void setRead_books(Set<ReadBooks> read_books) {
        this.read_books = read_books;
    }
}