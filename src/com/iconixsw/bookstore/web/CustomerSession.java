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
package com.iconixsw.bookstore.web;

import com.iconixsw.bookstore.domain.*;
import javax.servlet.http.*;

/**
 * Tracks the login details for a Customer.
 */
public class CustomerSession {
    
    private Customer customer;
    
    public CustomerSession() {
        makeFakeCustomer();
    }
    
    private void makeFakeCustomer() {
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Billy Bob");
        customer.setLastName("Red");
        customer.setEmail("billybob@abc.xyz");
    }
    
    /**
     * Gets the current CustomerSession, or creates a new one.
     */
    public static CustomerSession getCustomerSession(HttpServletRequest request) {
        CustomerSession customerSession = (CustomerSession) request.getSession().getAttribute("CustomerSession");
        if (customerSession == null) {
            customerSession = new CustomerSession();
            request.getSession().setAttribute("CustomerSession", customerSession);
        }
        return customerSession;
    }
    
    public Customer getCustomer() { return customer; }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public boolean isLoggedIn() {
        return customer != null;
    }
    
}
