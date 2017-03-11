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
package test.com.iconixsw.bookstore.dao;

import com.iconixsw.bookstore.dao.*;
import com.iconixsw.bookstore.dao.mock.*;
import com.iconixsw.bookstore.domain.*;
import junit.framework.*;

/**
 * Test case for the Retrieve Book Details controller.
 */
public class RetrieveBookDetailsTest extends TestCase {
    
    private BookDao bookDao;
    
    public RetrieveBookDetailsTest(java.lang.String testName) {
        super(testName);
    }
    
    public void setUp() throws Exception {
        bookDao = new MockBookDao();
    }
    
    public void tearDown() throws Exception {
        bookDao = null;
    }
    
    public void testBookIdFound() {
        Book book = new Book();
        try {
            book.load(1, bookDao);
        }
        catch (NotFoundException e) {
            fail("Book should have been found.");
        }
    }

    public void testBookIdNotFound() {
        Book book = new Book();
        try {
            book.load(-1, bookDao);
        }
        catch (NotFoundException e) {
            return;
        }
        fail("Book should not have been found.");
    }
    
    
}
