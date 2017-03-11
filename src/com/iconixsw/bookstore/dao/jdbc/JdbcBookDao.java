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
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.*;
import org.springframework.jdbc.object.*;

public class JdbcBookDao extends JdbcDaoSupport implements BookDao, InitializingBean {
    
    private static final String FIND_BY_ID_SQL = "select b.* from Books b where b.id=?";
    private BookLoader bookLoader;

    public void load(Book book) throws NotFoundException {
        bookLoader.findAndPopulate(book);
    }

    protected void initDao() throws Exception {
        super.initDao();
        bookLoader = new BookLoader(getDataSource());
    }
    
    class BookLoader extends MappingSqlQueryWithParameters {  // extends AbstractSelect
        public BookLoader(DataSource dataSource) {
            super(dataSource, FIND_BY_ID_SQL);
            declareParameter(new SqlParameter(Types.INTEGER)); // Book Id
        }
        
        public void findAndPopulate(Book book) throws NotFoundException {
            Map context = new HashMap(1);
            context.put("book", book);
            Book foundBook = (Book) findObject(book.getId(), context);
            if (foundBook == null) {
                throw new NotFoundException("Book ID not found: " + book.getId());
            }
        }
        
        protected Object mapRow(ResultSet rs, int rowNum, Object[] parameters, Map context) throws SQLException {
            Book book = (Book) context.get("book");
            int foundId = rs.getInt("id");
            //assert(foundId == book.getId());
            book.setTitle(rs.getString("title"));
            book.setSynopsis(rs.getString("synopsis"));
            return book;
        }
    }
    
}
