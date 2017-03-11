package com.iconixsw.bookstore.domain;

import org.springframework.validation.*;

public interface DomainObject {
    
    public void validate(Errors errors);
    
}
