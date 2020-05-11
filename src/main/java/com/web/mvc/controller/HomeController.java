package com.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.web.mvc.request.AddEmployeeRequest;

/**
 * Home Controller
 *
 */
@Controller
public class HomeController {

	private static final String INDEX_PAGE = "home";

	/**
	 * Launch the home page
	 * @param parsingRequest
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView showForm(@ModelAttribute AddEmployeeRequest parsingRequest) {
		ModelAndView mv = new ModelAndView(INDEX_PAGE);
		return mv;
	}
}
