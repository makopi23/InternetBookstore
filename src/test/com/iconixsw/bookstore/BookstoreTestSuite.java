package test.com.iconixsw.bookstore;

import test.com.iconixsw.bookstore.web.*;
import junit.framework.*;

public class BookstoreTestSuite {
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        //suite.addTestSuite(CustomerReviewValidatorTest.class);
        //suite.addTestSuite(DisplayHomePageTest.class);
        //suite.addTestSuite(RetrieveBookDetailsTest.class);
        suite.addTestSuite(DisplayBookDetailsPageTest.class);
        suite.addTestSuite(DisplayBookNotFoundPageTest.class);
        
        return suite;
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
}
