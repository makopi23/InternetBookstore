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
package test.com.iconixsw.bookstore.domain.logic;

import com.iconixsw.bookstore.domain.*;
import com.iconixsw.bookstore.domain.DomainObjectValidator;
import com.iconixsw.bookstore.web.*;
import java.util.*;
import junit.framework.*;
import org.springframework.validation.*;

public class CustomerReviewValidatorTest extends TestCase {
    
    public CustomerReviewValidatorTest(java.lang.String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(CustomerReviewValidatorTest.class);
        return suite;
    }
    
    public void setUp() {
        validator = new DomainObjectValidator();
        //reviewCommand.setBook(new Book());
        customerReview = new CustomerReview();
        
        
        // TODO: params passed into BindException are guesses:
        errors = new BindException(customerReview, "CustomerReview");
    }
    private DomainObjectValidator validator;
    private CustomerReview customerReview;
    private Errors errors;
    
    public void testEmptyReviewFailsValidation() {
        customerReview.setReview("");
        customerReview.setRating(1);
        validator.validate(customerReview, errors);
        
        assertEquals("There should be at least one validation error", 1, errors.getAllErrors().size());
        
        // Double-check that it's the right one:
        List errorList = errors.getAllErrors();
        for (Iterator iter=errorList.iterator(); iter.hasNext();) {
            ObjectError err = (ObjectError) iter.next();
            assertEquals("required", err.getCode());
        }
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
}
