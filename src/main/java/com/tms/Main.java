package com.tms;

import com.tms.model.Author;
import com.tms.model.Book;
import com.tms.model.Page;
import com.tms.repository.AuthorRepository;
import com.tms.repository.BookRepository;
import com.tms.repository.PageRepository;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
/*      PageRepository pageRepository = new PageRepository();
        Page p = pageRepository.getPageById(1);
        System.out.println(p);
        System.out.println(p.getBook());*/

/*        BookRepository bookRepository = new BookRepository();
        Book b = bookRepository.getBookById(1);
        System.out.println(b);
        System.out.println(b.getPages());
        System.out.println(b.getAuthors());*/

        AuthorRepository authorRepository = new AuthorRepository();
        for (Author a : authorRepository.getAllAuthors()){
            System.out.println(a);
            System.out.println(a.getBooks());
        }
    }
}
