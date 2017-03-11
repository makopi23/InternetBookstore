package com.iconixsw.bookstore.domain.logic;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.iconixsw.bookstore.domain.CustomerReview;

public class CustomerReviewValidator implements Validator {

	@Override
	public boolean supports(Class commandClass) {
		return commandClass.isAssignableFrom(CustomerReview.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors,
				"review",
				"required",
				"Review text is required");
	}

}
