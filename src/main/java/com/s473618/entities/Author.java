package com.s473618.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;

import javax.persistence.*;
//import javax.validation.constraints.Max;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"author_name", "author_surname"}
)})
public class Author {
    @Id
    @GeneratedValue(generator = "gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")
    @Column
    private int id;

    @Column(nullable = false)
    private String author_name;

    @Column(nullable = false)
    private String author_surname;

    @Column(nullable = true, length = 1000)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private DateTime date_of_birth;

    @JsonIgnore
    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Book> books_by_author = new HashSet<>();

    public Author() {}

    public Author(String a_name, String a_surname) {
        this.author_name = a_name;
        this.author_surname = a_surname;
    }

    public Author(String a_name, String a_surname, DateTime a_date_of_birth) {
        this.author_name = a_name;
        this.author_surname = a_surname;
        this.date_of_birth = a_date_of_birth;
    }

    public int getId_author() {
        return id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getAuthor_surname() {
        return author_surname;
    }

    public DateTime getAuthor_date_of_birth() {
        return date_of_birth;
    }

    public void setId_author(int id_author) {
        this.id = id_author;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setAuthor_surname(String author_surname) {
        this.author_surname = author_surname;
    }

    public void setAuthor_date_of_birth(DateTime author_date_of_birth) {
        this.date_of_birth = author_date_of_birth;
    }
}
