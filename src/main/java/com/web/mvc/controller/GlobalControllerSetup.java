package com.web.mvc.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global Controller setup
 * 
 */
@ControllerAdvice
public class GlobalControllerSetup {
	
	
	private static final String ERROR_PAGE = "error";
	private static final String ERROR_MSG = "errorMsg";

	/**
	 * Global resgitration of editors
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder (WebDataBinder binder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	/**
	 * Global Exception handling
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView(ERROR_PAGE);
		model.addObject(ERROR_MSG, ex.getMessage());
		return model;
	}
}
