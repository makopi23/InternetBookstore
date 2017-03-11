package com.iconixsw.bookstore.domain;

import com.iconixsw.bookstore.web.*;
import org.springframework.validation.*;

public class DomainObjectValidator implements Validator {
    
    public boolean supports(Class commandClass) {
        return commandClass.isAssignableFrom(DomainObject.class);
        //return commandClass.getMethod("validate", new Class[]{}) != null;
        //return commandClass.isAssignableFrom(CustomerReview.class);
    }
    
    public void validate(Object command, Errors errors) {
        DomainObject domainObj = (DomainObject) command;
        domainObj.validate(errors);
    }
    
}
