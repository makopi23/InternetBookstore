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

public class JdbcCustomerDao extends JdbcDaoSupport implements CustomerDao, InitializingBean {
    
    private static final String FIND_BY_ID_SQL = "select c.* from Customers c where c.id=?";
    private static final String FIND_BY_EMAIL_SQL = "select c.* from Customers c where c.email=?";

    // implements CustomerDao
    public Customer findById(int customerId) {
        return (Customer) findById.findObject(customerId);
    }

    // implements CustomerDao
    public Customer findByEmail(String email) {
        return (Customer) findByEmail.findObject(email);
    }
    
    
    abstract class AbstractSelect extends MappingSqlQuery {
        public AbstractSelect(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }

        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstname"));
            customer.setLastName(rs.getString("lastname"));
            return customer;
        }
    }

    class FindById extends AbstractSelect {
        public FindById(DataSource dataSource) {
            super(dataSource, FIND_BY_ID_SQL);
            declareParameter(new SqlParameter(Types.INTEGER)); // Customer Id
        }
    }
    
    class FindByEmail extends AbstractSelect {
        public FindByEmail(DataSource dataSource) {
            super(dataSource, FIND_BY_EMAIL_SQL);
            declareParameter(new SqlParameter(Types.VARCHAR)); // email
        }
    }
    
    private FindById findById;
    private FindByEmail findByEmail;

    protected void initDao() throws Exception {
        super.initDao();
        findById = new FindById(getDataSource());
        findByEmail = new FindByEmail(getDataSource());
    }
    
    
}
