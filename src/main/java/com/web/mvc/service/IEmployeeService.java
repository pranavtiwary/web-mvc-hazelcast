package com.web.mvc.service;

import com.web.mvc.request.AddEmployeeRequest;
import com.web.mvc.response.AddEmployeeResponse;

/**
 * Employee service
 *
 */
public interface IEmployeeService {

	public AddEmployeeResponse addEmployee(AddEmployeeRequest employeeRequest);
}
