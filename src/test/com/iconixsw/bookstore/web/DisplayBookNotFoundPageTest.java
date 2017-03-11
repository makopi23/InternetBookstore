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
package test.com.iconixsw.bookstore.web;

import com.iconixsw.bookstore.dao.mock.*;
import com.iconixsw.bookstore.domain.*;
import com.iconixsw.bookstore.web.*;
import java.util.*;
import javax.servlet.http.*;
import junit.framework.*;
import org.springframework.validation.*;
import org.springframework.web.servlet.*;

/**
 * Test case for the Display 'Book Not Found' Page controller.
 */
public class DisplayBookNotFoundPageTest extends TestCase {
    
    public DisplayBookNotFoundPageTest(String testName) {
        super(testName);
    }
    
    public void setUp() throws Exception {
        helper = new ShowBookTestHelper();
    }
    private ShowBookTestHelper helper;
    
    public void testBookNotFoundPageDisplayed() throws Exception {
        ModelAndView modelAndView = helper.callTestHandle(-1);
        assertEquals("The booknotfound page should be displayed", "booknotfound", modelAndView.getViewName());
    }

    public void testBookDetailsNotFound() throws Exception {
        ModelAndView modelAndView = helper.callTestHandle(-1);
        Map model = modelAndView.getModel();
        Book book = (Book) model.get("book");
        assertNull("The book should not have been found", book);
    }
    
}
