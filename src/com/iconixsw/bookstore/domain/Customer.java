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

import java.io.*;
import org.springframework.validation.*;

public class Customer implements DomainObject, Serializable {
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    
    public Customer() {
    }
    
    public void validate(Errors errors) {
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
}
