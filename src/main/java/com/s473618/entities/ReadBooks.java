package com.s473618.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
public class ReadBooks implements Serializable {

    @Id
    @GeneratedValue(generator = "gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "gen", sequenceName = "read_seq")
    int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="book_id")
    Book book;

    @Column(nullable = true, length = 1000)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    DateTime date;

    public ReadBooks() {}

    public ReadBooks(Book b){
        this.book = b;
    }

    public ReadBooks(Book b, DateTime d){
        this.book = b;
        this.date = d;
    }

    public Book getBook() {
        return book;
    }

    public DateTime getDate() {
        return date;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
}
