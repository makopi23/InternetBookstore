package com.iconixsw.bookstore.domain;

import org.springframework.dao.*;

/**
 * This exception is thrown if a <domain object>.load() method is
 * called but the ID isn't found in the database.
 * @author Matt
 */
public class NotFoundException extends DataAccessException {
    
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(int id) {
        super("Domain object not found in the database: " + id);
    }
    
}
