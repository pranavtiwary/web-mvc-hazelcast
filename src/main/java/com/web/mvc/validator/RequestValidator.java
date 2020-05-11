package com.web.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.web.mvc.request.AddEmployeeRequest;

/**
 * Validator of parsing request
 *
 */
@Component
public class RequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddEmployeeRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddEmployeeRequest req = (AddEmployeeRequest) target;
		if(null==req.getName()){
			errors.rejectValue("name", null, "Url can not be empty");
			return;
		}
	}

}
