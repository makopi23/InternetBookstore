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

import com.iconixsw.bookstore.web.*;

import junit.framework.*;
import org.springframework.web.servlet.*;

public class DisplayHomePageTest extends TestCase {
    
    private HomeController homeController;
    
    public DisplayHomePageTest(java.lang.String testName) {
        super(testName);
    }
    
    public void setUp() throws Exception {
        homeController = new HomeController();
    }
    
    public void tearDown() throws Exception {
        homeController = null;
    }
    
    public void testDisplayHomePage() throws Exception {
        ModelAndView modelAndView = homeController.handleRequest(null, null);
        assertEquals("Should be viewing home.jsp.", "home", modelAndView.getViewName());
    }
    
}
