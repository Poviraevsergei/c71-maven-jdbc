package com.tms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Entity(name = "books")
@Data
@ToString(exclude = {"pages", "authors"})
@EqualsAndHashCode(exclude = {"pages", "authors"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "book_name")
    private String bookName;

    //TODO: @JsonBackReference - чтобы Jackson не отрисовывал Book после того как зайдет в page.
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER) //fetch - как вытаскивать коллекции(EAGER всегда выгружай)
    private Collection<Page> pages; //пока lazy тут заглушка если не обращаемся к этому полю во время транзакции.

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Collection<Author> authors;
}
