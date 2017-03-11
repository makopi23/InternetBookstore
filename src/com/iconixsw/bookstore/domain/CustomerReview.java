package com.iconixsw.bookstore.domain;

import com.iconixsw.bookstore.dao.*;
import org.springframework.dao.*;
import org.springframework.validation.*;

import java.io.*;

public class CustomerReview implements DomainObject, Serializable {
    
    private int id;
    private Book book;
    private Customer customer;
    private String title;
    private String review;
    private int rating;
    
    public CustomerReview() {
    }

    public void validate(Errors errors) {
        checkBookExists(errors);
        checkTitleLength(errors);
        checkReviewLength(errors);
        checkRating(errors);
    }
    
    private void checkBookExists(Errors errors) {
        if (book == null) {
            errors.rejectValue("book", "BookNotFound", "The selected book could not be found.");
        }
    }

    private void checkTitleLength(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required", "Review title is required");
        if (title != null && title.length() > 100) {
            errors.rejectValue("title", "too_long", "The review title mustn't be more than 100 characters long.");
        }
    }
    
    private void checkReviewLength(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "review", "required", "Review text is required");
        if (review != null && review.length() > 10000) {
            errors.rejectValue("review", "too_long", "The review you entered is a novel in itself; please try to shorten it");
        }
    }
    
    private void checkRating(Errors errors) {
        if (rating < 1 || rating > 5) {
            errors.rejectValue("rating", "outofrange", "The rating must be between 1 and 5");
        }
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
}
