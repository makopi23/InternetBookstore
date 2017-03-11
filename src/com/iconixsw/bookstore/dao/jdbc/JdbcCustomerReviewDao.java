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
package com.iconixsw.bookstore.dao.jdbc;

import com.iconixsw.bookstore.dao.*;
import com.iconixsw.bookstore.domain.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.springframework.beans.factory.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.*;
import org.springframework.jdbc.object.*;

public class JdbcCustomerReviewDao extends JdbcDaoSupport implements CustomerReviewDao, InitializingBean {
    
    private static final String FIND_BY_ID_SQL = "select r.* from CustomerReviews r where r.id=?";
    
    private static final String INSERT_SQL = "insert into CustomerReviews " +
                "(bookID, customerID, title, review, rating) " +
                "values (?, ?, ?, ?, ?)";


    // implements CustomerReviewDao
    public CustomerReview findById(int reviewId) throws DataAccessException {
        return (CustomerReview) findById.findObject(reviewId);
    }

    // implements CustomerReviewDao
    public void save(CustomerReview review) throws DataAccessException {
        insert.update(new Object[] {new Integer(review.getBook().getId()),
                                    new Integer(review.getCustomer().getId()),
                                    review.getTitle(),
                                    review.getReview(),
                                    new Integer(review.getRating())
                      });
        
        // TODO: also insert a row into PendingReviewsQueue.
    }    
    
    abstract class AbstractSelect extends MappingSqlQuery {
        public AbstractSelect(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }

        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerReview review = new CustomerReview();
            review.setId(rs.getInt("id"));
            
            // TODO: When review-reading function is added, wire bookDao & customerDao to JdbcCustomerReviewDao:
            //int bookId = rs.getInt("bookid");
            //Book book = bookDao.findById(bookId);
            //review.setBook(book);
            
            //int customerId = rs.getInt("customerid");
            //Customer customer = customerDao.findById(customerId);
            //review.setCustomer(customer);
            
            review.setReview(rs.getString("review"));
            review.setRating(rs.getInt("rating"));
            return review;
        }
    }

    class FindById extends AbstractSelect {
        public FindById(DataSource dataSource) {
            super(dataSource, FIND_BY_ID_SQL);
            declareParameter(new SqlParameter(Types.INTEGER)); // Customer Id
        }
    }
    
    class Insert extends SqlUpdate {
        public Insert(DataSource dataSource) {
            super(dataSource, INSERT_SQL);
            declareParameter(new SqlParameter(Types.INTEGER));  // Book Id
            declareParameter(new SqlParameter(Types.INTEGER));  // Customer Id
            declareParameter(new SqlParameter(Types.VARCHAR));  // Title
            declareParameter(new SqlParameter(Types.VARCHAR));  // Review
            declareParameter(new SqlParameter(Types.INTEGER));  // Rating
        }
    }
    
    private FindById findById;
    private Insert insert;

    protected void initDao() throws Exception {
        super.initDao();
        findById = new FindById(getDataSource());
        insert = new Insert(getDataSource());
    }
    
}
