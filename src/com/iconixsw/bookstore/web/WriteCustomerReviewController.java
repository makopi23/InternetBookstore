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

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.iconixsw.bookstore.dao.BookDao;
import com.iconixsw.bookstore.dao.CustomerDao;
import com.iconixsw.bookstore.dao.CustomerReviewDao;
import com.iconixsw.bookstore.domain.Book;
import com.iconixsw.bookstore.domain.Customer;
import com.iconixsw.bookstore.domain.CustomerReview;
import com.iconixsw.bookstore.domain.NotFoundException;

public class WriteCustomerReviewController extends SimpleFormController {

    public WriteCustomerReviewController() {
        setCommandClass(CustomerReview.class);
    }

    protected void doSubmitAction(Object command) throws Exception {
        CustomerReview review = (CustomerReview) command;
        System.out.println("WriteCustomerReviewController#doSubmitAction");
        System.out.println("BookId : " + review.getBook().getId());
        System.out.println("CustomerId : " + review.getCustomer().getId());
        System.out.println("Title : " + review.getTitle());
        System.out.println("Review : " + review.getReview());
        System.out.println("Ranking : " + review.getRating());

        // review has no id set, so DAO will create a new record in the DB:
        customerReviewDao.save(review);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Customer customer = findCustomer(request);
        if (customer == null) {
            return null;
        }
        Book book = findBook(request);
        return newBlankReview(book, customer);
    }

    private Customer findCustomer(HttpServletRequest request) throws Exception {
        CustomerSession customerSession = CustomerSession.getCustomerSession(request);
        if (!customerSession.isLoggedIn()) {
            return null;
        }
        return customerSession.getCustomer();
    }


    private Book findBook(HttpServletRequest request) throws Exception {
        int bookId = RequestUtils.getRequiredIntParameter(request, "bookid");
        Book book = new Book();
        try {
            book.load(bookId, bookDao);
        }
        catch (NotFoundException e) {
            return null;
        }
        return book;
    }

    /**
     * Create a new, blank review for the book and customer.
     */
    private CustomerReview newBlankReview(Book book, Customer customer) {
        CustomerReview review = new CustomerReview();
        review.setBook(book);
        review.setCustomer(customer);
        return review;
    }


    /*
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {
        CustomerSession session = getCustomerSession(request);
        if (!session.isLoggedIn()) {
            return new ModelAndView("SignOnForm");
        }

        WriteCustomerReviewCommand reviewCommand = (WriteCustomerReviewCommand) command;
        Book book = bookDao.findById(reviewCommand.getBookId());
        Customer customer = customerDao.findById(reviewCommand.getCustomerId());

        if (book == null) {
            return new ModelAndView("booknotfound");
        }
        if (book == null) {
            return new ModelAndView("customernotfound");
        }

        Map model = new HashMap();
        model.put("customer", customer);
        model.put("book", book);

        return new ModelAndView("WriteCustomerReview", model);
    }
     */

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setCustomerReviewDao(CustomerReviewDao customerReviewDao) {
        this.customerReviewDao = customerReviewDao;
    }

    private BookDao bookDao;
    private CustomerDao customerDao;
    private CustomerReviewDao customerReviewDao;

}
