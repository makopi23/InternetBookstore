/*
    This is a sample class from the Internet Bookstore example project,
    for the book "Use Case Driven Object Modeling with UML - Theory and Practice".
    Copyright (c) Doug Rosenberg and Matt Stephens, 2007.
    
    For more info, see: http://www.iconixprocess.com/books/use-case-driven/
    
    Some of the code is "boilerplate" or adapted from the Spring Framework examples. For more
    info on Spring Framework, please see: http://www.springframework.org/
    
    Please note: This code is for learning purposes only, and comes with no warranty of any kind.
    
    This code represents the Bookstore at various stages of development and review,
    as described in the "Use Case Driven" book. We encourage you to compare this code with
    the models illustrated in the book, and especially with the "review" chapters. Have fun!
*/
package com.iconixsw.bookstore.domain;

import com.iconixsw.bookstore.dao.*;
import org.springframework.dao.*;
import org.springframework.validation.*;

import java.io.*;

public class Book implements DomainObject, Serializable {
    
    private int id;
    private String title;
    private String synopsis;
    
    public Book() {
    }
    
    public void load(BookDao bookDao) throws NotFoundException {
        bookDao.load(this);
    }
    
    public void load(int id, BookDao bookDao) throws NotFoundException {
        setId(id);
        bookDao.load(this);
    }
    
    public void validate(Errors errors) {
    }
    
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    //public boolean doesBookIdExist(BookDao bookDao) throws DataAccessException {
    //    return bookDao.doesBookIdExist(id);
    //}
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    
}
