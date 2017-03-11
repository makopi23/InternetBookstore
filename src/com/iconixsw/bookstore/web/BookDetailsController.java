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
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.iconixsw.bookstore.dao.BookDao;
import com.iconixsw.bookstore.domain.Book;
import com.iconixsw.bookstore.domain.NotFoundException;

public class BookDetailsController extends AbstractCommandController {

    public BookDetailsController() {
        setCommandClass(Book.class);
    }

    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
    	System.out.println("BookDetailsController#handle");
    	Book book = (Book) command;
        try {
            book.load(bookDao);
        }
        catch (NotFoundException e) {
            return new ModelAndView("booknotfound");
        }
        //Book book = bookDao.findById(detailsCommand.getId());

        //if (!checkBookFound(book)) {
        //    return new ModelAndView("booknotfound");
        //}

        return new ModelAndView("bookdetails", "book", book);
    }

    //private boolean checkBookFound(Book book) {
    //    return book.isLoaded();
    //}

    public ModelAndView testHandle(Book command) throws Exception {
        return handle(null, null, command, null);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    private BookDao bookDao;

}
