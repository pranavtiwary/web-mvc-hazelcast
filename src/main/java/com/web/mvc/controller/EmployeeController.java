package com.web.mvc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.mvc.request.AddEmployeeRequest;
import com.web.mvc.response.AddEmployeeResponse;
import com.web.mvc.service.IEmployeeService;
import com.web.mvc.validator.RequestValidator;
/**
 * Parsing controller
 * 
 *
 */
@RestController("/emp")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService service;

	@Autowired
	private RequestValidator validator;

	@InitBinder("employeeRequest")
	protected void init(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(name="/add", method = RequestMethod.POST, consumes="application/json")
	public AddEmployeeResponse addEmployee(@Valid @RequestBody AddEmployeeRequest employeeRequest,
			BindingResult bindingResult, Errors error) {
		logger.info("Got Request to add employee : {}", employeeRequest);
		AddEmployeeResponse response=null;
		if (bindingResult.hasErrors()) {
			response = new AddEmployeeResponse(false,"Not Valid Data");
		}
		response = service.addEmployee(employeeRequest);
		logger.info("Response to add employee : {}", response);
		return response;
	}
}
